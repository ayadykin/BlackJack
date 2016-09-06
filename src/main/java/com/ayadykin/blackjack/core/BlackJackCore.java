package com.ayadykin.blackjack.core;

import java.util.List;

import javax.ejb.Stateless;

import com.ayadykin.blackjack.actions.BlackJackResponce;
import com.ayadykin.blackjack.core.model.Card;
import com.ayadykin.blackjack.core.model.CardDeck;
import com.ayadykin.blackjack.core.model.Player;

/**
 * Created by Andrey Yadykin on 22.02.2016.
 */
@Stateless
public class BlackJackCore {

    private static final int DEAL_CARDS = 2;
    private static final int BLACK_JACK = 21;

    public void dealCards(List<Player> players, CardDeck cardDeck) {
        for (Player player : players) {
            for (int i = 0; i < BlackJackCore.DEAL_CARDS; i++) {
                Card card = cardDeck.getCard().setHidden(false);
                if (player.isDiler() && i == 1) {
                    card.setHidden(true);
                }
                player.addCard(card);
            }
        }
    }

    public BlackJackResponce checkBlackJack(List<Player> players) {

        boolean blackJack = false;
        // Count points and check black jack
        for (Player player : players) {
            if (player.isDiler() && blackJack) {
                dealerOpenHiddenCard(player);
                return BlackJackResponce.BLACK_JACK;
            }
            if (isBlackJack(player)) {
                blackJack = true;
            }
        }
        return BlackJackResponce.NEXT_MOVE;
    }

    public BlackJackResponce playerGetCard(Player player, Card card) {
        card.setHidden(false);

        player.getCards().add(card);
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
