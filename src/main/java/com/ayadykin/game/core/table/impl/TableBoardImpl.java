package com.ayadykin.game.core.table.impl;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ayadykin.game.core.table.Table;
import com.ayadykin.game.core.table.TableBoard;

/**
 * Created by Yadykin Andrii Sep 9, 2016
 *
 */

@Named
@ApplicationScoped
public class TableBoardImpl implements Serializable, TableBoard {

    private static final Logger logger = LoggerFactory.getLogger(TableBoard.class);

    private Set<Table> tables = new HashSet<>();

    @Override
    public Set<Table> getTables() {
        return tables;
    }

    @Override
    public boolean addTable(Table table) {
        return tables.add(table);
    }

    @Override
    public Table getTable(long tableId) {
        logger.debug("getTable tableId {}", tableId);
        return tables.stream().filter(t -> t.getId() == tableId).findFirst().orElse(null);
    }

    @Override
    public boolean removeTable(Table table) {
        logger.debug(" removeTable tableId : {}", table.getId());
        return tables.remove(table);
    }
}
