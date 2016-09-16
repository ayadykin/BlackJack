package com.ayadykin.blackjack.core.state.impl;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import com.ayadykin.blackjack.core.GameFlow;
import com.ayadykin.blackjack.core.model.Player;
import com.ayadykin.blackjack.core.state.GameState;
import com.ayadykin.blackjack.core.state.qualifiers.SetBetState;
import com.ayadykin.blackjack.core.table.BlackJackTable;
import com.ayadykin.blackjack.exceptions.BlackJackException;

/**
 * Created by Yadykin Andrii Sep 8, 2016
 *
 */

@SetBetState
@SessionScoped
public class SetBetStateImpl implements GameState, Serializable {

    private GameFlow gameFlow;

    public SetBetStateImpl() {

    }

    @Inject
    public SetBetStateImpl(GameFlow gameFlow) {
        this.gameFlow = gameFlow;
    }

    @Override
    public void setBet(double bet) {
        //todo
        gameFlow.setState(gameFlow.getStartGameState());
    }

    @Override
    public void hit(Player player, BlackJackTable table) {
        throw new BlackJackException("Error hit can only call setBet() method!");

    }

    @Override
    public void stand(Player player, BlackJackTable table) {
        throw new BlackJackException("Error stand can only call setBet() method!");

    }

}
