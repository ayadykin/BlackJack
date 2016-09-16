package com.ayadykin.blackjack.services.impl;

import java.util.Objects;
import java.util.concurrent.Callable;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ayadykin.blackjack.actions.GameActions;
import com.ayadykin.blackjack.actions.PlayerStatus;
import com.ayadykin.blackjack.core.GameFlow;
import com.ayadykin.blackjack.core.game.CheckTableExist;
import com.ayadykin.blackjack.core.game.ConnectToGame;
import com.ayadykin.blackjack.core.game.CreateNewGame;
import com.ayadykin.blackjack.core.table.BlackJackTable;
import com.ayadykin.blackjack.core.table.Table;
import com.ayadykin.blackjack.core.timer.StartGameTimer;
import com.ayadykin.blackjack.exceptions.BlackJackException;
import com.ayadykin.blackjack.rest.dto.PlayerActionDto;
import com.ayadykin.blackjack.rest.dto.ResponseDto;
import com.ayadykin.blackjack.services.GameService;

/**
 * Created by Andrey Yadykin on 22.02.2016.
 */

@Stateless
public class GameServiceImpl implements GameService {

    @Inject
    private GameFlow gameFlow;
    @EJB
    private ConnectToGame connectToGameFlow;
    @EJB
    private CreateNewGame createNewGame;
    @EJB
    private StartGameTimer gameTimer;
    @EJB
    private CheckTableExist checkTableExist;

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
            Table table = checkTableExist.getExistTable();
            if (Objects.nonNull(table)) {
                blackJackTable = (BlackJackTable) table;
            } else {
                blackJackTable = (BlackJackTable) createNewGame.createNewTable();
            }
            gameTimer.setStartGameTimer(blackJackTable);

            break;
        default:
            throw new BlackJackException("Error game type");
        }
        gameFlow.initGameFlow(blackJackTable);
    }

    @Override
    // @Asynchronous
    public Callable<PlayerStatus> gameStatus() throws InterruptedException {
        Thread.sleep(5000);
        return gameFlow::getPlayerStatus;
    }

    @Override
    // @Asynchronous
    public Callable<ResponseDto> getCards() throws InterruptedException {
        Thread.sleep(5000);
        return gameFlow::getResponseDto;
    }

}
