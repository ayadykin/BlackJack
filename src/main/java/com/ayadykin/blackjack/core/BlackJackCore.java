package com.ayadykin.blackjack.core;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.List;

import javax.ejb.Stateless;

import com.ayadykin.blackjack.actions.BlackJackResponce;
import com.ayadykin.blackjack.core.deal.impl.BlackJackDealStrategy;
import com.ayadykin.blackjack.core.model.Card;
import com.ayadykin.blackjack.core.model.CardDeck;
import com.ayadykin.blackjack.core.model.Dealer;
import com.ayadykin.blackjack.core.model.Player;

/**
 * Created by Andrey Yadykin on 22.02.2016.
 */
@Stateless
public class BlackJackCore {
    
    private static final int BLACK_JACK = 21;


    public BlackJackResponce checkBlackJack(List<Player> players) {

        boolean blackJack = false;
        // Count points and check black jack
        for (Player player : players) {
            if (player instanceof Dealer && blackJack) {
                dealerOpenHiddenCard(player);
                return BlackJackResponce.BLACK_JACK;
            }
            if (isBlackJack(player)) {
                blackJack = true;
            }
        }
        return BlackJackResponce.NEXT_MOVE;
    }

    public BlackJackResponce checkPlayerStep(Player player) {

        if (isBust(player)) {
            return BlackJackResponce.LOSE;
        }

        return BlackJackResponce.NEXT_MOVE;
    }

    public BlackJackResponce dealerStep(Player player, Card card) {

        card.setHidden(false);

        dealerOpenHiddenCard(player);

        int points = countPoints(player);
        while (points < 17) {
            player.getCards().add(card);
            points = countPoints(player);
        }
        if (isBust(player)) {
            return BlackJackResponce.WIN;
        }

        return BlackJackResponce.DEALER_STAND;
    }

    private void dealerOpenHiddenCard(Player player) {
        player.getCards().get(1).setHidden(false);
        countPoints(player);
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

    private int countPoints(Player player) {
        int points = 0;
        for (Card card : player.getCards()) {
            points += card.getNominal().getPoints();
        }
        player.setPoints(points);
        System.out.println(points);
        return points;
    }

    private boolean isBlackJack(Player player) {
        int points = countPoints(player);
        if (points == BLACK_JACK) {
            return true;
        }
        return false;
    }

    private boolean isBust(Player player) {
        int points = countPoints(player);
        if (points > BLACK_JACK) {
            return true;
        }
        return false;
    }
}
