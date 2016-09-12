package com.ayadykin.blackjack.config;

import javax.inject.Inject;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
//import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import com.ayadykin.blackjack.security.AuthenticationFailure;
import com.ayadykin.blackjack.security.CsrfHeaderFilter;
import com.ayadykin.blackjack.security.RestAuthenticationEntryPoint;

/**
 * Created by Yadykin Andrii May 12, 2016
 *
 */

@ComponentScan(basePackages = "com.ayadykin.blackjack.security")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //@Autowired
    //private RestAuthenticationEntryPoint authenticationEntryPoint;
    @Inject
    private LogoutSuccessHandler logoutSuccess;
    /*@Inject
    private AuthenticationFailure authenticationFailure;*/
    @Inject
    private UserDetailsService userService;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.eraseCredentials(true).userDetailsService(userService).passwordEncoder(new StandardPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/favicon.ico", "/resources/**", "/signin", "/signup").permitAll().anyRequest().authenticated()
                .and().csrf().csrfTokenRepository(csrfTokenRepository()).and().httpBasic()
                .authenticationEntryPoint(new RestAuthenticationEntryPoint()).and()
                .exceptionHandling().accessDeniedHandler(new AuthenticationFailure())
                .and().logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccess).and()
                .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }
}