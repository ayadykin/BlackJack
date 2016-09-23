package com.ayadykin.game.blackjack.timer;

import javax.ejb.Stateless;

import com.ayadykin.game.actions.PlayerStatus;
import com.ayadykin.game.blackjack.actions.PlayerResult;
import com.ayadykin.game.core.model.Dealer;
import com.ayadykin.game.core.model.Player;
import com.ayadykin.game.core.table.Table;

/**
 * Created by Yadykin Andrii Sep 16, 2016
 *
 */

@Stateless
public class EndGame {

    public void endGame(Table table) {
        // Clear
        for (Player player : table.getPlayers()) {
            player.getCards().clear();
            player.setPoints(0);
            player.setBet(0);
            player.setPlayerStatus(PlayerStatus.WAIT);
            player.setPlayerResult(PlayerResult.NONE);
        }
        Dealer dealer = table.getDealer();
        dealer.getCards().clear();
        dealer.setPoints(0);
        dealer.setPlayerResult(PlayerResult.NONE);
    }

}
