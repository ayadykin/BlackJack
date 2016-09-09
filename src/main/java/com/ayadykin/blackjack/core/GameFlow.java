package com.ayadykin.blackjack.core;

import java.io.Serializable;

import javax.ejb.Remove;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ayadykin.blackjack.actions.BlackJackActions;
import com.ayadykin.blackjack.actions.BlackJackResponce;
import com.ayadykin.blackjack.core.state.GameState;
import com.ayadykin.blackjack.core.state.qualifiers.EndGameState;
import com.ayadykin.blackjack.core.state.qualifiers.InitGameState;
import com.ayadykin.blackjack.core.state.qualifiers.NewGameState;
import com.ayadykin.blackjack.core.state.qualifiers.StartGameState;
import com.ayadykin.blackjack.core.table.Table;
import com.ayadykin.blackjack.core.table.TableBoard;
import com.ayadykin.blackjack.core.table.qualifiers.BlackJackTable;
import com.ayadykin.blackjack.exceptions.BlackJackException;
import com.ayadykin.blackjack.rest.dto.ResponseDto;

/**
 * Created by Andrey Yadykin on 15 бер. 2016 р.
 */
@Named
@SessionScoped
public class GameFlow implements Serializable {

    @Inject
    @BlackJackTable
    private Table blackJackTable;

    @Inject
    @InitGameState
    private GameState initGameState;

    @Inject
    @StartGameState
    private GameState startGameState;

    @Inject
    @EndGameState
    private GameState endGameState;
    
    @Inject
    private TableBoard tableBoard;
    
    @Inject
    @NewGameState
    private GameState state;

    private BlackJackResponce blackJackResponce;

    public ResponseDto blackJackActions(BlackJackActions blackJackActions) throws BlackJackException {

        switch (blackJackActions) {
        case NEW:
            // New
            tableBoard.addTable(blackJackTable);
            blackJackResponce = BlackJackResponce.NEXT_STEP;
            state.newGame(blackJackTable);
            break;
        case START:
            // Init
            blackJackResponce = BlackJackResponce.NEXT_STEP;
            state.startGame(blackJackTable);
            break;
        case HIT:
            // HIT
            blackJackResponce = state.hit(blackJackTable);
            break;
        case STAND:
            // Stand
            blackJackResponce = state.stand(blackJackTable);
            state.endGame(blackJackResponce, blackJackTable.getPlayer().getBet());
            break;
        default:
            break;
        }

        return new ResponseDto(blackJackTable.getPlayers(), blackJackResponce);
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public GameState getInitGameState() {
        return initGameState;
    }

    public GameState getStartGameState() {
        return startGameState;
    }

    public GameState getEndGameState() {
        return endGameState;
    }

    @Remove
    public void destroy() {
        blackJackTable = null;
    }
}
