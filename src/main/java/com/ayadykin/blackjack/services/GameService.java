package com.ayadykin.blackjack.services;

import java.util.concurrent.Callable;

import com.ayadykin.blackjack.actions.GameActions;
import com.ayadykin.blackjack.actions.PlayerStatus;
import com.ayadykin.blackjack.rest.dto.PlayerActionDto;
import com.ayadykin.blackjack.rest.dto.ResponseDto;

/**
* Created by Andrey Yadykin on 22 лют. 2016 р.
*/


public interface GameService {
    ResponseDto gameAction(PlayerActionDto playerActionDto);
   
    void gameType(GameActions gameActions);
    
    Callable<PlayerStatus> gameStatus() throws InterruptedException;
    
    Callable<ResponseDto> getCards() throws InterruptedException;
}

