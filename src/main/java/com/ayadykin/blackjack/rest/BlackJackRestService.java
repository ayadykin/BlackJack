package com.ayadykin.blackjack.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ayadykin.blackjack.exceptions.BlackJackException;
import com.ayadykin.blackjack.rest.dto.BetDto;
import com.ayadykin.blackjack.rest.dto.PlayerActionDto;
import com.ayadykin.blackjack.services.GameService;

/**
 * Created by Andrey Yadykin on 22.02.2016.
 */

@Path("/blackjack")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BlackJackRestService {

    @EJB
    private GameService gameService;

    @GET
    public Response init() throws BlackJackException {
        return Response.ok(gameService.initGame(0l)).build();
    }
    
    @GET
    @Path("/{id}")
    public Response init(@PathParam("id") long id) throws BlackJackException {
        return Response.ok(gameService.initGame(id)).build();
    }

    @PUT
    public Response setBet(BetDto bet) {
        return Response.ok(gameService.setBet(bet.getBet())).build();
    }

    @POST
    public Response action(PlayerActionDto playerActionDto) throws BlackJackException {
        return Response.ok(gameService.action(playerActionDto)).build();
    }

}
