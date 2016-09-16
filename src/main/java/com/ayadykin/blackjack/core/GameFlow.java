package com.ayadykin.blackjack.core;

import java.io.Serializable;
import java.util.Objects;

import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ayadykin.blackjack.actions.BlackJackActions;
import com.ayadykin.blackjack.actions.PlayerStatus;
import com.ayadykin.blackjack.core.model.Player;
import com.ayadykin.blackjack.core.state.GameState;
import com.ayadykin.blackjack.core.state.qualifiers.SetBetState;
import com.ayadykin.blackjack.core.state.qualifiers.StartGameState;
import com.ayadykin.blackjack.core.table.BlackJackTable;
import com.ayadykin.blackjack.exceptions.BlackJackException;
import com.ayadykin.blackjack.rest.dto.ResponseDto;
import com.ayadykin.blackjack.services.PlayerService;

/**
 * Created by Andrey Yadykin on 15 бер. 2016 р.
 */
@Named
@SessionScoped
public class GameFlow implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(GameFlow.class);

    private BlackJackTable blackJackTable;

    @Inject
    @StartGameState
    private GameState startGameState;

    @Inject
    @SetBetState
    private GameState setBetState;

    private GameState state;

    @EJB
    private PlayerService playerService;

    public void initGameFlow(BlackJackTable blackJackTable) {
        this.blackJackTable = blackJackTable;
        this.state = setBetState;
    }

    public ResponseDto blackJackActions(BlackJackActions blackJackActions) throws BlackJackException {

        // Validate user

        if (Objects.isNull(blackJackTable)) {
            throw new BlackJackException("Error choose game type");
        }

        switch (blackJackActions) {

        case BET:
            playerService.getLoggedPlayerByTable(blackJackTable).setBet(50);
            state.setBet(50);
            break;
        case HIT:
            // HIT
            state.hit(playerService.getLoggedPlayerByTable(blackJackTable), blackJackTable);
            break;
        case STAND:
            // Stand
            state.stand(playerService.getLoggedPlayerByTable(blackJackTable), blackJackTable);
            break;
        default:
            break;
        }

        return new ResponseDto(blackJackTable);
    }

    public PlayerStatus getPlayerStatus() {
        return playerService.getLoggedPlayerByTable(blackJackTable).getPlayerStatus();
    }

    public ResponseDto getResponseDto() {
        return new ResponseDto(blackJackTable);
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public GameState getSetBetState() {
        return setBetState;
    }

    public GameState getStartGameState() {
        return startGameState;
    }

    @Remove
    public void destroy() {
        blackJackTable = null;
    }
}
