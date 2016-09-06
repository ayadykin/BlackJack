package com.ayadykin.blackjack.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.ayadykin.blackjack.core.model.Card;
import com.ayadykin.blackjack.core.model.CardDeck;
import com.ayadykin.blackjack.core.model.Player;
import com.ayadykin.blackjack.exceptions.BlackJackException;

/**
 * Created by Andrey Yadykin on 22.02.2016.
 */
@Named
@SessionScoped
public class Table implements Serializable {

    private double bank = 0;
    private List<Player> players = new ArrayList<>();
    private CardDeck cardDeck = new CardDeck();

    public Table() {

    }

    public void init(long id, double cash) {
        players.add(new Player(id, cash));
        players.add(new Player(true));
    }

    public void resetGame() {
        bank = 0;
        cardDeck = new CardDeck();
        for (Player player : players) {
            player.getCards().clear();
        }
    }

    public void setBet(double bet) {
        bank += bet;
    }

    public double getBet() {
        return bank;
    }

    public CardDeck getCardDeck() {
        return cardDeck;
    }
    
    public Card getCard() {
        return cardDeck.getCard();
    }
    
    public List<Player> getPlayers() {
        return players;
    }

    public Player getDealer() throws BlackJackException {
        for (Player player : players) {
            if (player.isDiler()) {
                return player;
            }
        }
        throw new BlackJackException("No dealer");
    }

    public Player getPlayer() throws BlackJackException {
        for (Player player : players) {
            if (!player.isDiler()) {
                return player;
            }
        }
        throw new BlackJackException("No player");
    }

}
