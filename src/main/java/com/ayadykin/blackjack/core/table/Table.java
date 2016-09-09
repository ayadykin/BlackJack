package com.ayadykin.blackjack.core.table;

import java.util.List;

import javax.ejb.Local;

import com.ayadykin.blackjack.core.cards.Card;
import com.ayadykin.blackjack.core.cards.CardDeck;
import com.ayadykin.blackjack.core.model.Dealer;
import com.ayadykin.blackjack.core.model.Player;

/**
 * Created by Andrey Yadykin on 22.02.2016.
 */
@Local
public interface Table {
   
    void init(long id);
    
    long getId();

    void addPlayer(Player player);

    void newCardDeck();

    Player getPlayer();

    List<Player> getPlayers();

    CardDeck getCardDeck();

    Card getCard();

    Dealer getDealer();
}
