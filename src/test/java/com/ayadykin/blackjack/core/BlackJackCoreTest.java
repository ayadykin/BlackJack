package com.ayadykin.blackjack.core;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import org.apache.openejb.jee.EjbJar;
import org.apache.openejb.junit.ApplicationComposer;
import org.apache.openejb.mockito.MockitoInjector;
import org.apache.openejb.testing.Classes;
import org.apache.openejb.testing.Module;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ayadykin.card.game.core.state.impl.EndGameStateImpl;
import com.ayadykin.card.game.core.state.impl.InitGameStateImpl;
import com.ayadykin.game.blackjack.core.BlackJackCore;
import com.ayadykin.game.blackjack.core.BlackJackRules;
import com.ayadykin.game.core.actions.GameStatus;
import com.ayadykin.game.core.cards.Card;
import com.ayadykin.game.core.cards.Card.CardSuit;
import com.ayadykin.game.core.cards.Card.Nominal;
import com.ayadykin.game.core.deal.impl.BlackJackDealStrategy;
import com.ayadykin.game.core.model.Dealer;
import com.ayadykin.game.core.model.Player;
import com.ayadykin.game.core.state.GameState;
import com.ayadykin.game.core.state.impl.SetBetStateImpl;
import com.ayadykin.game.core.state.impl.StartGameStateImpl;
import com.ayadykin.game.core.table.Table;
import com.ayadykin.game.core.table.impl.BlackJackTableImpl;

import junit.framework.TestCase;

/**
 * Created by Andrey Yadykin on 15 ���. 2016 �.
 */

@RunWith(ApplicationComposer.class)
public class BlackJackCoreTest extends TestCase {
    Card card = new Card(CardSuit.clubs, Nominal.ACE).setHidden(false);
    Card card1 = new Card(CardSuit.clubs, Nominal.SIX).setHidden(false);
    Card card2 = new Card(CardSuit.clubs, Nominal.EIGHT).setHidden(false);
    Card card3 = new Card(CardSuit.clubs, Nominal.THREE).setHidden(false);
    Card card4 = new Card(CardSuit.clubs, Nominal.SEVEN).setHidden(false);
    Card card5 = new Card(CardSuit.clubs, Nominal.KING).setHidden(false);
    Card card6 = new Card(CardSuit.clubs, Nominal.TWO).setHidden(false);

    private Player player = new Player();
    private Dealer dealer = new Dealer();

    @EJB
    private BlackJackCore blackJackCore;
    
    @Module
    @Classes(cdi = true, value = {  BlackJackCore.class })
    public EjbJar jar() {
        return new EjbJar();
    }
    
    @Before
    public void setUp() throws NamingException {

        //Context context = EJBContainer.createEJBContainer().getContext();
        //blackJackCore = (BlackJackCore) context.lookup("java:global/BlackJack/" + BlackJackCore.class.getSimpleName());

        // Player set 18 point
        player.addCard(card);
        player.addCard(card2);

        // Dealer add 14 points
        dealer.addCard(card1);
        dealer.addCard(card2);
    }

    @Test
    public void testCheckBlackJack() {

        assertEquals(GameStatus.NEXT_STEP, blackJackCore.checkBlackJack(player, dealer));
        assertEquals(18, player.getPoints());
        //assertEquals(14, dealer.getPoints());
        
        blackJackCore.playerStep(player, card3);
        assertEquals(GameStatus.BLACK_JACK, blackJackCore.checkBlackJack(player, dealer));
        assertEquals(21, player.getPoints());
        assertEquals(14, dealer.getPoints());
        
        blackJackCore.playerStep(dealer, card4);
        assertEquals(GameStatus.PUSH, blackJackCore.checkBlackJack(player, dealer));
        assertEquals(21, player.getPoints());
        assertEquals(21, dealer.getPoints());
    }

    @Test
    public void testCheckPlayerStep() {
        assertEquals(GameStatus.NEXT_STEP, blackJackCore.playerStep(player, card6));
        assertEquals(20, player.getPoints());
        assertEquals(GameStatus.YOU_BUST, blackJackCore.playerStep(player, card6));
        assertEquals(22, player.getPoints());

    }

    @Test
    public void testCheckDealerStep() {       
        assertEquals(GameStatus.NEXT_STEP, blackJackCore.dealerStep(dealer, card6));
        assertEquals(16, dealer.getPoints());
        assertEquals(GameStatus.DEALER_STAND, blackJackCore.dealerStep(dealer, card6));
        assertEquals(18, dealer.getPoints());
    }
    
    @Test
    public void testCheckDealerStand() {  
        dealer.addCard(card1);
        assertEquals(GameStatus.DEALER_STAND, blackJackCore.dealerStep(dealer, card5));
        assertEquals(20, dealer.getPoints());
    }
    
    @Test
    public void testCheckDealerBust() {       
        assertEquals(GameStatus.DEALER_BUST, blackJackCore.dealerStep(dealer, card5));
        assertEquals(24, dealer.getPoints());
    }

    @Test
    public void testGetGameResult() {
        assertEquals(GameStatus.PUSH, blackJackCore.getGameResult(20, 20));
        assertEquals(GameStatus.WIN, blackJackCore.getGameResult(19, 18));
        assertEquals(GameStatus.LOSE, blackJackCore.getGameResult(19, 20));
    }
}
