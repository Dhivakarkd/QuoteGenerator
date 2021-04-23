package com.dhivakar.QuoteGenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class QuoteGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuoteGeneratorApplication.class, args);
    }


}
