package com.dhivakar.QuoteGenerator.controller;

import com.dhivakar.QuoteGenerator.model.QuoteUI;
import com.dhivakar.QuoteGenerator.service.daoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    daoService service;

    @RequestMapping(value = "/get")
    public List<QuoteUI> gethelp() {
        return service.findall();
    }

    @RequestMapping(value = "/")
    public String init() {
        return "hello world";

    }
}
