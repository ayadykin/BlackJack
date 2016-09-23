package com.ayadykin.game.services;

import com.ayadykin.game.core.table.TableType;

/**
 * Created by Andrey Yadykin on 22 ���. 2016 �.
 */

public interface GameService {

    void connectToGame(int tableId);

    long createGame(TableType.Type gameType);
}
