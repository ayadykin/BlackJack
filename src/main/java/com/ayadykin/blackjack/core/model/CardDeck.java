package com.ayadykin.blackjack.core.model;

import java.util.Collections;
import java.util.LinkedList;

import com.ayadykin.blackjack.core.model.Card.CardSuit;
import com.ayadykin.blackjack.core.model.Card.Nominal;

/**
 * Created by Andrey Yadykin on 22.02.2016.
 */

public class CardDeck extends LinkedList<Card> {

    public CardDeck() {
        for (CardSuit cardSuit : CardSuit.values()) {
            for (Nominal nominal : Nominal.values()) {
                if (!nominal.equals(Nominal.HIDDEN)) {
                    this.add(new Card(cardSuit, nominal));
                }
            }
        }
        Collections.shuffle(this);
    }

    public Card getCard() {
        return this.removeFirst();

    }
}
