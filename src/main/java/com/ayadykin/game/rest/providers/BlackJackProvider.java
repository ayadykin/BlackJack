package com.ayadykin.game.rest.providers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.ayadykin.game.blackjack.exceptions.BlackJackException;

/**
 * Created by Andrey Yadykin on 15 бер. 2016 р.
 */

@Provider
public class BlackJackProvider implements ExceptionMapper<BlackJackException> {

    @Override
    public Response toResponse(BlackJackException e) {
        return Response.ok(e.getMessage()).build();
    }

}
