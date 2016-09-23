package com.ayadykin.game.rest.dto;

import java.io.Serializable;

import com.ayadykin.game.actions.GameActions;

/**
 * Created by Yadykin Andrii Sep 15, 2016
 *
 */

public class GameActionDto implements Serializable {
    private GameActions gameActions;

    public GameActions getGameActions() {
        return gameActions;
    }

    public void setGameActions(GameActions gameActions) {
        this.gameActions = gameActions;
    }

}
