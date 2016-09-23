package com.ayadykin.blackjack.core;

import static org.mockito.Mockito.when;

import javax.ejb.EJB;
import javax.inject.Inject;

import org.apache.openejb.jee.EjbJar;
import org.apache.openejb.junit.ApplicationComposer;
import org.apache.openejb.testing.Classes;
import org.apache.openejb.testing.Module;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import com.ayadykin.card.game.core.state.impl.EndGameStateImpl;
import com.ayadykin.card.game.core.state.impl.InitGameStateImpl;
import com.ayadykin.game.blackjack.actions.BlackJackActions;
import com.ayadykin.game.blackjack.core.BlackJackCore;
import com.ayadykin.game.blackjack.core.BlackJackRules;
import com.ayadykin.game.blackjack.exceptions.BlackJackException;
import com.ayadykin.game.core.GameFlow;
import com.ayadykin.game.core.deal.impl.BlackJackDealStrategy;
import com.ayadykin.game.core.state.GameState;
import com.ayadykin.game.core.state.impl.SetBetStateImpl;
import com.ayadykin.game.core.state.impl.StartGameStateImpl;
import com.ayadykin.game.core.table.Table;
import com.ayadykin.game.core.table.impl.BlackJackTableImpl;
import com.ayadykin.game.domain.model.User;
import com.ayadykin.game.rest.dto.ResponseDto;
import com.ayadykin.game.services.UserService;

/**
 * Created by Yadykin Andrii Sep 8, 2016
 *
 */

@RunWith(ApplicationComposer.class)
public class GameFlowTest {

    @Module
    @Classes(cdi = true, value = { GameFlow.class, Table.class, BlackJackTableImpl.class, GameState.class, EndGameStateImpl.class,
            InitGameStateImpl.class, StartGameStateImpl.class, SetBetStateImpl.class, BlackJackDealStrategy.class, BlackJackCore.class,
            BlackJackRules.class })
    public EjbJar jar() {
        return new EjbJar();
    }
    
    @EJB
    private UserService userService;
    @Inject
    private GameFlow gameFlow; 
    @EJB
    private Table blackJackTable;
    

    @Before
    public void init(){
        gameFlow.initGameFlow(blackJackTable);
        
    }

    @Test
    public void testNewGame() {
        
        gameFlow.blackJackActions(BlackJackActions.BET);
        ResponseDto blackJackResponce = gameFlow.blackJackActions(BlackJackActions.START);

        if (blackJackResponce.getPlayer().getPoints() < 18) {
            gameFlow.blackJackActions(BlackJackActions.HIT);
        }
        gameFlow.blackJackActions(BlackJackActions.STAND);

    }

    @Test(expected = BlackJackException.class)
    public void testStartGameException() {      
        gameFlow.blackJackActions(BlackJackActions.START);
    }

    @Test(expected = BlackJackException.class)
    public void testHitGameException() {
        when(userService.getLoggedUser()).thenReturn(new User());
        gameFlow.blackJackActions(BlackJackActions.HIT);
    }

    @Test(expected = BlackJackException.class)
    public void testStandGameException() {
        when(userService.getLoggedUser()).thenReturn(new User());
        gameFlow.blackJackActions(BlackJackActions.STAND);
    }
}
