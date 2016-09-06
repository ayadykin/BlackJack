package com.ayadykin.blackjack.core;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.ayadykin.blackjack.actions.GameStatus;

/**
 * Created by Andrey Yadykin on 15 бер. 2016 р.
 */
@Named
@SessionScoped
public class GameFlow implements Serializable {
    private GameStatus gameStatus = GameStatus.NEW;

    public void setGameStatus(GameStatus gameAction) {
        this.gameStatus = gameAction;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

}
