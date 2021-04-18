package com.dhivakar.QuoteGenerator.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {




    @RequestMapping(value = "/")
    public String init() {
        return "hello world";

    }
}
