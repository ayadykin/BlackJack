package com.ayadykin.blackjack.services.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ayadykin.blackjack.actions.BlackJackResponce;
import com.ayadykin.blackjack.actions.GameStatus;
import com.ayadykin.blackjack.core.BlackJackCore;
import com.ayadykin.blackjack.core.GameFlow;
import com.ayadykin.blackjack.core.Table;
import com.ayadykin.blackjack.core.model.Account;
import com.ayadykin.blackjack.core.model.Card;
import com.ayadykin.blackjack.core.model.CardDeck;
import com.ayadykin.blackjack.core.model.Player;
import com.ayadykin.blackjack.exceptions.BlackJackException;
import com.ayadykin.blackjack.rest.dto.PlayerActionDto;
import com.ayadykin.blackjack.rest.dto.ResponseDto;
import com.ayadykin.blackjack.services.AccountService;
import com.ayadykin.blackjack.services.GameService;

/**
 * Created by Andrey Yadykin on 22.02.2016.
 */

@Stateless
public class GameServiceImpl implements GameService {

    @EJB
    private BlackJackCore blackJackCore;
    @EJB
    private AccountService accountService;
    @Inject
    private Table table;
    @Inject
    private GameFlow gameFlow;

    @Override
    public ResponseDto initGame(long id) throws BlackJackException {

        GameStatus gameAction = gameFlow.getGameStatus();

        if (gameAction.equals(GameStatus.NEW)) {
            if (id == 0l) {
                id = accountService.createAccount();
            }
            Account account = accountService.getAccount(id);
            table.init(id, 10);
        } else if (gameAction.equals(GameStatus.END)) {
            table.resetGame();
        } else {
            throw new BlackJackException("Error game flow");
        }

        gameFlow.setGameStatus(GameStatus.START);

        List<Player> players = table.getPlayers();
        CardDeck cardDeck = table.getCardDeck();
        blackJackCore.dealCards(players, cardDeck);
        BlackJackResponce blackJackResponce = blackJackCore.checkBlackJack(players);
        checkResponse(blackJackResponce, table.getPlayer());

        return new ResponseDto(players, blackJackResponce);
    }

    @Override
    public int setBet(int bet) {
        table.setBet(bet);
        return bet;
    }

    @Override
    public ResponseDto action(PlayerActionDto playerActionDto) throws BlackJackException {

        Card card = table.getCard();
        Player player = table.getPlayer();
        BlackJackResponce blackJackResponce = null;
        switch (playerActionDto.getBlackJackAction()) {
        case HIT:
            if (gameFlow.getGameStatus().equals(GameStatus.END)) {
                throw new BlackJackException("Error game is over");
            }
            blackJackResponce = blackJackCore.playerGetCard(player, card);
            checkResponse(blackJackResponce, player);

            break;
        case STAND:
            Player dealer = table.getDealer();
            blackJackResponce = blackJackCore.dealerStep(dealer, card);
            blackJackResponce = checkResponse(blackJackResponce, player);

            break;
        }
        return new ResponseDto(table.getPlayers(), blackJackResponce);

    }

    private BlackJackResponce checkResponse(BlackJackResponce blackJackResponce, Player player)
            throws BlackJackException {
        if (blackJackResponce.equals(BlackJackResponce.DEALER_STAND)) {
            blackJackResponce = getGameResultByPoints();
        }
        if (!blackJackResponce.equals(BlackJackResponce.NEXT_MOVE)) {
            finishGame(blackJackResponce, player);
        }

        return blackJackResponce;
    }

    private BlackJackResponce getGameResultByPoints() throws BlackJackException {
        Player dealer = table.getDealer();
        Player player = table.getPlayer();
        return blackJackCore.getGameResult(player.getPoints(), dealer.getPoints());
    }

    private void finishGame(BlackJackResponce blackJackResponce, Player player) {

        double bet = table.getBet();

        switch (blackJackResponce) {
        case LOSE:
            bet = -bet;
            break;
        case BLACK_JACK:
            bet = bet * 1.5;
            break;
        case WIN:
            bet += bet;
            break;
        default:
            break;
        }

        Account account = accountService.updateAccount(player.getId(), bet);
        player.setCash(account.getAccount());
        gameFlow.setGameStatus(GameStatus.END);
    }

    @Remove
    public void destroy() {
        table = null;
    }

}
