package com.ayadykin.game.services.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ayadykin.game.blackjack.exceptions.BlackJackException;
import com.ayadykin.game.core.model.Player;
import com.ayadykin.game.core.table.Table;
import com.ayadykin.game.domain.model.User;
import com.ayadykin.game.services.PlayerService;
import com.ayadykin.game.services.UserService;

/**
 * Created by Yadykin Andrii Sep 15, 2016
 *
 */

@Stateless
public class PlayerServiceImpl implements PlayerService {

    private static final Logger logger = LoggerFactory.getLogger(PlayerService.class);

    @EJB
    private UserService userService;


    @Override
    public Player getLoggedPlayerByTable(Table blackJackTable) {
        User user = userService.getLoggedUser();
        logger.debug(" getLoggedPlayerByTable ", user);
        return blackJackTable.getPlayers().stream().filter(p -> p.getId() == user.getId()).findFirst()
                .orElseThrow(() -> new BlackJackException("Logged player not found"));
    }

}
