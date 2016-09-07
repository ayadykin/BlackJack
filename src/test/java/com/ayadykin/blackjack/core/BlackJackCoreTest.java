package com.ayadykin.blackjack.core;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ayadykin.blackjack.actions.BlackJackResponce;
import com.ayadykin.blackjack.core.cards.Card;
import com.ayadykin.blackjack.core.cards.CardDeck;
import com.ayadykin.blackjack.core.cards.Card.CardSuit;
import com.ayadykin.blackjack.core.cards.Card.Nominal;
import com.ayadykin.blackjack.core.model.Player;

/**
* Created by Andrey Yadykin on 15 бер. 2016 р.
*/

public class BlackJackCoreTest {

    private List<Player> players = new ArrayList<>();
    
    
    private BlackJackCore blackJackCore = new BlackJackCore();
    
    @Before
    public void init(){
        players.add(new Player(0, 100));
        players.add(new Player(true));
        
        Card card = new Card(CardSuit.clubs, Nominal.ACE).setHidden(false);
        Card card2 = new Card(CardSuit.clubs, Nominal.EIGHT).setHidden(false);

        players.get(0).addCard(card);
        players.get(0).addCard(card2);
        //blackJackCore.dealCards(players, cardDeck);
    }
    @Test
    public void checkBlackJackTest(){
        
        Card card3 = new Card(CardSuit.clubs, Nominal.THREE).setHidden(false);
        
        assertEquals(BlackJackResponce.NEXT_MOVE, blackJackCore.checkBlackJack(players));
        
        players.get(0).addCard(card3);
        
        assertEquals(3, players.get(0).getCards().size());
        assertEquals(BlackJackResponce.BLACK_JACK, blackJackCore.checkBlackJack(players));
    }
    @Test
    public void playerGetCardTest(){
        Card card3 = new Card(CardSuit.clubs, Nominal.FOUR).setHidden(false);
        
        assertEquals(BlackJackResponce.LOSE, blackJackCore.playerGetCard(players.get(0), card3));
    }
}

