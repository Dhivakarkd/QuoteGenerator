package com.dhivakar.quotegenerator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    public static final String[] AUTH_WHITLIST = {
            "/",
            "/quote/randomQuote",
            "/h2-console/**",
            "/v2/api-docs"
    };
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
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authorizeRequests()
                .antMatchers(AUTH_WHITLIST).permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();


    }

}
