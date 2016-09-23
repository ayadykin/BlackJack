package com.ayadykin.game.core.state;

import javax.ejb.Local;

import com.ayadykin.game.core.model.Player;
import com.ayadykin.game.core.table.BlackJackTable;

/**
 * Created by Yadykin Andrii Sep 8, 2016
 *
 */

@Local
public interface GameState {

    void setBet(double bet);

    void hit(Player player, BlackJackTable table);

    void stand(Player player, BlackJackTable table);

}
