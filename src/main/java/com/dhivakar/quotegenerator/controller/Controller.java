package com.dhivakar.quotegenerator.controller;

import com.dhivakar.quotegenerator.model.Quote;
import com.dhivakar.quotegenerator.service.DAOservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
public class Controller {


    @Autowired
    DAOservice service;

    @RequestMapping(value = "/")
    public String init() {
        return "hello world";
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Quote> getquote() {


        return service.findall();
    }

    @GetMapping(value = "/randomQuote",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Quote getrandomquote() {
        Random random = new Random();

        return service.findbyid(random.nextInt(5000));
    }

}
