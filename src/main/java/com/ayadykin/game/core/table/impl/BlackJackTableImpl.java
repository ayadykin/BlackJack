package com.ayadykin.game.core.table.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;

import com.ayadykin.game.blackjack.core.BlackJackCore;
import com.ayadykin.game.core.actions.GameStatus;
import com.ayadykin.game.core.cards.Card;
import com.ayadykin.game.core.cards.CardDeck;
import com.ayadykin.game.core.model.Dealer;
import com.ayadykin.game.core.model.Player;
import com.ayadykin.game.core.table.BlackJackTable;
import com.ayadykin.game.core.table.TableType;

@Dependent
@TableType(TableType.Type.BLACK_JACK)
public class BlackJackTableImpl implements BlackJackTable, Serializable {

    private long id;
    private Dealer dealer;
    private List<Player> players = new ArrayList<>();
    private CardDeck cardDeck;
    private GameStatus gameStatus = GameStatus.SET_BET_PREPARE_GAME;
    @EJB
    private BlackJackCore blackJackCore;

    @Override
    public boolean playerStep(Player player) {
        return blackJackCore.playerStep(player, getCard());
    }

    @Override
    public boolean dealerStep() {

        while (blackJackCore.dealerStep(dealer, getCard()));

        // Game result
        blackJackCore.getGameResult(this);
        // Count bet
        blackJackCore.countBet(this);

        return true;
    }

    @Override
    public void countPoints() {
        for (Player player : players) {
            player.setPoints(blackJackCore.countPoints(player));
        }
        dealer.setPoints(blackJackCore.countPoints(dealer));
    }

    @Override
    public void init(Player player) {
        this.id = player.getId();
        dealer = new Dealer(0);
        if (!players.contains(player)) {
            players.add(player);
        }
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
    public void addPlayer(Player player) {
        players.add(player);
    }

    @Override
    public void removePlayer(Player player) {
        players.remove(player);
    }

    @Override
    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;

    }

    @Override
    public GameStatus getGameStatus() {
        return gameStatus;
    }

}
