package com.ayadykin.game.core.state.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import com.ayadykin.game.actions.PlayerStatus;
import com.ayadykin.game.blackjack.core.BlackJackPlayerFlow;
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

	private BlackJackPlayerFlow gameFlow;

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
	public StartGameStateImpl(BlackJackPlayerFlow gameFlow) {
		this.gameFlow = gameFlow;
	}

	@Override
	public void setBet(double bet) {
		throw new BlackJackException(
				"Error you can't call setBet() method, startGameState can call hit() or stand methods!");

	}

	@Override
	public void hit(Player player, BlackJackTable table) {

		if (!table.getDealer().playerStep(player)) {

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
		player.setPlayerStatus(PlayerStatus.WAIT);
		Player nextPlayer = getNextPlayer(player, table);
		if (Objects.nonNull(nextPlayer)) {
			nextPlayer.setPlayerStatus(PlayerStatus.STEP);
			gameFlow.setState(gameFlow.getSetBetState());
			return true;
		} else {
			return false;
		}
	}

	private Player getNextPlayer(Player player, BlackJackTable table) {
		List<Player> players = table.getPlayers();
		int index = players.lastIndexOf(player);
		if (players.size() <= index + 1) {
			return null;
		}

		return players.get(index + 1);
	}

	private void dealerStep(BlackJackTable table) {
		// dealer step
		table.getDealer().dealerStep(table.getPlayers());
		endGameTimer.setEndGameTimer(table);
		gameFlow.setState(gameFlow.getSetBetState());
	}

}
