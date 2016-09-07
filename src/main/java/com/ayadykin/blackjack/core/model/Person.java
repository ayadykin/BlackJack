package com.ayadykin.blackjack.core.model;

import java.util.List;

import com.ayadykin.blackjack.core.cards.Card;

public interface Person {
	List<Card> getCards();
	
	boolean addCard(Card card);
	
	void setPoints(int points);
}
