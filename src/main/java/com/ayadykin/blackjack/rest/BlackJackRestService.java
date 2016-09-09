package com.ayadykin.blackjack.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.ayadykin.blackjack.exceptions.BlackJackException;
import com.ayadykin.blackjack.rest.dto.PlayerActionDto;
import com.ayadykin.blackjack.services.GameService;

/**
 * Created by Andrey Yadykin on 22.02.2016.
 */

@Component
@Path("/blackjack")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BlackJackRestService {

    @EJB
    private GameService gameService;

    @GET
    public Response init() throws BlackJackException {
        return null;//Response.ok(gameService.gameAction(0)).build();
    }
    
    @POST
    public Response init(PlayerActionDto playerActionDto) throws BlackJackException {
        return Response.ok(gameService.gameAction(playerActionDto)).build();
    }

}
