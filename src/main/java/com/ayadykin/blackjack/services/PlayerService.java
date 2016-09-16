package com.ayadykin.blackjack.services;

import com.ayadykin.blackjack.core.model.Player;
import com.ayadykin.blackjack.core.table.Table;

/**
 * Created by Yadykin Andrii Sep 15, 2016
 *
 */

public interface PlayerService {
    
    public Player createPlayer();
    
    Player getPlayer(Table blackJackTable);
}

