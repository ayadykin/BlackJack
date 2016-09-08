package com.ayadykin.blackjack.core;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ayadykin.blackjack.actions.BlackJackResponce;
import com.ayadykin.blackjack.core.state.EndGameState;
import com.ayadykin.blackjack.core.state.GameState;
import com.ayadykin.blackjack.core.state.InitGameState;
import com.ayadykin.blackjack.core.state.NewGameState;
import com.ayadykin.blackjack.core.state.StartGameState;
import com.ayadykin.blackjack.core.table.BlackJackTable;
import com.ayadykin.blackjack.core.table.Table;
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
    @NewGameState
    private GameState newGameState;
    
    private BlackJackResponce blackJackResponce = BlackJackResponce.NEXT_STEP;
    
    /*public GameFlow(){
    	
    }
    
    @NewGameState
    public GameFlow(GameState state) {
        this.state = state;
    }*/

    public ResponseDto initGame(Integer id) throws BlackJackException {

        switch (id) {
        case 0:
            // New
            newGameState.newGame(blackJackTable);
            break;
        case 1:
            // Init
            newGameState.initGame(blackJackTable);
            break;
        case 2:
            // HIT
            blackJackResponce = newGameState.hit(blackJackTable);
            break;
        case 3:
            // Stand
            blackJackResponce = newGameState.stand(blackJackTable);
            break;
        case 4:
            // End
            newGameState.endGame(blackJackResponce, blackJackTable.getPlayer());
            break;
        }

        return new ResponseDto(blackJackTable.getPlayers(), blackJackResponce);
    }

    public void setState(GameState state) {
        this.newGameState = state;
    }

    private void gameResult(BlackJackResponce blackJackResponce) {

        // Account account = accountService.updateAccount(player.getId(), bet);
        // player.setCash(account.getAccount());
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
