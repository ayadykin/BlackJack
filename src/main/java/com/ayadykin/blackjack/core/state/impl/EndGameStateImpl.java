package com.ayadykin.blackjack.core.state.impl;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import com.ayadykin.blackjack.actions.BlackJackResponce;
import com.ayadykin.blackjack.core.GameFlow;
import com.ayadykin.blackjack.core.blackjack.BlackJackCore;
import com.ayadykin.blackjack.core.state.GameState;
import com.ayadykin.blackjack.core.state.qualifiers.EndGameState;
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

    public EndGameStateImpl() {

    }

    @Inject
    public EndGameStateImpl(GameFlow gameFlow) {
        this.gameFlow = gameFlow;
    }

    @Override
    public void newGame(Table table) {
        throw new BlackJackException("Error you can't call newGame() method, endGameState can call endGame method only!");
    }

    @Override
    public void startGame(Table table) {
        throw new BlackJackException("Error you can't call startGame() method, endGameState can call endGame method only!");
    }

    @Override
    public BlackJackResponce hit(Table table) {
        throw new BlackJackException("Error you can't call hit() method, endGameState can call endGame method only!");

    }

    @Override
    public BlackJackResponce stand(Table table) {
        throw new BlackJackException("Error you can't call stand() method, endGameState can call endGame method only!");
    }

    @Override
    public double endGame(BlackJackResponce blackJackResponce, double bet) {
        gameFlow.setState(gameFlow.getInitGameState());
        return blackJackCore.countBet(blackJackResponce, bet);
    }

}
