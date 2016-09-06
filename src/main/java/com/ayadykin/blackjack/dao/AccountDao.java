package com.ayadykin.blackjack.dao;

import com.ayadykin.blackjack.core.model.Account;

/**
 * Created by Andrey Yadykin on 22 ���. 2016 �.
 */

public interface AccountDao {
    Account getAccount(long id);
    Account updateAccount(long id, double bet);
    long createAccount();
}
