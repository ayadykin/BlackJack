package com.ayadykin.blackjack.core.timer;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ayadykin.blackjack.core.model.Player;
import com.ayadykin.blackjack.core.table.BlackJackTable;

/**
 * Created by Yadykin Andrii Sep 15, 2016
 *
 */

@Stateless
public class StartGameTimer {

    private static final Logger logger = LoggerFactory.getLogger(StartGameTimer.class);

    @Resource
    private TimerService timerService;
    private BlackJackTable blackJackTable;
    private Timer timer;

    public void setStartGameTimer(BlackJackTable blackJackTable) {
        this.blackJackTable = blackJackTable;
        logger.debug(" setTimer ");
        timer = timerService.createIntervalTimer(0, 2000, null);

    }

    @Timeout
    public void timeoutTimerService() {
        boolean bet = true;
        for (Player player : blackJackTable.getPlayers()) {
            if (player.getBet() == 0) {
                bet = false;
            }
        }
        if (bet) {
            blackJackTable.startGame();
            timer.cancel();
        }

        logger.debug(" timeoutTimerService ");
    }
}
