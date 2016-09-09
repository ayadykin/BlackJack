package com.ayadykin.blackjack.core.state.impl;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import com.ayadykin.blackjack.actions.BlackJackResponce;
import com.ayadykin.blackjack.core.GameFlow;
import com.ayadykin.blackjack.core.blackjack.BlackJackCore;
import com.ayadykin.blackjack.core.model.Dealer;
import com.ayadykin.blackjack.core.state.GameState;
import com.ayadykin.blackjack.core.state.qualifiers.StartGameState;
import com.ayadykin.blackjack.core.table.Table;
import com.ayadykin.blackjack.exceptions.BlackJackException;

/**
 * Created by Yadykin Andrii Sep 8, 2016
 *
 */

@StartGameState
@SessionScoped
public class StartGameStateImpl implements GameState, Serializable {

    @EJB
    private transient BlackJackCore blackJackCore;

    private GameFlow gameFlow;
    
    public StartGameStateImpl(){
    	
    }
    		
    /**
     * Inject game flow for change game state
     * 
     * @param gameFlow
     */
    @Inject
    public StartGameStateImpl(GameFlow gameFlow) {
        this.gameFlow = gameFlow;
    }

    @Override
    public void newGame(Table table) {
        throw new BlackJackException("Error you can't call newGame() method, startGameState can call hit() or stand methods!");

    }

    @Override
    public void startGame(Table table) {
        throw new BlackJackException("Error you can't call initGame() method, startGameState can call hit() or stand methods!");
    }

    @Override
    public BlackJackResponce hit(Table table) {
        BlackJackResponce blackJackResponce = blackJackCore.playerStep(table.getPlayer(), table.getCard());
        if (blackJackResponce == BlackJackResponce.YOU_BUST) {
            gameFlow.setState(gameFlow.getEndGameState());
        }

        return blackJackResponce;

    }

    @Override
    public BlackJackResponce stand(Table table) {
        BlackJackResponce blackJackResponce;
        Dealer dealer = table.getDealer();
        do {
            blackJackResponce = blackJackCore.dealerStep(dealer, table.getCard());
        } while (blackJackResponce == BlackJackResponce.NEXT_STEP);

        gameFlow.setState(gameFlow.getEndGameState());

        if (blackJackResponce != BlackJackResponce.DEALER_BUST) {
            int dealerPoints = dealer.getPoints();
            int playerPoints = table.getPlayer().getPoints();
            return blackJackCore.getGameResult(playerPoints, dealerPoints);
        }

        return blackJackResponce;
    }

    @Override
    public double endGame(BlackJackResponce blackJackResponce, double bet) {
        throw new BlackJackException("Error you can't call endGame() method, startGameState can call hit() or stand methods!");
    }

}
