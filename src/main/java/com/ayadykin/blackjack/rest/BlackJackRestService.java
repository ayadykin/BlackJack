package com.ayadykin.blackjack.rest;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ayadykin.blackjack.init.InitImpl;
import com.ayadykin.blackjack.rest.dto.PlayerActionDto;
import com.ayadykin.blackjack.services.GameService;

/**
 * Created by Andrey Yadykin on 22.02.2016.
 */


@Stateless
@Path("/blackjack")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BlackJackRestService {

    private static final Logger logger = LoggerFactory.getLogger(BlackJackRestService.class);

    @EJB
    private InitImpl init;
    @EJB
    private GameService gameService;
    

    @GET
    public Response init() {
        logger.debug("-----");
        init.Init();
        return Response.ok("Ok").build();
    }

    @POST
    public Response init(PlayerActionDto playerActionDto) {
        return Response.ok(gameService.gameAction(playerActionDto)).build();
    }

}
