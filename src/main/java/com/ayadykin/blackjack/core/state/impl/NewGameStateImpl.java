package com.ayadykin.blackjack.core.state.impl;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import com.ayadykin.blackjack.actions.BlackJackResponce;
import com.ayadykin.blackjack.core.GameFlow;
import com.ayadykin.blackjack.core.state.GameState;
import com.ayadykin.blackjack.core.state.qualifiers.NewGameState;
import com.ayadykin.blackjack.core.table.Table;
import com.ayadykin.blackjack.exceptions.BlackJackException;

/**
 * Created by Yadykin Andrii Sep 8, 2016
 *
 */

@NewGameState
@SessionScoped
public class NewGameStateImpl implements GameState, Serializable {

    private GameFlow gameFlow;

    public NewGameStateImpl(){
    	
    }
    
    @Inject
    public NewGameStateImpl(GameFlow gameFlow) {
        this.gameFlow = gameFlow;
    }

    @Override
    public void newGame(Table table) {
        table.init(1);
        //table.addPlayer(new);
        gameFlow.setState(gameFlow.getInitGameState());
    }

    @Override
    public void startGame(Table table) {
        throw new BlackJackException("Error newGameState can only call newGame() method!");
    }

    @Override
    public BlackJackResponce hit(Table table) {
        throw new BlackJackException("Error newGameState can only call newGame() method!");

    }

    @Override
    public BlackJackResponce stand(Table table) {
        throw new BlackJackException("Error newGameState can only call newGame() method!");

    }

    @Override
    public double endGame(BlackJackResponce blackJackResponce, double bet) {
        throw new BlackJackException("Error newGameState can only call newGame() method!");

    }

}
