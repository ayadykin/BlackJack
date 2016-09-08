package com.ayadykin.blackjack.core.state.impl;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ayadykin.blackjack.actions.BlackJackResponce;
import com.ayadykin.blackjack.core.GameFlow;
import com.ayadykin.blackjack.core.model.Player;
import com.ayadykin.blackjack.core.state.GameState;
import com.ayadykin.blackjack.core.table.Table;
import com.ayadykin.blackjack.exceptions.BlackJackException;

/**
 * Created by Yadykin Andrii Sep 8, 2016
 *
 */

@Named("newGameState")
@SessionScoped
public class NewGame implements GameState, Serializable {

    private GameFlow gameFlow;

    @Inject
    public NewGame(GameFlow gameFlow) {
        this.gameFlow = gameFlow;

    }

    @Override
    public void newGame(Table table) {
        table.init(1, 1000);
        gameFlow.setState(gameFlow.getInitGameState());
    }

    @Override
    public void initGame(Table table) {
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
    public void endGame(BlackJackResponce blackJackResponce, Player player) {
        throw new BlackJackException("Error newGameState can only call newGame() method!");

    }

}
