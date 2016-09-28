package com.ayadykin.game.blackjack.timer;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ayadykin.game.actions.PlayerStatus;
import com.ayadykin.game.blackjack.actions.PlayerResult;
import com.ayadykin.game.core.actions.GameStatus;
import com.ayadykin.game.core.model.Dealer;
import com.ayadykin.game.core.model.Player;
import com.ayadykin.game.core.table.Table;

/**
 * Created by Yadykin Andrii Sep 16, 2016
 *
 */

@Stateless
public class EndGameTimer {
    private static final Logger logger = LoggerFactory.getLogger(EndGameTimer.class);

    @Resource
    private TimerService timerService;
    @EJB
    private StartGameTimer gameTimer;

    public void setEndGameTimer(Table table) {
        logger.debug(" setEndGameTimer ");      
        table.setGameStatus(GameStatus.GAME_END);
        timerService.createSingleActionTimer(10000, new TimerConfig(table, false));
    }

    @Timeout
    public void timeoutTimerService(Timer timer) {
        Table table = (Table) timer.getInfo();
        
        endGame(table);
        logger.debug(" timeoutTimerService ");
        gameTimer.setStartGameTimer(table);
    }
    
    public void endGame(Table table) {
        // Clear
        for (Player player : table.getPlayers()) {
            player.getCards().clear();
            player.setPoints(0);
            player.setBet(0);
            player.setPlayerStatus(PlayerStatus.WAIT);
            player.setPlayerResult(PlayerResult.NONE);
        }
        Dealer dealer = table.getDealer();
        dealer.getCards().clear();
        dealer.setPoints(0);
        dealer.setPlayerResult(PlayerResult.NONE);
    }
}
