package com.ayadykin.blackjack.security;

import java.util.Arrays;
import java.util.Objects;

import javax.ejb.EJB;
import javax.inject.Named;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ayadykin.blackjack.dao.UserDao;
import com.ayadykin.blackjack.model.User;

/**
 * Created by Yadykin Andrii Sep 9, 2016
 *
 */

@Named("userService")
public class UserService implements UserDetailsService {

    @EJB
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String email){

        User user = userDao.findByEmail(email);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("user not found");
        }
        user.setAuthorities(Arrays.asList(user.getRole()));

        return user;
    }
}

