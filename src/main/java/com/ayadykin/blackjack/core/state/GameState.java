package com.ayadykin.blackjack.core.state;

import com.ayadykin.blackjack.actions.BlackJackResponce;
import com.ayadykin.blackjack.core.model.Player;
import com.ayadykin.blackjack.core.table.Table;

/**
 * Created by Yadykin Andrii Sep 8, 2016
 *
 */

public interface GameState {
    
    void newGame(Table table);
    
    void initGame(Table table);
    
    BlackJackResponce hit(Table table);
    
    BlackJackResponce stand(Table table);
    
    void endGame(BlackJackResponce blackJackResponce, Player player);
}

