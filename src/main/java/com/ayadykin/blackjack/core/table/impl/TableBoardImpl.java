package com.ayadykin.blackjack.core.table.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import com.ayadykin.blackjack.core.table.Table;
import com.ayadykin.blackjack.core.table.TableBoard;

/**
 * Created by Yadykin Andrii Sep 9, 2016
 *
 */

@Named
@ApplicationScoped
public class TableBoardImpl implements Serializable, TableBoard{

    private List<Table> tables = new ArrayList<>();

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    @Override
    public void addTable(Table tables) {
        this.tables.add(tables);
    }

}
