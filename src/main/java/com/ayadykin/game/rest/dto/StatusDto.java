package com.ayadykin.game.rest.dto;

import java.io.Serializable;

import com.ayadykin.game.actions.PlayerStatus;
import com.ayadykin.game.core.actions.GameStatus;

/**
 * Created by Yadykin Andrii Sep 23, 2016
 *
 */

public class StatusDto implements Serializable {

    private GameStatus gameStatus;
    private PlayerStatus playerStatus;

    public StatusDto() {

    }

    public StatusDto(GameStatus gameStatus, PlayerStatus playerStatus) {
        this.gameStatus = gameStatus;
        this.playerStatus = playerStatus;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }

    public void setPlayerStatus(PlayerStatus playerStatus) {
        this.playerStatus = playerStatus;
    }

}
