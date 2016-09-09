package com.ayadykin.blackjack.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Created by Yadykin Andrii on 5/30/2016.
 */
public class WebAppInitializer extends AbstractSecurityWebApplicationInitializer {
    public WebAppInitializer() {
        super(SecurityConfig.class);
    }
}
