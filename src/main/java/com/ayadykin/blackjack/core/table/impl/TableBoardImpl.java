package com.ayadykin.blackjack.core.table.impl;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ayadykin.blackjack.core.table.Table;
import com.ayadykin.blackjack.core.table.TableBoard;
import com.ayadykin.blackjack.exceptions.BlackJackException;

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
    public void addTable(Table tables) {
        this.tables.add(tables);
    }

    @Override
    public Table getTable(long id) {
        logger.debug("getTable size {}", tables.size());
        return tables.stream().filter(t -> t.getId() == id).findFirst().orElseThrow(() -> new BlackJackException("Table not found"));
    }
}
