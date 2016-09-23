package com.ayadykin.game.security;

import java.util.Arrays;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ayadykin.game.domain.model.Role;
import com.ayadykin.game.domain.model.User;
import com.ayadykin.game.security.jdbc.UserJdbc;

/**
 * Created by Yadykin Andrii Sep 9, 2016
 *
 */

@Named
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Inject
    private UserJdbc userJdbc;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
        logger.debug(" loadUserByUsername email : {}", email);
        User user = null;
        try {
            user = userJdbc.findByEmail(email);
            if (Objects.isNull(user)) {
                logger.error(" loadUserByUsername user = null!");
                throw new UsernameNotFoundException("user not found");
            }
            user.setAuthorities(Arrays.asList(new Role("user")));
        } catch (Exception e) {
            logger.error(" loadUserByUsername error : " + e.getMessage());
        }
        return user;
    }
}
