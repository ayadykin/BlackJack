package com.ayadykin.blackjack.core.table;

import javax.ejb.Local;

/**
 * Created by Yadykin Andrii Sep 9, 2016
 *
 */

@Local
public interface TableBoard {
    void addTable(Table tables);
}

