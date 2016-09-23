package com.ayadykin.game.services;

import com.ayadykin.game.core.table.TableType;

/**
 * Created by Andrey Yadykin on 22 лют. 2016 р.
 */

public interface GameService {

    void connectToGame(int tableId);

    long createGame(TableType.Type gameType);
}
