package com.ayadykin.game.services.impl;

import java.util.Objects;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import com.ayadykin.game.domain.dao.UserDao;
import com.ayadykin.game.domain.exceptions.UserNotFoundException;
import com.ayadykin.game.domain.model.User;
import com.ayadykin.game.services.UserService;

/**
 * Created by Yadykin Andrii May 16, 2016
 *
 */

@Stateless
public class UserServiceImpl implements UserService {

    private final static Logger logger = LoggerFactory.getLogger(UserService.class);

    @EJB
    private UserDao userDao;

    @Override
    @Transactional
    public User getLoggedUserEntity() {
        long userId = getLoggedUser().getId();
        logger.debug(" getLoggedUserEntity userId : {} ", userId);

        User user = userDao.find(userId);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException("Can't find logged user in db");
        }
        logger.debug(" getLoggedUserEntity user : {} ", user);
        return user;
    }

    @Override
    public User getLoggedUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
