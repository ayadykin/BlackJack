package com.ayadykin.blackjack.core;

import javax.ejb.embeddable.EJBContainer;
import javax.inject.Inject;
import javax.naming.NamingException;

import org.apache.openejb.jee.EjbJar;
import org.apache.openejb.junit.ApplicationComposer;
import org.apache.openejb.testing.Classes;
import org.apache.openejb.testing.Module;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ayadykin.blackjack.actions.BlackJackActions;
import com.ayadykin.blackjack.core.blackjack.BlackJackCore;
import com.ayadykin.blackjack.core.blackjack.BlackJackRules;
import com.ayadykin.blackjack.core.deal.impl.BlackJackDealStrategy;
import com.ayadykin.blackjack.core.state.GameState;
import com.ayadykin.blackjack.core.state.impl.EndGameStateImpl;
import com.ayadykin.blackjack.core.state.impl.InitGameStateImpl;
import com.ayadykin.blackjack.core.state.impl.NewGameStateImpl;
import com.ayadykin.blackjack.core.state.impl.StartGameStateImpl;
import com.ayadykin.blackjack.core.table.Table;
import com.ayadykin.blackjack.core.table.impl.BlackJackTableImpl;
import com.ayadykin.blackjack.core.table.qualifiers.BlackJackTable;
import com.ayadykin.blackjack.exceptions.BlackJackException;
import com.ayadykin.blackjack.rest.dto.ResponseDto;

import junit.framework.TestCase;

/**
 * Created by Yadykin Andrii Sep 8, 2016
 *
 */

@RunWith(ApplicationComposer.class)
public class GameFlowTest extends TestCase {

    @Module
    @Classes(cdi = true, value = { GameFlow.class, Table.class, BlackJackTableImpl.class, GameState.class, EndGameStateImpl.class,
            InitGameStateImpl.class, StartGameStateImpl.class, NewGameStateImpl.class, BlackJackDealStrategy.class, BlackJackCore.class,
            BlackJackRules.class })
    public EjbJar jar() {
        return new EjbJar();
    }

    @Inject
    private GameFlow gameFlow;

    @Test
    public void testNewGame() {

        gameFlow.blackJackActions(BlackJackActions.NEW);
        ResponseDto blackJackResponce = gameFlow.blackJackActions(BlackJackActions.START);

        if (blackJackResponce.getPlayers().get(1).getPoints() < 18) {
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
        gameFlow.blackJackActions(BlackJackActions.HIT);
    }

    @Test(expected = BlackJackException.class)
    public void testStandGameException() {
        gameFlow.blackJackActions(BlackJackActions.STAND);
    }
}
