package com.ayadykin.blackjack.security;

import java.io.IOException;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * Created by Yadykin Andrii Sep 6, 2016
 *
 */

@Named
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(RestAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {

        logger.error(" commence error {}", authException.getMessage());

       // String genson = new Genson().serialize(Json.createArrayBuilder().add("error").add(authException.getMessage()).build());

        //obj.put("error", authException.getMessage());
        //obj.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().print(authException.getMessage());
    }

}
