package com.ayadykin.blackjack.core.model;

import java.util.List;

import com.ayadykin.blackjack.core.cards.Card;

/**
 * Created by Yadykin Andrii Sep 15, 2016
 *
 */

public interface GamePoints {
    List<Card> getCards();
    
    void setPoints(int points);
}

