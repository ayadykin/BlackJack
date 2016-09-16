package com.ayadykin.blackjack.core.game;

import java.io.Serializable;
import java.util.Objects;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ayadykin.blackjack.core.table.Table;
import com.ayadykin.blackjack.core.table.TableBoard;
import com.ayadykin.blackjack.exceptions.BlackJackException;
import com.ayadykin.blackjack.services.PlayerService;

/**
 * Created by Yadykin Andrii Sep 14, 2016
 *
 */

@Stateless
public class ConnectToGame implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(ConnectToGame.class);

    @Inject
    private TableBoard tableBoard;
    @EJB
    private PlayerService playerService;

    public Table connectToTable(long tableId) {

        logger.debug(" connectToTable tableId : {}", tableId);

        Table blackJackTable = tableBoard.getTable(tableId);
        if (Objects.isNull(blackJackTable)) {
            throw new BlackJackException("Table not found");
        }

        blackJackTable.addPlayer(playerService.createPlayer());

        return blackJackTable;
    }
}
