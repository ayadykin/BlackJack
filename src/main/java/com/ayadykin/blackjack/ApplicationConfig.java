package com.ayadykin.blackjack;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.ayadykin.blackjack.rest.BlackJackRestService;
import com.ayadykin.blackjack.rest.GameRestService;
import com.ayadykin.blackjack.rest.Signin;
import com.ayadykin.blackjack.rest.Signup;
import com.ayadykin.blackjack.rest.providers.BlackJackProvider;
import com.owlike.genson.ext.jaxrs.GensonJaxRSFeature;

@ApplicationPath("/")
public class ApplicationConfig extends Application {

    // ======================================
    // = Attributes =
    // ======================================

    private final Set<Class<?>> classes;

    // ======================================
    // = Constructors =
    // ======================================

    public ApplicationConfig() {
        HashSet<Class<?>> c = new HashSet<>();

        c.add(BlackJackRestService.class);
        c.add(GameRestService.class);
        c.add(Signin.class);
        c.add(Signup.class);
        c.add(GensonJaxRSFeature.class);
        c.add(BlackJackProvider.class);
        // c.add(AuthenticationFilter.class);

        classes = Collections.unmodifiableSet(c);
    }

    // ======================================
    // = Getters & Setters =
    // ======================================

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }
}