package com.ayadykin.blackjack.core.blackjack;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.ayadykin.blackjack.actions.BlackJackResponce;
import com.ayadykin.blackjack.actions.PlayerResult;
import com.ayadykin.blackjack.core.cards.Card;
import com.ayadykin.blackjack.core.model.Dealer;
import com.ayadykin.blackjack.core.model.GamePoints;
import com.ayadykin.blackjack.core.model.Player;
import com.ayadykin.blackjack.core.table.Table;

/**
 * Created by Andrey Yadykin on 22.02.2016.
 */

@Stateless
public class BlackJackCore implements Serializable {

    @EJB
    private BlackJackRules blackJackRules;

    public void checkBlackJack(Player player, Player dealer) {

        int playerPoints = countPoints(player);
        int dealerPoints = countPoints(dealer);
        if (blackJackRules.isBlackJack(playerPoints)) {
            dealerOpenHiddenCard(dealer);
            dealerPoints = countPoints(dealer);
            if (blackJackRules.isBlackJack(dealerPoints)) {
                //return BlackJackResponce.NEXT_STEP;
            } else {
                //return BlackJackResponce.NEXT_STEP;
            }
        }
        //return BlackJackResponce.NEXT_STEP;

    }

    public boolean playerStep(Player player, Card card) {
        player.addCard(card);
        int points = countPoints(player);
        if (blackJackRules.isBust(points)) {
            player.setPlayerResult(PlayerResult.BUST);
            return false;
        }
        return true;
    }

    public boolean dealerStep(Dealer dealer, Card card) {
        dealerOpenHiddenCard(dealer);
        int points = countPoints(dealer);
        if (blackJackRules.dealerStep(points)) {
            dealer.addCard(card);
            points = countPoints(dealer);
            if (blackJackRules.isBust(points)) {
                dealer.setPlayerResult(PlayerResult.BUST);
                return false;
            } else if (blackJackRules.dealerStep(points)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void getGameResult(Table table) {
        int dealerPoints = table.getDealer().getPoints();

        for (Player player : table.getPlayers()) {
            int playerPoints = player.getPoints();
            if (playerPoints == dealerPoints) {
                player.setPlayerResult(PlayerResult.PUSH);
            } else if (playerPoints > dealerPoints) {
                player.setPlayerResult(PlayerResult.WIN);
            } else {
                player.setPlayerResult(PlayerResult.LOSE);
            }
        }
    }

    public void countBet(Table table) {
        blackJackRules.countBet(table);
    }

    private void dealerOpenHiddenCard(Dealer dealer) {
        dealer.getCards().get(1).setHidden(false);
    }

    public int countPoints(GamePoints player) {
        int points = 0;

        for (Card card : player.getCards()) {
            points += card.getNominal().getPoints();
        }
        player.setPoints(points);
        return points;
    }
}
