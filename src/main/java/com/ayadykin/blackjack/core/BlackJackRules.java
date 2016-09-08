package com.ayadykin.blackjack.core;

import javax.ejb.Stateless;

/**
 * Created by Yadykin Andrii Sep 8, 2016
 *
 */

@Stateless
public class BlackJackRules {
    private static final int DEALER_MAX = 17;
    private static final int BLACK_JACK = 21;

    public boolean dealerStep(int points) {
        if (points < DEALER_MAX) {
            return true;
        }
        return false;
    }

    public boolean isBlackJack(int points) {
        if (points == BLACK_JACK) {
            return true;
        }
        return false;
    }

    public boolean isBust(int points) {
        if (points > BLACK_JACK) {
            return true;
        }
        return false;
    }
}
