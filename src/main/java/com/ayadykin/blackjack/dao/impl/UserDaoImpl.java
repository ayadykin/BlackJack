package com.ayadykin.blackjack.dao.impl;

import javax.ejb.Stateless;

import com.ayadykin.blackjack.dao.UserDao;
import com.ayadykin.blackjack.model.User;

/**
 * Created by Yadykin Andrii Sep 14, 2016
 *
 */

@Stateless
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }
}
