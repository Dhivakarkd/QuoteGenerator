package com.dhivakar.quotegenerator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Value("${api.user}")
    private String user;
    @Value("${api.pass}")
    private String password;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(user).password(password).roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Disable default CSRF And CORS Protection
        http
                .csrf().disable()
                .cors().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic();


    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/", "/quote/randomQuote", "/h2-console/**");
    }
}
