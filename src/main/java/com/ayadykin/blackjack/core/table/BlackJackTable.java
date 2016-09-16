package com.ayadykin.blackjack.core.table;

import com.ayadykin.blackjack.core.BlackJackFlow;

/**
 * Created by Yadykin Andrii Sep 16, 2016
 *
 */

public interface BlackJackTable extends Table{

    BlackJackFlow getBlackJackFlow();
    
    void startGame();
    
    void endGame() ;
}

