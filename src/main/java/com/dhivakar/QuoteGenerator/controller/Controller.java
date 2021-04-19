package com.dhivakar.QuoteGenerator.controller;

import com.dhivakar.QuoteGenerator.model.Quote;
import com.dhivakar.QuoteGenerator.service.DAOservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public List<Quote> getquote(){
        return service.findall();
    }

}
