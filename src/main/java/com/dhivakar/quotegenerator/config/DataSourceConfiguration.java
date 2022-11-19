package com.dhivakar.quotegenerator.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile(value = "prod|test")
@Slf4j
public class DataSourceConfiguration {

    private final String url = System.getenv("DATASOURCE_URL");
    private final String username = System.getenv("DATASOURCE_USERNAME");
    private final String password = System.getenv("DATASOURCE_PASSWORD");

    @Bean
    public DataSource getDatasource() {

        log.info("Database URL is {}", url);
        log.info("Database USER is {}", username);
        log.info("Database password is {}", password);


        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(url);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);
        return dataSourceBuilder.build();
    }
}
