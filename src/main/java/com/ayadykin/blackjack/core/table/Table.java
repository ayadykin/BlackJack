package com.ayadykin.blackjack.core.table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.ayadykin.blackjack.core.cards.Card;
import com.ayadykin.blackjack.core.cards.CardDeck;
import com.ayadykin.blackjack.core.model.Dealer;
import com.ayadykin.blackjack.core.model.Player;

/**
 * Created by Andrey Yadykin on 22.02.2016.
 */
@Named
@SessionScoped
public class Table implements Serializable {

    private List<Player> players = new ArrayList<>();
    private CardDeck cardDeck;

    public Table() {

    }

    public void init(long id, double cash) {
        players.add(new Dealer());
        players.add(new Player(id, cash));
    }

    public void resetGame() {
        cardDeck = new CardDeck();
        for (Player player : players) {
            player.getCards().clear();
            player.setPoints(0);
        }
    }

    public CardDeck getCardDeck() {
        return cardDeck;
    }

    public Card getCard() {
        return cardDeck.getCard().setHidden(false);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return (Dealer) players.get(0);
    }

    public Player getPlayer() {
        return players.get(1);
        // throw new BlackJackException("No player");
    }

}
