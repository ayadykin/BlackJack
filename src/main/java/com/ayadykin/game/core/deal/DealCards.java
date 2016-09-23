package com.ayadykin.game.core.deal;

import java.util.List;

import com.ayadykin.game.core.cards.CardDeck;
import com.ayadykin.game.core.model.Dealer;
import com.ayadykin.game.core.model.Player;

public interface DealCards {
    void dealCards(List<Player> players, Dealer dealer, CardDeck cardDeck);
}
