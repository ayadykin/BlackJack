package com.ayadykin.blackjack.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ayadykin.blackjack.rest.dto.GameActionDto;
import com.ayadykin.blackjack.services.GameService;

/**
 * Created by Yadykin Andrii Sep 15, 2016
 *
 */

@Stateless
@Path("/game")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GameRestService {

    private static final Logger logger = LoggerFactory.getLogger(GameRestService.class);

    @EJB
    private GameService gameService;

    @POST
    public Response gameAction(GameActionDto gameActionDto) {
        gameService.gameType(gameActionDto.getGameActions());
        return Response.ok().build();
    }
}
