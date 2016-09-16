package com.ayadykin.blackjack.core.game;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ayadykin.blackjack.core.table.Table;
import com.ayadykin.blackjack.core.table.TableBoard;
import com.ayadykin.blackjack.model.User;
import com.ayadykin.blackjack.services.UserService;

/**
 * Created by Yadykin Andrii Sep 16, 2016
 *
 */

@Stateless
public class CheckTableExist {

    @Inject
    private TableBoard tableBoard;
    @EJB
    private UserService userService;

    public Table getExistTable() {
        User user = userService.getLoggedUser();
        return tableBoard.getTable(user.getId());
    }
}
