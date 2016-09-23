package com.ayadykin.game.core.state.impl;

import java.io.Serializable;
import java.util.Objects;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import com.ayadykin.game.actions.PlayerStatus;
import com.ayadykin.game.blackjack.core.BlackJackGameFlow;
import com.ayadykin.game.blackjack.exceptions.BlackJackException;
import com.ayadykin.game.blackjack.timer.EndGameTimer;
import com.ayadykin.game.core.model.Player;
import com.ayadykin.game.core.state.GameState;
import com.ayadykin.game.core.state.qualifiers.StartGameState;
import com.ayadykin.game.core.table.BlackJackTable;

/**
 * Created by Yadykin Andrii Sep 8, 2016
 *
 */

@StartGameState
@SessionScoped
public class StartGameStateImpl implements GameState, Serializable {

    private BlackJackGameFlow gameFlow;

    @EJB
    private EndGameTimer endGameTimer;

    public StartGameStateImpl() {

    }

    /**
     * Inject game flow for change game state
     * 
     * @param gameFlow
     */
    @Inject
    public StartGameStateImpl(BlackJackGameFlow gameFlow) {
        this.gameFlow = gameFlow;
    }

    @Override
    public void setBet(double bet) {
        throw new BlackJackException("Error you can't call setBet() method, startGameState can call hit() or stand methods!");

    }

    @Override
    public void hit(Player player, BlackJackTable table) {

        if (!table.playerStep(player)) {

            // next player or dealer step
            if (!checkNextPlayer(player, table)) {
                dealerStep(table);
            }
        }
    }

    @Override
    public void stand(Player player, BlackJackTable table) {

        // next player or dealer step
        if (!checkNextPlayer(player, table)) {
            dealerStep(table);
        }
    }

    private boolean checkNextPlayer(Player player, BlackJackTable table) {
        Player nextPlayer = table.getNextPlayer(player);
        player.setPlayerStatus(PlayerStatus.WAIT);
        if (Objects.nonNull(nextPlayer)) {
            nextPlayer.setPlayerStatus(PlayerStatus.STEP);
            gameFlow.setState(gameFlow.getSetBetState());
            return true;
        } else {
            return false;
        }
    }

    private void dealerStep(BlackJackTable table) {
        // dealer step
        table.dealerStep();
        endGameTimer.setEndGameTimer(table);
        gameFlow.setState(gameFlow.getSetBetState());
    }

}
