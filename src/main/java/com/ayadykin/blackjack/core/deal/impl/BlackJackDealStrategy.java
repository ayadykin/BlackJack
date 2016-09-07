package com.ayadykin.blackjack.core.deal.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.ayadykin.blackjack.core.deal.DealCards;
import com.ayadykin.blackjack.core.model.Card;
import com.ayadykin.blackjack.core.model.CardDeck;
import com.ayadykin.blackjack.core.model.Dealer;
import com.ayadykin.blackjack.core.model.Player;

/**
 * Created by Yadykin Andrii Sep 7, 2016
 *
 */

@Named("blackJackDeal")
@RequestScoped
public class BlackJackDealStrategy implements DealCards {
    
    private static final int DEAL_CARDS = 2;

    @Override
    public void dealCards(List<Player> players, CardDeck cardDeck) {
        for (Player player : players) {
            for (int i = 0; i < DEAL_CARDS; i++) {
                Card card = cardDeck.getCard().setHidden(false);
                if (player instanceof Dealer && i == 1) {
                    card.setHidden(true);
                }
                player.addCard(card);
            }
        }
    }

}
