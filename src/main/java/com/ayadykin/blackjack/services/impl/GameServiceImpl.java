package com.ayadykin.blackjack.services.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import com.ayadykin.blackjack.actions.BlackJackResponce;
import com.ayadykin.blackjack.actions.GameStatus;
import com.ayadykin.blackjack.core.BlackJackCore;
import com.ayadykin.blackjack.core.GameFlow;
import com.ayadykin.blackjack.core.Table;
import com.ayadykin.blackjack.core.deal.DealCards;
import com.ayadykin.blackjack.core.model.Card;
import com.ayadykin.blackjack.core.model.CardDeck;
import com.ayadykin.blackjack.core.model.Player;
import com.ayadykin.blackjack.exceptions.BlackJackException;
import com.ayadykin.blackjack.rest.dto.ResponseDto;
import com.ayadykin.blackjack.services.GameService;

/**
 * Created by Andrey Yadykin on 22.02.2016.
 */

@Stateless
public class GameServiceImpl implements GameService {

    @Inject
    @Named("blackJackDeal")
    private DealCards blackJackDealStrategy;
    @EJB
    private BlackJackCore blackJackCore;
    // @EJB
    // private AccountService accountService;
    @Inject
    private Table table;
    @Inject
    private GameFlow gameFlow;

    @Override
    public ResponseDto initGame(Integer id) throws BlackJackException {

        GameStatus gameAction = gameFlow.getGameStatus();

        List<Player> players = table.getPlayers();
        CardDeck cardDeck = table.getCardDeck();
        BlackJackResponce blackJackResponce = null;

        switch (id) {

        case 1:
            // Init
            table.init(id, 10);

            blackJackDealStrategy.dealCards(players, cardDeck);

            blackJackResponce = blackJackCore.checkBlackJack(players);
            checkResponse(blackJackResponce, table.getPlayer());

            gameFlow.setGameStatus(GameStatus.START);
            break;
        case 2:
            // HIT
            if (gameFlow.getGameStatus().equals(GameStatus.END)) {
                throw new BlackJackException("Error game is over");
            }
            Player player = table.getPlayer();
            
            Card card = table.getCard();
            card.setHidden(false);
            player.addCard(card);
            
            blackJackResponce = blackJackCore.checkPlayerStep(player);
            checkResponse(blackJackResponce, player);

            break;
        case 3:
            // Stand
            player = table.getPlayer();
            card = table.getCard();

            Player dealer = table.getDealer();
            blackJackResponce = blackJackCore.dealerStep(dealer, card);
            blackJackResponce = checkResponse(blackJackResponce, player);

            break;
        }

        if (gameAction.equals(GameStatus.END)) {
            table.resetGame();
        }

        return new ResponseDto(players, blackJackResponce);
    }

    private BlackJackResponce checkResponse(BlackJackResponce blackJackResponce, Player player) throws BlackJackException {
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

        // Account account = accountService.updateAccount(player.getId(), bet);
        // player.setCash(account.getAccount());
        gameFlow.setGameStatus(GameStatus.END);
    }

    @Remove
    public void destroy() {
        table = null;
    }

}
