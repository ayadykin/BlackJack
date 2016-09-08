package com.ayadykin.blackjack.core;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ayadykin.blackjack.actions.BlackJackResponce;
import com.ayadykin.blackjack.core.state.GameState;
import com.ayadykin.blackjack.core.state.impl.NewGame;
import com.ayadykin.blackjack.core.table.Table;
import com.ayadykin.blackjack.exceptions.BlackJackException;
import com.ayadykin.blackjack.rest.dto.ResponseDto;

/**
 * Created by Andrey Yadykin on 15 бер. 2016 р.
 */
@Named
@SessionScoped
public class GameFlow implements Serializable {

    @EJB
    private BlackJackCore blackJackCore;

    @Inject
    private Table table;

    @Inject
    @Named("initGameState")
    private GameState initGameState;

    @Inject
    @Named("startGameState")
    private GameState startGameState;

    @Inject
    @Named("endGameState")
    private GameState endGameState;

    private GameState state;
    
    private BlackJackResponce blackJackResponce = BlackJackResponce.NEXT_STEP;

    public GameFlow() {
        this.state = new NewGame(this);
    }

    public ResponseDto initGame(Integer id) throws BlackJackException {

        switch (id) {
        case 0:
            // New
            state.newGame(table);
            break;
        case 1:
            // Init
            state.initGame(table);
            break;
        case 2:
            // HIT
            blackJackResponce = state.hit(table);
            break;
        case 3:
            // Stand
            blackJackResponce = state.stand(table);
            break;
        case 4:
            // End
            state.endGame(blackJackResponce, table.getPlayer());
            break;
        }

        return new ResponseDto(table.getPlayers(), blackJackResponce);
    }

    public void setState(GameState state) {
        this.state = state;
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
        table = null;
    }
}
