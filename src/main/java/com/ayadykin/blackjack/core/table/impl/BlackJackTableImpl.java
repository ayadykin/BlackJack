package com.ayadykin.blackjack.core.table.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;

import com.ayadykin.blackjack.core.BlackJackFlow;
import com.ayadykin.blackjack.core.cards.Card;
import com.ayadykin.blackjack.core.cards.CardDeck;
import com.ayadykin.blackjack.core.model.Dealer;
import com.ayadykin.blackjack.core.model.Player;
import com.ayadykin.blackjack.core.table.BlackJackTable;
import com.ayadykin.blackjack.core.table.TableType;

@Dependent
@TableType(TableType.Type.BLACK_JACK)
public class BlackJackTableImpl implements BlackJackTable, Serializable {

    private long id;
    private Dealer dealer;
    private List<Player> players = new ArrayList<>();
    private CardDeck cardDeck;
    @EJB
    private BlackJackFlow blackJackFlow;
    
    
    @Override
    public void init(Player player) {
        this.id = player.getId();
        dealer = new Dealer(0);
        if(!players.contains(player)){
            players.add(player);
        }       
    }
    
    public void startGame() {
        blackJackFlow.startGame(this);
    }

    public void endGame() {
        blackJackFlow.endGame(this);
    }
    
    @Override
    public long getId() {
        return id;
    }

    @Override
    public Player getNextPlayer(Player player) {
        int index = players.lastIndexOf(player);
        if (players.size() <= index + 1) {
            return null;
        }

        return players.get(index + 1);
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
        return dealer;
    }

    @Override
    public Player getPlayer(long id) {
        return players.stream().filter(p -> p.getId() == id).findFirst().get();
    }

    @Override
    public void addPlayer(Player player) {
        players.add(player);
    }

    public BlackJackFlow getBlackJackFlow() {
        return blackJackFlow;
    }
    
}
