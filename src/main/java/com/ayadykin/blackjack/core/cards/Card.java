package com.ayadykin.blackjack.core.cards;

/**
 * Created by Andrey Yadykin on 22.02.2016 ð.
 */

public class Card {
    public enum CardSuit {
        spades, clubs, hearts, diamonds
    };

    public enum Nominal {
        ACE(10), KING(10), QUEEN(10), KNAVE(10), TEN(10), NINE(9), EIGHT(8), SEVEN(7), SIX(6), FIVE(5), FOUR(4), THREE(3), TWO(2), HIDDEN(0);

        private int points;

        Nominal(int points) {
            this.points = points;
        }

        public int getPoints() {
            return points;
        }
    };

    private CardSuit cardSuit;
    private Nominal nominal;
    private boolean hidden = true;

    public Card(CardSuit cardSuit, Nominal nominal) {
        this.cardSuit = cardSuit;
        this.nominal = nominal;
    }

    public CardSuit getCardSuit() {
        return cardSuit;
    }

    public void setCardSuit(CardSuit cardSuit) {
        this.cardSuit = cardSuit;
    }

    public Nominal getNominal() {
        if (hidden) {
            return Nominal.HIDDEN;
        }
        return nominal;
    }

    public void setNominal(Nominal nominal) {
        this.nominal = nominal;
    }

    public boolean isHidden() {
        return hidden;
    }

    public Card setHidden(boolean hidden) {
        this.hidden = hidden;
        return this;
    }

}
