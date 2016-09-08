package com.ayadykin.blackjack.core;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import com.ayadykin.blackjack.rest.dto.ResponseDto;

import junit.framework.TestCase;

/**
 * Created by Yadykin Andrii Sep 8, 2016
 *
 */

public class GameFlowTest extends TestCase {

    private GameFlow gameFlow;

    @Before
    public void setUp() throws NamingException {
        Context context = EJBContainer.createEJBContainer().getContext();
        gameFlow = (GameFlow) context.lookup("java:global/BlackJack/" + GameFlow.class.getSimpleName());
    }
    
    @Test
    public void testNewGame(){
        
        gameFlow.initGame(0);
        ResponseDto blackJackResponce = gameFlow.initGame(1);
        
        if(blackJackResponce.getPlayers().get(1).getPoints()<18){
            
        }
        
    }
}
