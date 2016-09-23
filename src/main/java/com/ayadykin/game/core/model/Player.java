package com.ayadykin.game.core.model;

import java.io.Serializable;

import com.ayadykin.game.actions.PlayerStatus;

/**
 * Created by Andrey Yadykin on 22.02.2016 ð.
 */

public class Player extends Dealer implements Serializable, GamePoints {

    private String name;
    private double cash;
    private double bet;
    private PlayerStatus playerStatus = PlayerStatus.WAIT;

    public Player() {

    }

    public Player(long id, String name, double cash) {
        super(id);
        this.name = name;
        this.cash = cash;
    }

    public double getBet() {
        return bet;
    }

    public void setBet(double bet) {
        this.bet = bet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public void addBetToCash(double bet) {
        this.cash += bet;
    }

    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }

    public void setPlayerStatus(PlayerStatus playerStatus) {
        this.playerStatus = playerStatus;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(bet);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(cash);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((playerStatus == null) ? 0 : playerStatus.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object player) {
        if (getId() == ((Dealer) player).getId()) {
            return true;
        } else {
            return false;
        }
    }

}
