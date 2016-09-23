package com.ayadykin.game.rest;

import java.util.concurrent.Callable;

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

import com.ayadykin.game.blackjack.services.BlackJackService;
import com.ayadykin.game.rest.dto.PlayerActionDto;
import com.ayadykin.game.rest.dto.ResponseDto;
import com.ayadykin.game.rest.dto.StatusDto;

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
    private BlackJackService blackJackService;

    @GET
    public Response newGame() {
        return Response.ok().build();
    }
    
    @GET
    @Path("/leave")
    public Response leaveGame() {
        blackJackService.leaveGame(1);
        return Response.ok().build();
    }

    @POST
    public Response gameAction(PlayerActionDto playerActionDto) {
        return Response.ok(blackJackService.gameAction(playerActionDto)).build();
    }

    @GET
    @Path("/status")
    public Response status() {
        StatusDto status = null;
        try {
            Callable<StatusDto> f = blackJackService::gameStatus;
            status = f.call();
        } catch (Exception e) {
            logger.error(" status error : {}", e.getMessage());
        }
        return Response.ok(status).build();
    }

    @GET
    @Path("/getCards")
    public Response getCards() {
        ResponseDto responseDto = null;
        try {
            Callable<ResponseDto> f = blackJackService::getCards;
            responseDto = f.call();
        } catch (Exception e) {
            logger.error(" status error : {}", e.getMessage());
        }
        return Response.ok(responseDto).build();
    }

}
