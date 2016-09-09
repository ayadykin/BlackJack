package com.ayadykin.blackjack.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.ayadykin.blackjack.dao.UserDao;
import com.ayadykin.blackjack.model.User;
import com.ayadykin.blackjack.utils.Constants;

/**
 * Created by Yadykin Andrii Sep 9, 2016
 *
 */

@Stateless
public class UserDaoImpl implements UserDao {

    @PersistenceContext(unitName = "blackjack")
    private EntityManager em;

    @Override
    public User findByEmail(String email) {
        TypedQuery<User> query = em.createNamedQuery(Constants.FIND_BY_EMAIL, User.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }
}
