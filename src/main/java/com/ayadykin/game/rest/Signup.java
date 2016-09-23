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

import com.ayadykin.game.init.InitImpl;

/**
 * Created by Yadykin Andrii Sep 14, 2016
 *
 */

@Stateless
@Path("/signup")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Signup {
    
    private static final Logger logger = LoggerFactory.getLogger(InitImpl.class);
   
    @EJB
    private InitImpl init;
    
    @GET
    public Response signup() {
        init.Init();
        return Response.ok().build();
    }
}
