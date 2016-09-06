package com.ayadykin.blackjack.dao.impl;

import java.util.Random;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.ayadykin.blackjack.core.model.Account;
import com.ayadykin.blackjack.dao.AccountDao;

/**
 * Created by Andrey Yadykin on 15 ���. 2016 �.
 */
@Stateless
public class AccountDaoImpl implements AccountDao {

    @PersistenceContext(unitName = "blackjack")
    private EntityManager em;

    @Override
    public Account getAccount(long id) {
        TypedQuery<Account> query = em.createNamedQuery(Account.FIND_BY_PURSE, Account.class);
        query.setParameter("purseId", id);
        return query.getSingleResult();
    }

    @Override
    public long createAccount() {
        Random rn = new Random();
        int id = rn.nextInt(1000);
        em.persist(new Account(100, id));
        return id;
    }

    @Override
    public Account updateAccount(long id, double bet) {
        Account account =  getAccount(id);
        account.setAccount(account.getAccount() + bet);
        return em.merge(account);
    }
}
