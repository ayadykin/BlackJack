package com.ayadykin.blackjack.core.model;

import java.util.ArrayList;
import java.util.List;

import com.ayadykin.blackjack.core.cards.Card;

/**
 * Created by Andrey Yadykin on 22.02.2016 ð.
 */

public class Player implements Person{

    private long id;
    private double cash;
    private List<Card> cards = new ArrayList<>();
    private int points;
    
    public Player(){
    	
    }
    public Player(long id, double cash) {
        this.cash = cash;
        this.id = id;
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

    @Override
    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
    
    public void clearPoints() {
         points = 0;
    }
    
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

}
