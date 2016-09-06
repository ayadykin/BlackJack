package com.ayadykin.blackjack.services;

import com.ayadykin.blackjack.exceptions.BlackJackException;
import com.ayadykin.blackjack.rest.dto.PlayerActionDto;
import com.ayadykin.blackjack.rest.dto.ResponseDto;

/**
* Created by Andrey Yadykin on 22 лют. 2016 р.
*/


public interface GameService {
    ResponseDto initGame(long id) throws BlackJackException;
    
    int setBet(int bet);
    
    ResponseDto action(PlayerActionDto playerActionDto) throws BlackJackException;
}

