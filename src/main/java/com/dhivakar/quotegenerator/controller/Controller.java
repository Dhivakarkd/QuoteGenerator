package com.dhivakar.quotegenerator.controller;

import com.dhivakar.quotegenerator.model.Quote;
import com.dhivakar.quotegenerator.service.DAOservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@Slf4j
public class Controller {

    private static final String DEV_PROFILE ="dev";
    private final Random random = new Random();

    @Value("${spring.profiles.active}")
    private String profile;

    @Autowired
    DAOservice service;

    @RequestMapping(value = "/")
    public Quote init() {
        //TODO:Enable more Exception Feature
        log.info("Generating Quote with profile :{}",profile);
        return getrandomquote();
    }

    @GetMapping(value = "/randomQuote",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Quote getrandomquote() {
        
        if(profile.equalsIgnoreCase(DEV_PROFILE)) {
            return service.findbyid(random.nextInt(8));
        }else{
            return service.findbyid(random.nextInt(5000));
        }
    }

    //TODO:Add Another API Mapping to handle insert feature

}
