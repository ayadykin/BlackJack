package com.ayadykin.blackjack.security;

import com.ayadykin.blackjack.model.User;

/**
 * Created by Yadykin Andrii Sep 9, 2016
 *
 */

public interface UserDao {
    User findByEmail(String email);
}

