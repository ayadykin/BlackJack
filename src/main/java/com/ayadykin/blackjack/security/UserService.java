package com.ayadykin.blackjack.security;

import java.util.Arrays;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ayadykin.blackjack.model.Role;
import com.ayadykin.blackjack.model.User;

/**
 * Created by Yadykin Andrii Sep 9, 2016
 *
 */

@Named
public class UserService implements UserDetailsService {

    private static final Logger logger = Logger.getLogger(UserService.class.getName());

    @Inject
    private UserDao userDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
        logger.debug("-------- : " + email);
        User user = null;
        try {
            if(Objects.isNull(userDao)){
                logger.debug("userDao -------- null: ");
            }
             user = userDao.findByEmail(email);
            logger.debug("userDao -------- : " + user.getEmail());
            if (Objects.isNull(user)) {
                throw new UsernameNotFoundException("user not found");
            }
            user.setAuthorities(Arrays.asList(new Role("user")));
        } catch (Exception e) {
            logger.error(" loadUserByUsername error : " + e.getMessage());
        }
        return user;
    }
}
