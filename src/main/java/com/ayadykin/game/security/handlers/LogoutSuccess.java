package com.ayadykin.game.security.handlers;

import java.io.IOException;

import javax.inject.Named;
import javax.json.Json;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.owlike.genson.Genson;

/**
 * Created by Yadykin Andrii Aug 31, 2016
 *
 */

@Named
public class LogoutSuccess implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        String genson = new Genson().serialize(Json.createArrayBuilder().add("logout").add(true).build());
       
        response.getWriter().print(genson);
    }
}
