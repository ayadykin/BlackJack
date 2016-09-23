package com.ayadykin.game.blackjack.services;

import com.ayadykin.game.core.model.Player;
import com.ayadykin.game.core.table.Table;
import com.ayadykin.game.rest.dto.PlayerActionDto;
import com.ayadykin.game.rest.dto.ResponseDto;
import com.ayadykin.game.rest.dto.StatusDto;

/**
 * Created by Andrey Yadykin on 22 лют. 2016 р.
 */

public interface BlackJackService {

    void init(Table table, Player player);

    ResponseDto gameAction(PlayerActionDto playerActionDto);

    StatusDto gameStatus() throws InterruptedException;

    ResponseDto getCards() throws InterruptedException;
    
    boolean leaveGame(int tableId);
}
