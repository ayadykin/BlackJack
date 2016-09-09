package com.ayadykin.blackjack.config;

import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import com.ayadykin.blackjack.security.AuthenticationFailure;
import com.ayadykin.blackjack.security.CsrfHeaderFilter;
import com.ayadykin.blackjack.security.LogoutSuccess;
import com.ayadykin.blackjack.security.RestAuthenticationEntryPoint;
import com.ayadykin.blackjack.security.UserService;

/**
 * Created by Yadykin Andrii May 12, 2016
 *
 */

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /*@Inject
    @Named("restAuthenticationEntryPoint")
    private RestAuthenticationEntryPoint authenticationEntryPoint;
    @Inject
    @Named("logoutSuccess")
    private LogoutSuccess logoutSuccess;
    @Inject
    @Named("authenticationFailure")
    private AuthenticationFailure authenticationFailure;
    @Inject
    @Named("userService")
    private UserService userService;*/

    //@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //auth.eraseCredentials(true).userDetailsService(userService).passwordEncoder(new StandardPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/favicon.ico", "/resources/**", "/signin", "/signup").permitAll().anyRequest().authenticated()
                .and().csrf().csrfTokenRepository(csrfTokenRepository()).and().httpBasic();
                //.authenticationEntryPoint(authenticationEntryPoint).and().exceptionHandling().accessDeniedHandler(authenticationFailure)
                //.and().logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccess).and()
                //.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }
}