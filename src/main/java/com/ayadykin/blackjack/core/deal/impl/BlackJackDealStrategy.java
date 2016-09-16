package com.ayadykin.blackjack.core.deal.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ayadykin.blackjack.core.cards.Card;
import com.ayadykin.blackjack.core.cards.CardDeck;
import com.ayadykin.blackjack.core.deal.DealCards;
import com.ayadykin.blackjack.core.game.StartGame;
import com.ayadykin.blackjack.core.model.Dealer;
import com.ayadykin.blackjack.core.model.Player;

/**
 * Created by Yadykin Andrii Sep 7, 2016
 *
 */

@Named("blackJackDeal")
@RequestScoped
public class BlackJackDealStrategy implements DealCards {
    
    private static final Logger logger = LoggerFactory.getLogger(DealCards.class);
    
    private static final int DEAL_CARDS = 2;

    @Override
    public void dealCards(List<Player> players, Dealer dealer, CardDeck cardDeck) {
        logger.debug(" dealCards ");
        int size = players.size();
        for (int i = 0; i < DEAL_CARDS; i++) {
            for (int j = 0; j < size + 1; j++) {
                Card card = cardDeck.getCard().setHidden(false);
                if (j == size) {
                    if(i == 1){
                        card.setHidden(true);
                    }
                    dealer.addCard(card);
                } else {
                    players.get(j).addCard(card);
                }
            }
        }
    }

}
