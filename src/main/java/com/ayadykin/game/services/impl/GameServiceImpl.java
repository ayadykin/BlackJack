package com.ayadykin.game.services.impl;

import java.util.Objects;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ayadykin.game.blackjack.services.BlackJackService;
import com.ayadykin.game.core.model.Player;
import com.ayadykin.game.core.table.Table;
import com.ayadykin.game.core.table.TableType;
import com.ayadykin.game.core.table.factory.TableFactory;
import com.ayadykin.game.domain.model.User;
import com.ayadykin.game.services.GameService;
import com.ayadykin.game.services.ITableBoardService;
import com.ayadykin.game.services.UserService;

/**
 * Created by Andrey Yadykin on 22.02.2016.
 */

@Stateless
public class GameServiceImpl implements GameService {

    private static final Logger logger = LoggerFactory.getLogger(GameService.class);

    @Inject
    private TableFactory tableFactory;
    @EJB
    private UserService userService;
    @EJB
    private ITableBoardService tableBoardService;
    @EJB
    private BlackJackService blackJackService;
    
    @Override
    public long createGame(TableType.Type gameType) {
        logger.debug(" createGame type : {}", gameType);
        Table table = null;
        switch (gameType) {
        case BLACK_JACK:

            // Get table
            User user = userService.getLoggedUser();

            // Create if not exist
            table = tableBoardService.getTable(user.getId());
            if (Objects.isNull(table)) {
                table = tableFactory.createTable(TableType.Type.BLACK_JACK);
                Player player = createPlayer();
                table.init(player);
                tableBoardService.addTable(table);
                blackJackService.init(table, player);
            }
            break;
        default:
            return 0;
        }
        return table.getId();
    }

    @Override
    public void connectToGame(int tableId) {
        logger.debug(" connectToTable tableId : {}", tableId);

        Table table = tableBoardService.getExistTable(tableId);
        Player player = createPlayer();
        table.addPlayer(player);
        blackJackService.init(table, player);
    }

    private Player createPlayer() {
        logger.debug(" createPlayer ");
        User user = userService.getLoggedUserEntity();
        return new Player(user.getId(), user.getName(), user.getAccount().getBalance());
    }

}
