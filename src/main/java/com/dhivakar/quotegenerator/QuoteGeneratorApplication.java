package com.dhivakar.quotegenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableJpaRepositories
@EnableWebMvc
public class QuoteGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuoteGeneratorApplication.class, args);
    }


}
