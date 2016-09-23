package com.ayadykin.game.services;

import com.ayadykin.game.core.table.Table;

/**
 * Created by Yadykin Andrii Sep 23, 2016
 *
 */

public interface ITableBoardService {

    /**
     * Get table by table id
     * 
     * @param tableId
     * @return table or null
     */
    Table getTable(long tableId);
    
    /**
     * Get table by table id
     * 
     * @param tableId
     * @return table
     * 
     * @throws TableBoardException if the table could not be found
     */
    Table getExistTable(long tableId);
    
    boolean addTable(Table table);
}

