package com.ayadykin.game.services;

import com.ayadykin.game.domain.model.Account;

/**
* Created by Andrey Yadykin on 15 бер. 2016 р.
*/

public interface AccountService {
    Account updateAccount(long id, double bet);
    Account getAccount(long id);
    long createAccount();
}

