package com.ayadykin.blackjack;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.ayadykin.blackjack.rest.BlackJackRestService;
import com.ayadykin.blackjack.rest.providers.BlackJackProvider;
import com.owlike.genson.ext.jaxrs.GensonJaxRSFeature;

@ApplicationPath("/*")
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
        c.add(GensonJaxRSFeature.class);
        c.add(BlackJackProvider.class);
 
        
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