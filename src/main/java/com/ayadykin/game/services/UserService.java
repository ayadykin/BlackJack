package com.ayadykin.game.services;

import com.ayadykin.game.domain.model.User;

/**
 * Created by Yadykin Andrii May 13, 2016
 *
 */

public interface UserService {

    public User getLoggedUserEntity();
    
    User getLoggedUser();

}
