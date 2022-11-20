package com.dhivakar.quotegenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@EnableJpaRepositories
@EnableWebMvc
@EnableWebSecurity
public class QuoteGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuoteGeneratorApplication.class, args);
    }


}
