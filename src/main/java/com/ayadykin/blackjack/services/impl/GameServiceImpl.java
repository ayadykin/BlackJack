package com.ayadykin.blackjack.services.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ayadykin.blackjack.core.GameFlow;
import com.ayadykin.blackjack.rest.dto.ResponseDto;
import com.ayadykin.blackjack.services.GameService;

/**
 * Created by Andrey Yadykin on 22.02.2016.
 */

@Stateless      
public class GameServiceImpl implements GameService {

    @Inject
    private GameFlow gameFlow;



    @Override
    public ResponseDto initGame(Integer id) {

        return gameFlow.initGame(id);
    }


}
