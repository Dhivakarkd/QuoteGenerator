package com.dhivakar.QuoteGenerator.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @RequestMapping(value = "/get")
    public ResponseEntity<Void> gethelp(){
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }
    @RequestMapping(value = "/")
    public ResponseEntity<String> init(){
        return ResponseEntity.status(HttpStatus.OK)
                .header("hello world").build();
    }


}
