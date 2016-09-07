package com.ayadykin.blackjack.core.deal;

import java.util.List;

import com.ayadykin.blackjack.core.model.CardDeck;
import com.ayadykin.blackjack.core.model.Player;

public interface DealCards {
    void dealCards(List<Player> players, CardDeck cardDeck);
}
