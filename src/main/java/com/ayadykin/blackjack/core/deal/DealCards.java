package com.ayadykin.blackjack.core.deal;

import java.util.List;

import com.ayadykin.blackjack.core.cards.CardDeck;
import com.ayadykin.blackjack.core.model.Person;

public interface DealCards {
    void dealCards(List<Person> players, CardDeck cardDeck);
}
