package com.ayadykin.blackjack.rest;

import java.security.Principal;
import java.util.Objects;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;

import com.ayadykin.blackjack.model.Account;
import com.ayadykin.blackjack.model.User;

/**
 * Created by Yadykin Andrii Sep 9, 2016
 *
 */

@Stateless
@Path("/signin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Signin {

    private static final Logger logger = Logger.getLogger(Signin.class.getName());

    @GET
    public Response user() {
        logger.debug("------ : ");
        AccountDto accountDto = null;

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (Objects.nonNull(user)) {
            accountDto = new AccountDto(user.getPassword(), user.getEmail());
        }
        return Response.ok(accountDto).build();
    }

}
