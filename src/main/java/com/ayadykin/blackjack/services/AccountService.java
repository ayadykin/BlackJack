package com.ayadykin.blackjack.services;

import com.ayadykin.blackjack.model.Account;

/**
* Created by Andrey Yadykin on 15 ���. 2016 �.
*/

public interface AccountService {
    Account updateAccount(long id, double bet);
    Account getAccount(long id);
    long createAccount();
}

