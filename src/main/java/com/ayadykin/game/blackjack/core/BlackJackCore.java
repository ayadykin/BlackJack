package com.ayadykin.game.blackjack.core;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.ayadykin.game.blackjack.actions.PlayerResult;
import com.ayadykin.game.core.cards.Card;
import com.ayadykin.game.core.model.Dealer;
import com.ayadykin.game.core.model.GamePoints;
import com.ayadykin.game.core.model.Player;
import com.ayadykin.game.core.table.Table;

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
                // return BlackJackResponce.NEXT_STEP;
            } else {
                // return BlackJackResponce.NEXT_STEP;
            }
        }
        // return BlackJackResponce.NEXT_STEP;

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
        Dealer dealer = table.getDealer();
        int dealerPoints = dealer.getPoints();

        for (Player player : table.getPlayers()) {

            if (player.getPlayerResult() == PlayerResult.BUST) {
                player.setPlayerResult(PlayerResult.LOSE);
            } else if (dealer.getPlayerResult() == PlayerResult.BUST) {
                player.setPlayerResult(PlayerResult.WIN);
            } else {
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
