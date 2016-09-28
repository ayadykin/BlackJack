package com.ayadykin.game.core.state.impl;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import com.ayadykin.game.blackjack.core.BlackJackPlayerFlow;
import com.ayadykin.game.blackjack.exceptions.BlackJackException;
import com.ayadykin.game.core.model.Player;
import com.ayadykin.game.core.state.GameState;
import com.ayadykin.game.core.state.qualifiers.SetBetState;
import com.ayadykin.game.core.table.BlackJackTable;

/**
 * Created by Yadykin Andrii Sep 8, 2016
 *
 */

@SetBetState
@SessionScoped
public class SetBetStateImpl implements GameState, Serializable {

    private BlackJackPlayerFlow gameFlow;

    public SetBetStateImpl() {

    }

    @Inject
    public SetBetStateImpl(BlackJackPlayerFlow gameFlow) {
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
