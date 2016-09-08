package com.ayadykin.blackjack.core.state.impl;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ayadykin.blackjack.actions.BlackJackResponce;
import com.ayadykin.blackjack.core.BlackJackCore;
import com.ayadykin.blackjack.core.GameFlow;
import com.ayadykin.blackjack.core.deal.DealCards;
import com.ayadykin.blackjack.core.model.Player;
import com.ayadykin.blackjack.core.state.GameState;
import com.ayadykin.blackjack.core.state.InitGameState;
import com.ayadykin.blackjack.core.table.Table;
import com.ayadykin.blackjack.exceptions.BlackJackException;

/**
 * Created by Yadykin Andrii Sep 8, 2016
 *
 */

@InitGameState
@SessionScoped
public class InitGameStateImpl implements GameState, Serializable {
    @Inject
    @Named("blackJackDeal")
    private DealCards blackJackDealStrategy;

    @EJB
    private BlackJackCore blackJackCore;

    private GameFlow gameFlow;
    
    public InitGameStateImpl(){
    	
    }
    
    @Inject
    public InitGameStateImpl(GameFlow gameFlow) {
        this.gameFlow = gameFlow;
    }

    @Override
    public void newGame(Table table) {
        throw new BlackJackException("Error you can't call newGame() method, initGameState can only call initGame() method!");
    }

    @Override
    public void initGame(Table table) {
        table.newCardDeck();
        
        for (Player player : table.getPlayers()) {
			player.getCards().clear();
			player.setPoints(0);
		}
        
        table.getPlayer().setBet(10);
        blackJackDealStrategy.dealCards(table.getPlayers(), table.getCardDeck());
        blackJackCore.checkBlackJack(table.getPlayer(), table.getDealer());
        gameFlow.setState(gameFlow.getStartGameState());
    }

    @Override
    public BlackJackResponce hit(Table table) {
        throw new BlackJackException("Error initGameState can only call initGame() method!");

    }

    @Override
    public BlackJackResponce stand(Table table) {
        throw new BlackJackException("Error initGameState can only call initGame() method!");

    }

    @Override
    public void endGame(BlackJackResponce blackJackResponce, Player player) {
        throw new BlackJackException("Error initGameState can only call initGame() method!");

    }

}
