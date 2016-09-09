package com.ayadykin.blackjack.core.table.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;

import com.ayadykin.blackjack.core.cards.Card;
import com.ayadykin.blackjack.core.cards.CardDeck;
import com.ayadykin.blackjack.core.model.Dealer;
import com.ayadykin.blackjack.core.model.Player;
import com.ayadykin.blackjack.core.table.Table;
import com.ayadykin.blackjack.core.table.qualifiers.BlackJackTable;

@BlackJackTable
@SessionScoped
public class BlackJackTableImpl implements Table, Serializable {

    private long id;
    private List<Player> players = new ArrayList<>();
    private CardDeck cardDeck;

    @Override
    public void init(long id) {
        this.id = id;
        players.add(new Dealer());
    }
    
    @Override
    public long getId() {
        return id;
    }

    @Override
    public void newCardDeck() {
        cardDeck = new CardDeck();
    }

    @Override
    public CardDeck getCardDeck() {
        return cardDeck;
    }

    @Override
    public Card getCard() {
        return cardDeck.getCard().setHidden(false);
    }

    @Override
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public Dealer getDealer() {
        return (Dealer) players.get(0);
    }

    @Override
    public Player getPlayer() {
        return players.get(1);
    }

    @Override
    public void addPlayer(Player player) {
        players.add(player);
    }
}
