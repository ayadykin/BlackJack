package com.ayadykin.blackjack.services;

import com.ayadykin.blackjack.model.User;

/**
 * Created by Yadykin Andrii May 13, 2016
 *
 */

public interface UserService {

    public User getLoggedUserEntity();
    
    User getLoggedUser();

}
