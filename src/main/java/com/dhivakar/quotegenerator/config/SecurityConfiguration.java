package com.dhivakar.quotegenerator.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    private final String user = System.getenv("SECURITY_USER");
    private final String password = "{noop}" + System.getenv("SECURITY_PASSWORD");

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
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
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
