package com.ayadykin.game.blackjack.core;

import java.io.Serializable;
import java.util.Objects;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ayadykin.game.blackjack.actions.BlackJackActions;
import com.ayadykin.game.blackjack.exceptions.BlackJackException;
import com.ayadykin.game.core.model.Player;
import com.ayadykin.game.core.state.GameState;
import com.ayadykin.game.core.state.qualifiers.SetBetState;
import com.ayadykin.game.core.state.qualifiers.StartGameState;
import com.ayadykin.game.core.table.BlackJackTable;
import com.ayadykin.game.core.table.Table;
import com.ayadykin.game.rest.dto.ResponseDto;
import com.ayadykin.game.rest.dto.StatusDto;
import com.ayadykin.game.services.PlayerService;

/**
 * Created by Yadykin Andrii Sep 21, 2016
 *
 */

@Named
@SessionScoped
public class BlackJackPlayerFlow implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(BlackJackPlayerFlow.class);

    @EJB
    private PlayerService playerService;

    @Inject
    @StartGameState
    private GameState startGameState;

    @Inject
    @SetBetState
    private GameState setBetState;

    private GameState state;

    private Player player;

    private BlackJackTable blackJackTable;

    public void initBlackJackGameFlow(Table table, Player player) {
        logger.debug(" initBlackJackGameFlow ");
        this.blackJackTable = (BlackJackTable) table;
        this.state = setBetState;
        this.player = player;
    }

    public ResponseDto gameAction(BlackJackActions blackJackActions) throws BlackJackException {

        // Validate user

        if (Objects.isNull(blackJackTable)) {
            throw new BlackJackException("Error choose game type");
        }

        switch (blackJackActions) {

        case BET:
            player.setBet(50);
            state.setBet(50);
            break;
        case HIT:
            // HIT
            state.hit(player, blackJackTable);
            break;
        case STAND:
            // Stand
            state.stand(player, blackJackTable);
            break;
        default:
            break;
        }

        return new ResponseDto(blackJackTable);
    }

    public StatusDto getStatus() {
        logger.debug(" getPlayerStatus ");
        return new StatusDto(blackJackTable.getGameStatus(), player.getPlayerStatus());
    }

    public ResponseDto getResponseDto() {
        return new ResponseDto(blackJackTable);
    }

    /**
     * Get states
     */
    public void setState(GameState state) {
        this.state = state;
    }

    public GameState getSetBetState() {
        return setBetState;
    }

    public GameState getStartGameState() {
        return startGameState;
    }

}
