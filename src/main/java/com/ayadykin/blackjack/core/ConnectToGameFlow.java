package com.ayadykin.blackjack.core;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ayadykin.blackjack.core.table.Table;
import com.ayadykin.blackjack.core.table.TableBoard;
import com.ayadykin.blackjack.services.PlayerService;

/**
 * Created by Yadykin Andrii Sep 14, 2016
 *
 */

@Stateless
public class ConnectToGameFlow implements Serializable{

    private static final Logger logger = LoggerFactory.getLogger(ConnectToGameFlow.class);

    @Inject
    private TableBoard tableBoard;
    @EJB
    private PlayerService playerService;
    
    public Table connectToTable(long tableId) {

        logger.debug(" connectToTable tableId : {}", tableId);

        Table blackJackTable = tableBoard.getTable(tableId);

        blackJackTable.addPlayer(playerService.createPlayer());

        return blackJackTable;
    }
}
