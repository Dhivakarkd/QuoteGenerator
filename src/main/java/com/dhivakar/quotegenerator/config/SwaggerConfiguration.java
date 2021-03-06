package com.dhivakar.quotegenerator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dhivakar.quotegenerator.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Quote Generator API",
                "Rest Service which provides a restful endpoint for Motivational Quotes",
                "v1",
                "Terms of service",
                new Contact("Dhivakar KD", "https://github.com/Dhivakarkd", "dhivainfo084@gmail.com"),
                "Unlicense License", "https://github.com/Dhivakarkd/QuoteGenerator/blob/master/LICENSE", Collections.emptyList());
    }

}
