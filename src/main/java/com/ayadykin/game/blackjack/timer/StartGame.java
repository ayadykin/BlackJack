package com.ayadykin.game.blackjack.timer;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ayadykin.game.actions.PlayerStatus;
import com.ayadykin.game.core.actions.GameStatus;
import com.ayadykin.game.core.deal.DealCards;
import com.ayadykin.game.core.table.Table;

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

    public void startGame(Table table) {
        logger.debug(" startGame ");

        // Create new card deck
        table.newCardDeck();

        // Deal cards
        blackJackDealStrategy.dealCards(table.getPlayers(), table.getDealer(), table.getCardDeck());

        // Set points
        table.countPoints();

        // Set player status
        table.getPlayers().get(0).setPlayerStatus(PlayerStatus.STEP);

        table.setGameStatus(GameStatus.GAME_START);
        // blackJackCore.checkBlackJack(table.getPlayer(), table.getDealer());
    }

}
