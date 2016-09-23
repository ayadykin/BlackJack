package com.ayadykin.game.rest;

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

import com.ayadykin.game.core.table.TableType;
import com.ayadykin.game.services.GameService;

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

    @GET
    @Path("/create")
    public Response createGame() {
        gameService.createGame(TableType.Type.BLACK_JACK);
        return Response.ok().build();
    }

    @GET
    @Path("/connect")
    public Response connetToGame() {
        gameService.connectToGame(1);
        return Response.ok().build();
    }

    @GET
    @Path("/list")
    public Response getList() {
        return Response.ok(TableType.Type.BLACK_JACK.values()).build();
    }

}
