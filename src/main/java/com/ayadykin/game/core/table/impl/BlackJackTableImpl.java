package com.ayadykin.game.core.table.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;

import com.ayadykin.game.core.actions.GameStatus;
import com.ayadykin.game.core.model.Dealer;
import com.ayadykin.game.core.model.Player;
import com.ayadykin.game.core.table.BlackJackTable;
import com.ayadykin.game.core.table.TableType;

@Dependent
@TableType(TableType.Type.BLACK_JACK)
public class BlackJackTableImpl implements BlackJackTable, Serializable {

	private long id;
	private Dealer dealer;
	private List<Player> players = new ArrayList<>();

	private GameStatus gameStatus = GameStatus.SET_BET_PREPARE_GAME;

	@Override
	public void init(Player player) {
		this.id = player.getId();
		dealer = new Dealer();
		if (!players.contains(player)) {
			players.add(player);
		}
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public List<Player> getPlayers() {
		return players;
	}

	@Override
	public Dealer getDealer() {
		return dealer;
	}

	@Override
	public void addPlayer(Player player) {
		players.add(player);
	}

	@Override
	public void removePlayer(Player player) {
		players.remove(player);
	}

	@Override
	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	@Override
	public GameStatus getGameStatus() {
		return gameStatus;
	}

}
