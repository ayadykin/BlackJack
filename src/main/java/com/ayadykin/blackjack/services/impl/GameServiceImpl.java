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
import com.ayadykin.blackjack.core.cards.Card;
import com.ayadykin.blackjack.core.cards.CardDeck;
import com.ayadykin.blackjack.core.deal.DealCards;
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

        //List<Player> players = table.getPlayers();
        BlackJackResponce blackJackResponce = null;

        switch (id) {
        case 0 :
        	// Init
            table.init(id, 10);
        	break;
        case 1:   
            blackJackDealStrategy.dealCards(table.getPlayers(),table.getCardDeck());
            blackJackResponce = blackJackCore.checkBlackJack(table);
            gameFlow.setGameStatus(GameStatus.START);
            break;
        case 2:
            // HIT
            if (!gameFlow.getGameStatus().equals(GameStatus.START)) {
                throw new BlackJackException("Error game is over");
            }           
            blackJackResponce = blackJackCore.playerHit(table);
            gameResult(blackJackResponce);
            break;
        case 3:
            // Stand
            blackJackResponce = blackJackCore.dealerStep(table);
            gameResult(blackJackResponce);
            gameFlow.setGameStatus(GameStatus.END);
            break;
        }

        if (gameAction.equals(GameStatus.END)) {
            table.resetGame();
        }

        return new ResponseDto(table.getPlayers(), blackJackResponce);
    }

    private void gameResult(BlackJackResponce blackJackResponce) {

        double bet = table.getBet();

        switch (blackJackResponce) {
        case PUSH:
        	break;
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
    }

    @Remove
    public void destroy() {
        table = null;
    }

}
