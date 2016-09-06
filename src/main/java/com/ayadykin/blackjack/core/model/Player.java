package com.ayadykin.blackjack.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey Yadykin on 22.02.2016 ð.
 */

public class Player {

    private long id;
    private double cash;
    private List<Card> cards = new ArrayList<>();
    private int points;
    private boolean diler = false;
    
    public Player(){
    	
    }
    public Player(long id, double cash) {
        this.cash = cash;
        this.id = id;
    }

    public Player(boolean diler) {
        this.diler = diler;
    }

    public boolean addCard(Card card) {
        return cards.add(card);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public boolean isDiler() {
        return diler;
    }

    public void setDiler(boolean diler) {
        this.diler = diler;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

}
