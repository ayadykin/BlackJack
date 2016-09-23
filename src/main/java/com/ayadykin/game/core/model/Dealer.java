package com.ayadykin.game.core.model;

import java.util.ArrayList;
import java.util.List;

import com.ayadykin.game.blackjack.actions.PlayerResult;
import com.ayadykin.game.core.cards.Card;

/**
 * Created by Yadykin Andrii Sep 7, 2016
 *
 */

public class Dealer implements GamePoints {

    private long id;
    private List<Card> cards = new ArrayList<>();
    private int points;
    private PlayerResult playerResult = PlayerResult.NONE;

    public Dealer() {

    }

    public Dealer(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean addCard(Card card) {
        return cards.add(card);
    }

    public PlayerResult getPlayerResult() {
        return playerResult;
    }

    public void setPlayerResult(PlayerResult playerResult) {
        this.playerResult = playerResult;
    }

}
