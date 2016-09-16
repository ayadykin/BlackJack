package com.ayadykin.blackjack.core.game;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ayadykin.blackjack.core.table.Table;
import com.ayadykin.blackjack.core.table.TableBoard;
import com.ayadykin.blackjack.core.table.TableType;
import com.ayadykin.blackjack.core.table.factory.TableFactory;
import com.ayadykin.blackjack.services.PlayerService;

/**
 * Created by Yadykin Andrii Sep 16, 2016
 *
 */

@Stateless
public class CreateNewGame {
    @EJB
    private PlayerService playerService;
    @Inject
    private TableBoard tableBoard;
    @Inject
    private TableFactory tableFactory;

    public Table createNewTable() {
        Table blackJackTable = tableFactory.createTable(TableType.Type.BLACK_JACK);      
        blackJackTable.init(playerService.createPlayer());
        tableBoard.addTable(blackJackTable);
        return blackJackTable;
    }
}
