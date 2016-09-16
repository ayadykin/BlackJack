package com.ayadykin.blackjack.core.table;

import java.util.Set;

import javax.ejb.Local;

/**
 * Created by Yadykin Andrii Sep 9, 2016
 *
 */

@Local
public interface TableBoard {
    
    Set<Table> getTables();
    
    void addTable(Table tables);

    Table getTable(long id);
}

