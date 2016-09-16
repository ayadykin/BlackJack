package com.ayadykin.blackjack.services.impl;

import java.util.concurrent.Callable;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ayadykin.blackjack.actions.GameActions;
import com.ayadykin.blackjack.actions.PlayerStatus;
import com.ayadykin.blackjack.core.ConnectToGameFlow;
import com.ayadykin.blackjack.core.GameFlow;
import com.ayadykin.blackjack.core.table.BlackJackTable;
import com.ayadykin.blackjack.core.table.TableBoard;
import com.ayadykin.blackjack.core.table.TableType;
import com.ayadykin.blackjack.core.table.factory.TableFactory;
import com.ayadykin.blackjack.core.timer.StartGameTimer;
import com.ayadykin.blackjack.exceptions.BlackJackException;
import com.ayadykin.blackjack.rest.dto.PlayerActionDto;
import com.ayadykin.blackjack.rest.dto.ResponseDto;
import com.ayadykin.blackjack.services.GameService;
import com.ayadykin.blackjack.services.PlayerService;

/**
 * Created by Andrey Yadykin on 22.02.2016.
 */

@Stateless
public class GameServiceImpl implements GameService {

    @Inject
    private GameFlow gameFlow;
    @EJB
    private ConnectToGameFlow connectToGameFlow;
    @Inject
    private TableFactory tableFactory;
    @EJB
    private PlayerService playerService;
    @Inject
    private TableBoard tableBoard;
    @EJB
    private StartGameTimer gameTimer;
    
    @Override
    public ResponseDto gameAction(PlayerActionDto playerActionDto) {
        return gameFlow.blackJackActions(playerActionDto.getBlackJackAction());
    }

    @Override
    public void gameType(GameActions gameActions) {
        BlackJackTable blackJackTable = null;
        switch (gameActions) {
        case CONNECT:
            blackJackTable = (BlackJackTable) connectToGameFlow.connectToTable(1);
            break;
        case NEW:
            blackJackTable = (BlackJackTable) tableFactory.createTable(TableType.Type.BLACK_JACK);
            blackJackTable.init(playerService.createPlayer());
            tableBoard.addTable(blackJackTable);
            
            gameTimer.setStartGameTimer(blackJackTable);
            break;
        default:
            throw new BlackJackException("Error game type");
        }
        gameFlow.initGameFlow(blackJackTable);
    }
    
    @Override
    //@Asynchronous
    public Callable<PlayerStatus> gameStatus() throws InterruptedException {
        Thread.sleep(5000);
        return gameFlow::getPlayerStatus;
    }
    
    @Override
    //@Asynchronous
    public Callable<ResponseDto> getCards() throws InterruptedException {
        Thread.sleep(5000);
        return gameFlow::getResponseDto;
    }

}
