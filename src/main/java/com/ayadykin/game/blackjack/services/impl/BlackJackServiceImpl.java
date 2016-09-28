package com.ayadykin.game.blackjack.services.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ayadykin.game.blackjack.core.BlackJackPlayerFlow;
import com.ayadykin.game.blackjack.services.BlackJackService;
import com.ayadykin.game.blackjack.timer.StartGameTimer;
import com.ayadykin.game.core.model.Player;
import com.ayadykin.game.core.table.BlackJackTable;
import com.ayadykin.game.core.table.Table;
import com.ayadykin.game.rest.dto.PlayerActionDto;
import com.ayadykin.game.rest.dto.ResponseDto;
import com.ayadykin.game.rest.dto.StatusDto;
import com.ayadykin.game.services.ITableBoardService;

/**
 * Created by Andrey Yadykin on 22 лют. 2016 р.
 */

@Stateless
public class BlackJackServiceImpl implements BlackJackService {

    private static final Logger logger = LoggerFactory.getLogger(BlackJackService.class);

    @Inject
    private BlackJackPlayerFlow blackJackPlayerFlow;
    @EJB
    private ITableBoardService tableBoardService;
    @EJB
    private StartGameTimer gameTimer;

    @Override
    public void init(Table table, Player player) {
        logger.debug(" init ");

        // Init game flow
        blackJackPlayerFlow.initBlackJackGameFlow(table, player);
        gameTimer.setStartGameTimer((BlackJackTable) table);
    }

    @Override
    public ResponseDto gameAction(PlayerActionDto playerActionDto) {      
        return blackJackPlayerFlow.gameAction(playerActionDto.getBlackJackAction());
    }
    
    @Override
    public boolean leaveGame(int tableId){
        //Table table = tableBoard.getTable(tableId);
        //table.addPlayer(player);
        //tableBoard.removeTable(table);
        return true;
    }
    

    @Override
    public StatusDto gameStatus() throws InterruptedException {
        Thread.sleep(5000);
        return blackJackPlayerFlow.getStatus();
    }

    @Override
    public ResponseDto getCards() throws InterruptedException {
        return blackJackPlayerFlow.getResponseDto();
    }
}
