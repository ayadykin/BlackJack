package com.ayadykin.game.services;

import com.ayadykin.game.domain.model.Account;

/**
* Created by Andrey Yadykin on 15 ���. 2016 �.
*/

public interface AccountService {
    Account updateAccount(long id, double bet);
    Account getAccount(long id);
    long createAccount();
}

