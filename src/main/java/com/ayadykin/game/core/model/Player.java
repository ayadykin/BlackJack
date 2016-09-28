package com.ayadykin.game.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ayadykin.game.actions.PlayerStatus;
import com.ayadykin.game.blackjack.actions.PlayerResult;
import com.ayadykin.game.core.cards.Card;

/**
 * Created by Andrey Yadykin on 22.02.2016 ð.
 */

public class Player implements Serializable {

	private String name;
	private double cash;
	private double bet;
	private long id;
	private List<Card> cards = new ArrayList<>();
	private int points;
	private PlayerResult playerResult = PlayerResult.NONE;
	private PlayerStatus playerStatus = PlayerStatus.WAIT;

	public Player() {

	}

	public Player(long id, String name, double cash) {
		this.id = id;
		this.name = name;
		this.cash = cash;
	}

	public long getId() {
		return id;
	}

	public double getBet() {
		return bet;
	}

	public void setBet(double bet) {
		this.bet = bet;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	public void addBetToCash(double bet) {
		this.cash += bet;
	}

	public PlayerStatus getPlayerStatus() {
		return playerStatus;
	}

	public void setPlayerStatus(PlayerStatus playerStatus) {
		this.playerStatus = playerStatus;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public PlayerResult getPlayerResult() {
		return playerResult;
	}

	public void setPlayerResult(PlayerResult playerResult) {
		this.playerResult = playerResult;
	}

}
