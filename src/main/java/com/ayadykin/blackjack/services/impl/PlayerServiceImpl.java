package com.ayadykin.blackjack.services.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ayadykin.blackjack.core.model.Player;
import com.ayadykin.blackjack.core.table.Table;
import com.ayadykin.blackjack.exceptions.BlackJackException;
import com.ayadykin.blackjack.model.User;
import com.ayadykin.blackjack.services.PlayerService;
import com.ayadykin.blackjack.services.UserService;

/**
 * Created by Yadykin Andrii Sep 15, 2016
 *
 */

@Stateless
public class PlayerServiceImpl implements PlayerService{
    private final static Logger logger = LoggerFactory.getLogger(PlayerService.class);
    
    @EJB
    private UserService userService;
    
    public Player createPlayer() {
        User user = userService.getLoggedUserEntity();
        return new Player(user.getId(), user.getName(), user.getAccount().getBalance());
    }

    public Player getLoggedPlayerByTable(Table blackJackTable) {
        User user = userService.getLoggedUser();
        logger.debug(" getPlayer ", user);
        return blackJackTable.getPlayers().stream().filter(p -> p.getId() == user.getId()).findFirst()
                .orElseThrow(() -> new BlackJackException("Logged player not found"));
    }
}

