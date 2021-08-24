package com.dhivakar.quotegenerator.controller;

import com.dhivakar.quotegenerator.model.Quote;
import com.dhivakar.quotegenerator.service.DAOservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class Controller {

    @Autowired
    DAOservice service;

    @RequestMapping(value = "/")
    public Quote init() {
        //TODO:Enable more Exception Feature
        return getrandomquote();
    }

    @GetMapping(value = "/randomQuote",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Quote getrandomquote() {
        Random random = new Random();

        return service.findbyid(random.nextInt(5000));
    }

    //TODO:Add Another API Mapping to handle insert feature

}
