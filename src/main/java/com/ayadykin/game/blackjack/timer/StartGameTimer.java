package com.ayadykin.game.blackjack.timer;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ayadykin.game.core.actions.GameStatus;
import com.ayadykin.game.core.model.Player;
import com.ayadykin.game.core.table.Table;

/**
 * Created by Yadykin Andrii Sep 15, 2016
 *
 */

@Stateless
public class StartGameTimer {

    private static final Logger logger = LoggerFactory.getLogger(StartGameTimer.class);

    @Resource
    private TimerService timerService;

    public void setStartGameTimer(Table table) {
        logger.debug(" setStartGameTimer ");
        table.setGameStatus(GameStatus.SET_BET_PREPARE_GAME);
        timerService.createIntervalTimer(0, 3000, new TimerConfig(table, false));

    }

    @Timeout
    public void timeoutTimerService(Timer timer) {
        logger.debug(" timeoutTimerService ");
        boolean bet = true;
        Table table = (Table) timer.getInfo();
        for (Player player : table.getPlayers()) {
            if (player.getBet() == 0) {
                bet = false;
            }
        }
        if (bet) {
        	 // Create new card deck
            table.getDealer().startGame(table.getPlayers());

            table.setGameStatus(GameStatus.GAME_START);
            
            timer.cancel();
            logger.debug(" timeoutTimerService canceled");
        }

    }
}
