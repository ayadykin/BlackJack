package com.ayadykin.game.core.table;

import java.io.Serializable;
import java.util.List;

import com.ayadykin.game.core.actions.GameStatus;
import com.ayadykin.game.core.cards.Card;
import com.ayadykin.game.core.cards.CardDeck;
import com.ayadykin.game.core.model.Dealer;
import com.ayadykin.game.core.model.Player;

/**
 * Created by Andrey Yadykin on 22.02.2016.
 */

public interface Table extends Serializable{
   
    void init(Player player);
    
    boolean playerStep(Player player);
    
    boolean dealerStep();
    
    void countPoints();
    
    Player getNextPlayer(Player player);
    
    long getId();

    void addPlayer(Player player);
    
    void removePlayer(Player player);
    
    void newCardDeck();

    List<Player> getPlayers();

    CardDeck getCardDeck();

    Card getCard();

    Dealer getDealer();
    
    void setGameStatus(GameStatus gameStatus);

    GameStatus getGameStatus();
}
