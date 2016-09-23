package com.ayadykin.game.rest.dto;

import java.io.Serializable;

import com.ayadykin.game.blackjack.actions.BlackJackActions;

/**
 * Created by Andrey Yadykin on 14 бер. 2016 р.
 */

public class PlayerActionDto implements Serializable{
    private BlackJackActions blackJackAction;

    public BlackJackActions getBlackJackAction() {
        return blackJackAction;
    }

    public void setBlackJackAction(BlackJackActions blackJackAction) {
        this.blackJackAction = blackJackAction;
    }
}
