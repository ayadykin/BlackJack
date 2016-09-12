package com.ayadykin.blackjack.security;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;

import com.ayadykin.blackjack.model.User;
import com.ayadykin.blackjack.utils.Constants;

/**
 * Created by Yadykin Andrii Sep 9, 2016
 *
 */

@Named
@Stateless
public class UserDaoImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDao.class.getName());

    @PersistenceContext
    private EntityManager em;

    @Override   
    @Transactional
    public User findByEmail(String email) {
        logger.debug(" findByEmail ");
        User user = null;
        try {
            TypedQuery<User> query = em.createNamedQuery(Constants.FIND_BY_EMAIL, User.class);
            query.setParameter("email", email);

            user = query.getSingleResult();
        } catch (Exception e) {
            logger.error(" findByEmail error : " + e);
        }
        return user;
    }
}
