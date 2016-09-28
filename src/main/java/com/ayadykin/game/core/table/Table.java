package com.ayadykin.game.core.table;

import java.io.Serializable;
import java.util.List;

import com.ayadykin.game.core.actions.GameStatus;
import com.ayadykin.game.core.model.Dealer;
import com.ayadykin.game.core.model.Player;

/**
 * Created by Andrey Yadykin on 22.02.2016.
 */

public interface Table extends Serializable{
	
    void init(Player player);
    
    long getId();

    void addPlayer(Player player);
    
    void removePlayer(Player player);

    List<Player> getPlayers();

    Dealer getDealer();
    
    void setGameStatus(GameStatus gameStatus);

    GameStatus getGameStatus();
}
