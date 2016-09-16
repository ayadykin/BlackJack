package com.ayadykin.blackjack.init;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import com.ayadykin.blackjack.model.Role;
import com.ayadykin.blackjack.model.User;

/**
 * Created by Yadykin Andrii Sep 12, 2016
 *
 */

@Stateless
public class InitImpl {

    private static final Logger logger = LoggerFactory.getLogger(InitImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void Init() {
        logger.debug("----- Init");
        //PasswordEncoder passwordEncoder = new StandardPasswordEncoder();
        String pass = new StandardPasswordEncoder().encode("user2");
        em.persist(new User("user2", pass, new Role("USER")));
    }
}
