package com.ayadykin.blackjack.security;

import java.io.IOException;

import javax.inject.Named;
import javax.json.Json;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.owlike.genson.Genson;

/**
 * Created by Yadykin Andrii Aug 3, 2016
 *
 */

@Named("authenticationFailure")
public class AuthenticationFailure implements AccessDeniedHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestAuthenticationEntryPoint.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {

        String genson = new Genson().serialize(Json.createArrayBuilder().add("error").add(accessDeniedException.getMessage()).build());
        /*
         * JSONObject obj = new JSONObject(); obj.put("error",
         * accessDeniedException.getMessage()); obj.put("status",
         * HttpServletResponse.SC_FORBIDDEN);
         */
        response.getWriter().print(genson);
    }

}
