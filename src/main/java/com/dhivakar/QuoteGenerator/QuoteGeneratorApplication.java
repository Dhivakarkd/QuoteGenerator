package com.dhivakar.QuoteGenerator;

import com.dhivakar.QuoteGenerator.model.Quote;
import com.dhivakar.QuoteGenerator.repository.QuoteRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class QuoteGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuoteGeneratorApplication.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(QuoteRepository repository){
        return args -> {
            repository.save(new Quote("Nelson Mandela","The greatest glory in living lies not in never falling,but in rising every time we fall."));
            repository.save(new Quote("Nelson Mandela1","1The greatest glory in living lies not in never falling,but in rising every time we fall."));
            repository.save(new Quote("Nelson Mandela2","2The greatest glory in living lies not in never falling,but in rising every time we fall."));
        };
    }

}
