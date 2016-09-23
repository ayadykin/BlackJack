package com.ayadykin.game.services.impl;

import java.util.Objects;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ayadykin.game.core.exceptions.TableBoardException;
import com.ayadykin.game.core.table.Table;
import com.ayadykin.game.core.table.TableBoard;
import com.ayadykin.game.services.ITableBoardService;

/**
 * Created by Yadykin Andrii Sep 23, 2016
 *
 */

@Stateless
public class TableBoardServiceImpl implements ITableBoardService {

    private static final Logger logger = LoggerFactory.getLogger(ITableBoardService.class);

    @Inject
    private TableBoard tableBoard;

    @Override
    public Table getExistTable(long tableId) {
        Table blackJackTable = tableBoard.getTable(tableId);
        if (Objects.isNull(blackJackTable)) {
            logger.error(" getExistTable : Table not found");
            throw new TableBoardException("Table not found");
        }
        return blackJackTable;
    }

    @Override
    public Table getTable(long tableId) {
        return tableBoard.getTable(tableId);
    }

    @Override
    public boolean addTable(Table table) {
        return tableBoard.addTable(table);
    }

}
