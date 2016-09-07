package com.ayadykin.blackjack.core.model;

import java.util.ArrayList;
import java.util.List;

import com.ayadykin.blackjack.core.cards.Card;

/**
 * Created by Yadykin Andrii Sep 7, 2016
 *
 */

public class Dealer implements Person{
	private List<Card> cards = new ArrayList<>();
	private int points;

	@Override
	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public boolean addCard(Card card) {
		return cards.add(card);
	}

}
