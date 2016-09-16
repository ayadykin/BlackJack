package com.ayadykin.blackjack.core.state;

import javax.ejb.Local;

import com.ayadykin.blackjack.core.model.Player;
import com.ayadykin.blackjack.core.table.BlackJackTable;
import com.ayadykin.blackjack.core.table.Table;

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
