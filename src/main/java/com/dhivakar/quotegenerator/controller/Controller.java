package com.dhivakar.quotegenerator.controller;

import com.dhivakar.quotegenerator.model.Quote;
import com.dhivakar.quotegenerator.service.DAOservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@Slf4j
public class Controller {

    private static final String DEV_PROFILE = "dev";
    private final Random random = new Random();
    @Autowired
    DAOservice service;
    @Value("${spring.profiles.active}")
    private String profile;

    @RequestMapping(value = "/")
    public ResponseEntity<Quote> init() {
        //TODO:Enable more Exception Feature
        log.info("Generating Quote with profile :{}", profile);
        return getrandomquote();
    }

    @GetMapping(value = "/randomQuote",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Quote> getrandomquote() {
        Quote quote;
        if (profile.equalsIgnoreCase(DEV_PROFILE)) {

            quote = service.findbyid(random.nextInt(8));
            if (quote != null) {
                return ResponseEntity.ok(quote);
            }
        } else {
            quote = service.findbyid(random.nextInt(5000));
            if (quote != null) {
                ResponseEntity.ok(quote);
            }
        }
        //TODO: Handle me with appropriate error page
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Quote("Not Found","Not Found"));
    }

    //TODO:Add Another API Mapping to handle insert feature

}
