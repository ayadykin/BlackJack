package com.ayadykin.blackjack.rest;

import static org.junit.Assert.assertEquals;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

//import org.codehaus.jettison.json.JSONException;
//import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

import com.ayadykin.game.rest.dto.ResponseDto;

/**
 * Created by Andrey Yadykin on 15 бер. 2016 р.
 */

public class BlackJackRestTest {//extends JerseyTest {

    private static URI uri = UriBuilder.fromUri("http://localhost/game/blackjack").port(8080).build();
    private static Client client = ClientBuilder.newClient();

   /* @Override
    protected AppDescriptor configure() {
        return new WebAppDescriptor.Builder().build();
    }

    @Test
    public void initGameTest() throws JSONException {
        // Response response = client.target(uri).request().get();
        // System.out.println(response.getEntity());
        // assertEquals(Response.ok().build(), response.getStatusInfo());

        WebResource webResource = client().resource("http://localhost:8080/game/blackjack/501");
        JSONObject json = webResource.accept(MediaType.APPLICATION_JSON).get(
                JSONObject.class);
        System.out.println(json);
        //assertEquals("NEXT_MOVE", json.getString("blackJackResponce"));
        //assertEquals("461", ((JSONObject) json.get("players")).get("id"));

    }*/
}
