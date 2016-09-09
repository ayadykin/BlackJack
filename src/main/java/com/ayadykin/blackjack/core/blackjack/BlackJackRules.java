package com.ayadykin.blackjack.core.blackjack;

import javax.ejb.Stateless;

import com.ayadykin.blackjack.actions.BlackJackResponce;

/**
 * Created by Yadykin Andrii Sep 8, 2016
 *
 */

@Stateless
public class BlackJackRules {
    private static final int DEALER_MAX = 17;
    private static final int BLACK_JACK = 21;
    private static final double BLACK_JACK_COEFFICIENT = 1.5;

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

    public double countBet(BlackJackResponce blackJackResponce, double bet) {

        switch (blackJackResponce) {
        case PUSH:
            bet = 0;
            break;
        case LOSE:
        case YOU_BUST:
            bet = -bet;
            break;
        case BLACK_JACK:
            bet += bet * BLACK_JACK_COEFFICIENT;
            break;
        case WIN:
        case DEALER_BUST:
            // bet = +bet;
            break;
        default:
            break;
        }
        return bet;

    }
}
