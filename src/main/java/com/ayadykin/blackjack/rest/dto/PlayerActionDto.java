package com.ayadykin.blackjack.rest.dto;

import java.io.Serializable;

import com.ayadykin.blackjack.actions.BlackJackActions;

/**
 * Created by Andrey Yadykin on 14 ���. 2016 �.
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
