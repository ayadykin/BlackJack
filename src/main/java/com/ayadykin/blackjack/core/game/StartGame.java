package com.ayadykin.blackjack.core.game;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ayadykin.blackjack.actions.PlayerStatus;
import com.ayadykin.blackjack.core.blackjack.BlackJackCore;
import com.ayadykin.blackjack.core.deal.DealCards;
import com.ayadykin.blackjack.core.model.Dealer;
import com.ayadykin.blackjack.core.model.Player;
import com.ayadykin.blackjack.core.table.Table;

/**
 * Created by Yadykin Andrii Sep 15, 2016
 *
 */

@Stateless
public class StartGame {
    private static final Logger logger = LoggerFactory.getLogger(StartGame.class);
    
    @Inject
    @Named("blackJackDeal")
    private DealCards blackJackDealStrategy;

    @EJB
    private BlackJackCore blackJackCore;
    
    public void startGame(Table table) {
        logger.debug(" startGame ");
        
        // Create new card deck
        table.newCardDeck();
        
        //Deal cards
        blackJackDealStrategy.dealCards(table.getPlayers(), table.getDealer(), table.getCardDeck());
        
        //Set points
        for (Player player : table.getPlayers()){
            player.setPoints(blackJackCore.countPoints(player));
        }        
        Dealer dealer = table.getDealer();
        dealer.setPoints(blackJackCore.countPoints(dealer));
        
        //Set player status
        int i = 0;     
        for (Player player : table.getPlayers()) {          
            if(i == 0){
                player.setPlayerStatus(PlayerStatus.STEP);
            }else{
                player.setPlayerStatus(PlayerStatus.WAIT);
            }
            ++i;
        }
        
        //blackJackCore.checkBlackJack(table.getPlayer(), table.getDealer());
    }

}

