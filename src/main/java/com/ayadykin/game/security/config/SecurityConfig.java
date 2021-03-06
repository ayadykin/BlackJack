package com.ayadykin.game.security.config;

import javax.inject.Inject;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
//import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import com.ayadykin.game.security.RestAuthenticationEntryPoint;
import com.ayadykin.game.security.UserDetailsServiceImpl;
import com.ayadykin.game.security.filter.CsrfHeaderFilter;
import com.ayadykin.game.security.handlers.AccessDenied;
import com.ayadykin.game.security.handlers.LogoutSuccess;

/**
 * Created by Yadykin Andrii May 12, 2016
 *
 */

@ComponentScan(basePackages = "com.ayadykin.game.security")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Inject
    private AuthenticationEntryPoint restAuthenticationEntryPoint;
    @Inject
    private LogoutSuccessHandler logoutSuccess;
    @Inject
    private AccessDeniedHandler accessDenied;
    @Inject
    private UserDetailsService userDetailsServiceImpl;
    @Inject
    private CsrfHeaderFilter csrfHeaderFilter;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.eraseCredentials(true).userDetailsService(userDetailsServiceImpl).passwordEncoder(new StandardPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/favicon.ico", "/resources/**", "/signin", "/signup").permitAll().anyRequest().authenticated()
                .and().csrf().csrfTokenRepository(csrfTokenRepository()).and().httpBasic()
                .authenticationEntryPoint(restAuthenticationEntryPoint).and().exceptionHandling().accessDeniedHandler(accessDenied).and()
                .logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccess).and()
                .addFilterAfter(csrfHeaderFilter, CsrfFilter.class);
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }
}