package com.ayadykin.game.services.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.ayadykin.game.domain.dao.AccountDao;
import com.ayadykin.game.domain.model.Account;
import com.ayadykin.game.services.AccountService;

/**
* Created by Andrey Yadykin on 15 бер. 2016 р.
*/

@Stateless
public class AccountServiceImpl implements AccountService{

    @EJB
    private AccountDao daoService;
    
    @Override
    public Account updateAccount(long id, double bet) {              
        return daoService.updateAccount(id, bet);     
    }

    @Override
    public Account getAccount(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long createAccount() {
        // TODO Auto-generated method stub
        return 0;
    }

}

