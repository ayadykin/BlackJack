package com.ayadykin.blackjack.core.game;

import javax.ejb.Stateless;

import com.ayadykin.blackjack.actions.PlayerStatus;
import com.ayadykin.blackjack.core.model.Dealer;
import com.ayadykin.blackjack.core.model.Player;
import com.ayadykin.blackjack.core.table.Table;

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
            player.setPlayerStatus(PlayerStatus.INIT);

        }
        Dealer dealer = table.getDealer();
        dealer.getCards().clear();
        dealer.setPoints(0);
    }
}

