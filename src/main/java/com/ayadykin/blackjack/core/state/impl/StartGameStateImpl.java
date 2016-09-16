package com.ayadykin.blackjack.core.state.impl;

import java.io.Serializable;
import java.util.Objects;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import com.ayadykin.blackjack.actions.PlayerStatus;
import com.ayadykin.blackjack.core.GameFlow;
import com.ayadykin.blackjack.core.blackjack.BlackJackCore;
import com.ayadykin.blackjack.core.model.Dealer;
import com.ayadykin.blackjack.core.model.Player;
import com.ayadykin.blackjack.core.state.GameState;
import com.ayadykin.blackjack.core.state.qualifiers.StartGameState;
import com.ayadykin.blackjack.core.table.BlackJackTable;
import com.ayadykin.blackjack.core.timer.EndGameTimer;
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
    public StartGameStateImpl(GameFlow gameFlow) {
        this.gameFlow = gameFlow;
    }

    @Override
    public void setBet(double bet) {
        throw new BlackJackException("Error you can't call setBet() method, startGameState can call hit() or stand methods!");

    }

    @Override
    public void hit(Player player, BlackJackTable table) {
        if (!blackJackCore.playerStep(player, table.getCard())) {
            player.setPlayerStatus(PlayerStatus.WAIT);
            // next player or end
            Player nextPlayer = table.getNextPlayer(player);
            if (Objects.nonNull(nextPlayer)) {
                nextPlayer.setPlayerStatus(PlayerStatus.STEP);
            } else {
                dealerStep(table);
            }
        }
    }

    @Override
    public void stand(Player player, BlackJackTable table) {

        // Status
        Player nextPlayer = table.getNextPlayer(player);
        player.setPlayerStatus(PlayerStatus.WAIT);

        if (Objects.nonNull(nextPlayer)) {
            // next player
            nextPlayer.setPlayerStatus(PlayerStatus.STEP);
        } else {
            // dealer step
            dealerStep(table);

            // Game result
            blackJackCore.getGameResult(table);
            // Count bet
            blackJackCore.countBet(table);
        }
    }

    private void dealerStep(BlackJackTable table) {
        Dealer dealer = table.getDealer();
        // dealer step
        while (blackJackCore.dealerStep(dealer, table.getCard())) {
        }
        endGameTimer.setEndGameTimer(table);
    }

}
