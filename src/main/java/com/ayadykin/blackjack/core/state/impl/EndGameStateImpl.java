package com.ayadykin.blackjack.core.state.impl;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import com.ayadykin.blackjack.actions.BlackJackResponce;
import com.ayadykin.blackjack.core.BlackJackCore;
import com.ayadykin.blackjack.core.GameFlow;
import com.ayadykin.blackjack.core.model.Player;
import com.ayadykin.blackjack.core.state.EndGameState;
import com.ayadykin.blackjack.core.state.GameState;
import com.ayadykin.blackjack.core.table.Table;
import com.ayadykin.blackjack.exceptions.BlackJackException;

/**
 * Created by Yadykin Andrii Sep 8, 2016
 *
 */

@EndGameState
@SessionScoped
public class EndGameStateImpl implements GameState, Serializable {
    @EJB
    private BlackJackCore blackJackCore;

    private GameFlow gameFlow;
    
    public EndGameStateImpl(){
    	
    }
    
    @Inject
    public EndGameStateImpl(GameFlow gameFlow) {
        this.gameFlow = gameFlow;
    }

    @Override
    public void newGame(Table table) {
        throw new BlackJackException("Error game is over");
    }

    @Override
    public void initGame(Table table) {
        throw new BlackJackException("Error game is over");
    }

    @Override
    public BlackJackResponce hit(Table table) {
        throw new BlackJackException("Error game is over");

    }

    @Override
    public BlackJackResponce stand(Table table) {
        throw new BlackJackException("Error game is over");
    }

    @Override
    public void endGame(BlackJackResponce blackJackResponce, Player player) {

        double bet = player.getBet();

        switch (blackJackResponce) {
        case PUSH:
            break;
        case LOSE:
        case YOU_BUST:
            bet = -bet;
            break;
        case BLACK_JACK:
            bet += bet * 1.5;
            break;
        case WIN:
        case DEALER_BUST:
            bet = +bet;
            break;
        default:
            break;
        }

        player.addBetToCash(bet);

        gameFlow.setState(gameFlow.getInitGameState());
    }

}
