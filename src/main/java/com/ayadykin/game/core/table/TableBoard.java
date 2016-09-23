package com.ayadykin.game.core.table;

import java.util.Set;

import javax.ejb.Local;

/**
 * Created by Yadykin Andrii Sep 9, 2016
 *
 */

@Local
public interface TableBoard {
    
    Set<Table> getTables();
    
    boolean addTable(Table tables);

    Table getTable(long tableId);
    
    boolean removeTable(Table table);
}

