package com.dhivakar.quotegenerator.controller;

import com.dhivakar.quotegenerator.model.QuoteDO;
import com.dhivakar.quotegenerator.model.QuoteVO;
import com.dhivakar.quotegenerator.service.DAOservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping(value = "/",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QuoteDO> init() {
        //TODO:Enable more Exception Feature
        log.info("Generating Quote with profile :{}", profile);
        return getrandomquote();
    }

    @GetMapping(value = "/randomQuote",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QuoteDO> getrandomquote() {
        QuoteDO quoteDO;
        if (profile.equalsIgnoreCase(DEV_PROFILE)) {

            quoteDO = service.findbyid(random.nextInt(8));
            if (quoteDO != null) {
                return ResponseEntity.ok(quoteDO);
            }
        } else {
            quoteDO = service.findbyid(random.nextInt(5000));
            if (quoteDO != null) {
                ResponseEntity.ok(quoteDO);
            }
        }
        //TODO: Handle me with appropriate error page
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new QuoteDO("Not Found", "Not Found"));
    }

    @PostMapping(value = "/insert")
    public ResponseEntity<String> insertQuote(@RequestBody QuoteVO quoteVO) {
        log.info("insert method called");
        QuoteDO quoteDO = new QuoteDO(quoteVO.getAuthor(), quoteVO.getQuote());
        boolean status = service.insertQuote(quoteDO);

        if (status) {
            return ResponseEntity.accepted().body("Insertion Successful");
        } else {
            return ResponseEntity.unprocessableEntity().body("Insertion Failed");
        }
    }

}
