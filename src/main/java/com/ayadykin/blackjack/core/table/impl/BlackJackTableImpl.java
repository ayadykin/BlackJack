package com.ayadykin.blackjack.core.table.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;

import com.ayadykin.blackjack.core.cards.Card;
import com.ayadykin.blackjack.core.cards.CardDeck;
import com.ayadykin.blackjack.core.model.Dealer;
import com.ayadykin.blackjack.core.model.Player;
import com.ayadykin.blackjack.core.table.BlackJackTable;
import com.ayadykin.blackjack.core.table.Table;

@BlackJackTable
@SessionScoped
public class BlackJackTableImpl implements Table, Serializable{
	
	private List<Player> players = new ArrayList<>();
	private CardDeck cardDeck;

	public void init(long id, double cash) {
		players.add(new Dealer());
		players.add(new Player(id, cash));
	}

	public void newCardDeck() {
		cardDeck = new CardDeck();
	}

	public CardDeck getCardDeck() {
		return cardDeck;
	}

	public Card getCard() {
		return cardDeck.getCard().setHidden(false);
	}

	public List<Player> getPlayers() {
		return players;
	}

	public Dealer getDealer() {
		return (Dealer) players.get(0);
	}

	public Player getPlayer() {
		return players.get(1);
	}
}
