package com.ayadykin.blackjack.core;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import com.ayadykin.blackjack.actions.BlackJackResponce;
import com.ayadykin.blackjack.core.game.EndGame;
import com.ayadykin.blackjack.core.game.StartGame;
import com.ayadykin.blackjack.core.table.BlackJackTable;

/**
 * Created by Yadykin Andrii Sep 16, 2016
 *
 */

@Stateful
public class BlackJackFlow implements Serializable {

    @EJB
    private StartGame startGame;
    @EJB
    private EndGame endGame;

    private BlackJackResponce blackJackResponce = BlackJackResponce.SET_BET;

    public void startGame(BlackJackTable blackJackTable) {
        startGame.startGame(blackJackTable);
        blackJackResponce = BlackJackResponce.GAME_START;
    }

    public void endGame(BlackJackTable blackJackTable) {
        endGame.endGame(blackJackTable);
        blackJackResponce = BlackJackResponce.GAME_END;
    }

    public BlackJackResponce getBlackJackResponce() {
        return blackJackResponce;
    }

}
