package com.ayadykin.blackjack.rest;

import java.util.Objects;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ayadykin.blackjack.init.InitImpl;
import com.ayadykin.blackjack.model.User;
import com.ayadykin.blackjack.rest.dto.AccountDto;
import com.ayadykin.blackjack.services.UserService;

/**
 * Created by Yadykin Andrii Sep 9, 2016
 *
 */

@Stateless
@Path("/signin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Signin {

    private static final Logger logger = LoggerFactory.getLogger(InitImpl.class);

    @EJB
    private UserService userService;

    @GET
    public Response signin() {
        logger.debug(" signin ");
        AccountDto accountDto = null;

        User user = userService.getLoggedUser();
        if (Objects.nonNull(user)) {
            accountDto = new AccountDto(user.getId(), user.getEmail(), user.getName());
        }
        logger.debug(" signin accountDto : {}", accountDto);
        return Response.ok(accountDto).build();
    }
}
