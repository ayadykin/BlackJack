package com.ayadykin.blackjack.core.blackjack;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.ayadykin.blackjack.actions.BlackJackResponce;
import com.ayadykin.blackjack.core.cards.Card;
import com.ayadykin.blackjack.core.model.Dealer;
import com.ayadykin.blackjack.core.model.Player;

/**
 * Created by Andrey Yadykin on 22.02.2016.
 */

@Stateless
public class BlackJackCore implements Serializable{

    @EJB
    private BlackJackRules blackJackRules;

    public BlackJackResponce checkBlackJack(Player player, Dealer dealer) {

        int playerPoints = countPoints(player);
        int dealerPoints = countPoints(dealer);
        if (blackJackRules.isBlackJack(playerPoints)) {
            dealerOpenHiddenCard(dealer);
            dealerPoints = countPoints(dealer);
            if (blackJackRules.isBlackJack(dealerPoints)) {
                return BlackJackResponce.PUSH;
            } else {
                return BlackJackResponce.BLACK_JACK;
            }
        }
        return BlackJackResponce.NEXT_STEP;

    }

    public BlackJackResponce playerStep(Player player, Card card) {
        player.addCard(card);
        int points = countPoints(player);
        if (blackJackRules.isBust(points)) {
            return BlackJackResponce.YOU_BUST;
        }
        return BlackJackResponce.NEXT_STEP;
    }

    public BlackJackResponce dealerStep(Dealer dealer, Card card) {
        dealerOpenHiddenCard(dealer);
        int points = countPoints(dealer);
        if (blackJackRules.dealerStep(points)) {
            dealer.addCard(card);
            points = countPoints(dealer);
            if (blackJackRules.isBust(points)) {
                return BlackJackResponce.DEALER_BUST;
            } else if (blackJackRules.dealerStep(points)) {
                return BlackJackResponce.NEXT_STEP;
            } else {
                return BlackJackResponce.DEALER_STAND;
            }
        } else {
            return BlackJackResponce.DEALER_STAND;
        }
    }

    public BlackJackResponce getGameResult(int playerPoints, int dealerPoints) {

        if (playerPoints == dealerPoints) {
            return BlackJackResponce.PUSH;
        } else if (playerPoints > dealerPoints) {
            return BlackJackResponce.WIN;
        } else {
            return BlackJackResponce.LOSE;
        }
    }
    
    public double countBet(BlackJackResponce blackJackResponce, double bet) {
        return blackJackRules.countBet(blackJackResponce, bet);
    }

    private void dealerOpenHiddenCard(Dealer dealer) {
        dealer.getCards().get(1).setHidden(false);
    }

    private int countPoints(Player player) {
        int points = 0;

        for (Card card : player.getCards()) {
            points += card.getNominal().getPoints();
        }
        player.setPoints(points);
        return points;
    }
}
