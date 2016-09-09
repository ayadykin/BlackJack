package com.ayadykin.blackjack.core;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import com.ayadykin.blackjack.actions.BlackJackResponce;
import com.ayadykin.blackjack.core.blackjack.BlackJackCore;
import com.ayadykin.blackjack.core.cards.Card;
import com.ayadykin.blackjack.core.cards.Card.CardSuit;
import com.ayadykin.blackjack.core.cards.Card.Nominal;
import com.ayadykin.blackjack.core.model.Dealer;
import com.ayadykin.blackjack.core.model.Player;

import junit.framework.TestCase;

/**
 * Created by Andrey Yadykin on 15 бер. 2016 р.
 */

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

    private BlackJackCore blackJackCore;

    @Before
    public void setUp() throws NamingException {

        Context context = EJBContainer.createEJBContainer().getContext();
        blackJackCore = (BlackJackCore) context.lookup("java:global/BlackJack/" + BlackJackCore.class.getSimpleName());

        // Player set 18 point
        player.addCard(card);
        player.addCard(card2);

        // Dealer add 14 points
        dealer.addCard(card1);
        dealer.addCard(card2);
    }

    @Test
    public void testCheckBlackJack() {

        assertEquals(BlackJackResponce.NEXT_STEP, blackJackCore.checkBlackJack(player, dealer));
        assertEquals(18, player.getPoints());
        //assertEquals(14, dealer.getPoints());
        
        blackJackCore.playerStep(player, card3);
        assertEquals(BlackJackResponce.BLACK_JACK, blackJackCore.checkBlackJack(player, dealer));
        assertEquals(21, player.getPoints());
        assertEquals(14, dealer.getPoints());
        
        blackJackCore.playerStep(dealer, card4);
        assertEquals(BlackJackResponce.PUSH, blackJackCore.checkBlackJack(player, dealer));
        assertEquals(21, player.getPoints());
        assertEquals(21, dealer.getPoints());
    }

    @Test
    public void testCheckPlayerStep() {
        assertEquals(BlackJackResponce.NEXT_STEP, blackJackCore.playerStep(player, card6));
        assertEquals(20, player.getPoints());
        assertEquals(BlackJackResponce.YOU_BUST, blackJackCore.playerStep(player, card6));
        assertEquals(22, player.getPoints());

    }

    @Test
    public void testCheckDealerStep() {       
        assertEquals(BlackJackResponce.NEXT_STEP, blackJackCore.dealerStep(dealer, card6));
        assertEquals(16, dealer.getPoints());
        assertEquals(BlackJackResponce.DEALER_STAND, blackJackCore.dealerStep(dealer, card6));
        assertEquals(18, dealer.getPoints());
    }
    
    @Test
    public void testCheckDealerStand() {  
        dealer.addCard(card1);
        assertEquals(BlackJackResponce.DEALER_STAND, blackJackCore.dealerStep(dealer, card5));
        assertEquals(20, dealer.getPoints());
    }
    
    @Test
    public void testCheckDealerBust() {       
        assertEquals(BlackJackResponce.DEALER_BUST, blackJackCore.dealerStep(dealer, card5));
        assertEquals(24, dealer.getPoints());
    }

    @Test
    public void testGetGameResult() {
        assertEquals(BlackJackResponce.PUSH, blackJackCore.getGameResult(20, 20));
        assertEquals(BlackJackResponce.WIN, blackJackCore.getGameResult(19, 18));
        assertEquals(BlackJackResponce.LOSE, blackJackCore.getGameResult(19, 20));
    }
}
