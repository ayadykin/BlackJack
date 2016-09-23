package com.ayadykin.game.services;

import com.ayadykin.game.core.model.Player;
import com.ayadykin.game.core.table.Table;

/**
 * Created by Yadykin Andrii Sep 15, 2016
 *
 */

public interface PlayerService {
    
    Player getLoggedPlayerByTable(Table blackJackTable);
    
}

