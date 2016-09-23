package com.ayadykin.game.domain.dao;

import com.ayadykin.game.domain.model.Account;

/**
 * Created by Andrey Yadykin on 22 ���. 2016 �.
 */

public interface AccountDao {
    Account getAccount(long id);
    Account updateAccount(long id, double bet);
    long createAccount();
}
