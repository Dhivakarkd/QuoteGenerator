package com.dhivakar.quotegenerator.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@SecurityScheme(
        name = "basicAuth", // can be set to anything
        type = SecuritySchemeType.HTTP,
        scheme = "basic",
        in = SecuritySchemeIn.HEADER
)
public class SwaggerConfiguration {


    private static final String SCHEME_NAME = "basicAuth";
    private static final String SCHEME = "basic";

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("quote-public")
                .pathsToMatch("/quote/randomQuote")
                .build();
    }

    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("quote-admin")
                .pathsToMatch("/quote/**","/image/**")
                .build();
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("Quote Generator API")
                        .description("Quote Generator is an Rest Client built using the Spring Framework . It uses a list of motivational quotes found on the web to generate new random quotes every time it runs")
                        .version("v1")
                        .license(new License().name("Unlicense License").url("https://github.com/Dhivakarkd/QuoteGenerator/blob/master/LICENSE")))
                .externalDocs(new ExternalDocumentation()
                        .description("Dhivakar KD")
                        .url("https://github.com/Dhivakarkd"));
    }


    @Bean
    public InternalResourceViewResolver defaultViewResolver() {
        return new InternalResourceViewResolver();
    }

}
