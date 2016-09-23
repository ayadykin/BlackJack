package com.ayadykin.game.core.cards;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;

import com.ayadykin.game.core.cards.Card.CardSuit;
import com.ayadykin.game.core.cards.Card.Nominal;

/**
 * Created by Andrey Yadykin on 22.02.2016.
 */

public class CardDeck extends LinkedList<Card> implements Serializable{

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
