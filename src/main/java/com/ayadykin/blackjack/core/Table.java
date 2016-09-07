package com.ayadykin.blackjack.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.ayadykin.blackjack.core.cards.Card;
import com.ayadykin.blackjack.core.cards.CardDeck;
import com.ayadykin.blackjack.core.model.Dealer;
import com.ayadykin.blackjack.core.model.Person;
import com.ayadykin.blackjack.core.model.Player;
import com.ayadykin.blackjack.exceptions.BlackJackException;

/**
 * Created by Andrey Yadykin on 22.02.2016.
 */
@Named
@SessionScoped
public class Table implements Serializable {

	private double bank = 0;
	private List<Person> players = new ArrayList<>();
	private CardDeck cardDeck = new CardDeck();

	public Table() {

	}

	public void init(long id, double cash) {
		players.add(new Dealer());
		players.add(new Player(id, cash));
	}

	public void resetGame() {
		bank = 0;
		cardDeck = new CardDeck();
		for (Person player : players) {
			player.getCards().clear();
			player.setPoints(0);
		}
	}

	public void setBet(double bet) {
		bank += bet;
	}

	public double getBet() {
		return bank;
	}

	public CardDeck getCardDeck() {
		return cardDeck;
	}

	public Card getCard() {
		return cardDeck.getCard().setHidden(false);
	}

	public List<Person> getPlayers() {
		return players;
	}

	public Dealer getDealer() {
		return (Dealer) players.get(0);
	}

	public Player getPlayer() throws BlackJackException {
		return (Player) players.get(1);
		//throw new BlackJackException("No player");
	}

}
