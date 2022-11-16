package com.dhivakar.quotegenerator.controller;

import com.dhivakar.quotegenerator.model.QuoteDO;
import com.dhivakar.quotegenerator.model.QuotePatch;
import com.dhivakar.quotegenerator.model.QuoteVO;
import com.dhivakar.quotegenerator.service.DAOservice;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@Slf4j
public class QuoteController {

    private static final String DEV_PROFILE = "dev";
    private final Random random = new Random();
    @Autowired
    DAOservice daoservice;
    @Value("${spring.profiles.active}")
    private String profile;


    @GetMapping(value = "/quote/randomQuote",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QuoteVO> getrandomquote() {
        QuoteDO quoteDO;
        if (profile.equalsIgnoreCase(DEV_PROFILE)) {

            quoteDO = daoservice.findbyid(random.nextInt(12));
            if (quoteDO != null) {

                QuoteVO quoteVO = QuoteVO.builder()
                        .quote(quoteDO.getQuote())
                        .author(quoteDO.getAuthor())
                        .build();

                quoteVO = validateQuote(quoteVO);

                return ResponseEntity.ok(quoteVO);
            }
        } else {
            quoteDO = daoservice.findbyid(random.nextInt(5000));
            if (quoteDO != null) {

                QuoteVO quoteVO = QuoteVO.builder()
                        .quote(quoteDO.getQuote())
                        .author(quoteDO.getAuthor())
                        .build();
                return ResponseEntity.ok(quoteVO);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(QuoteVO.builder().quote("Not Found").author("Not Found").build());
    }

    private QuoteVO validateQuote(QuoteVO quoteVO) {

        if(StringUtils.isAllEmpty(quoteVO.getAuthor()) || StringUtils.isAllBlank(quoteVO.getAuthor())){
            quoteVO.setAuthor("Unknown");
        }

        return quoteVO;

    }


    @PostMapping(value = "/quote")
    public ResponseEntity<String> insertQuote(@RequestBody QuoteVO quoteVO) {
        log.info("insert method called");
        QuoteDO quoteDO = new QuoteDO(quoteVO.getAuthor(), quoteVO.getQuote());
        boolean status = daoservice.insertQuote(quoteDO);

        if (status) {
            return ResponseEntity.accepted().body("Insertion Successful");
        } else {
            return ResponseEntity.unprocessableEntity().body("Insertion Failed");
        }
    }

    @PatchMapping(value = "/quote")
    public ResponseEntity<String> updateQuote(@RequestBody QuotePatch quotePatch) {


        boolean doesUpdateRequired= false;

        log.info("Trying to Update Quote for id : {}", quotePatch.getId());

        QuoteDO optionalQuote = daoservice.findbyid(quotePatch.getId());


        if (optionalQuote != null) {

            if (quotePatch.getQuote() != null) {
                optionalQuote.setQuote(quotePatch.getQuote());
                doesUpdateRequired = true;
            }
            if (quotePatch.getAuthor() != null) {
                optionalQuote.setAuthor(quotePatch.getAuthor());
                doesUpdateRequired = true;
            }

            if(doesUpdateRequired) {
                boolean status = daoservice.updateQuote(optionalQuote);

                if (status) {
                    String updateText = "Quote/Author Updated for Id : " + quotePatch.getId() + " in Database";

                    log.info(updateText);

                    return ResponseEntity.accepted().body(updateText);
                } else {

                    return ResponseEntity.unprocessableEntity().body("Error Occurred at our Side Please try with other ID");
                }
            }else{

                return ResponseEntity.unprocessableEntity().body("No Further Input Provided to Update Entity , Skipping Update");
            }
        } else {

            String errorText = "No Quote found for Id : " + quotePatch.getId() + " in Database";
            log.info(errorText);
            return ResponseEntity.unprocessableEntity().body(errorText);
        }

    }

    @DeleteMapping("/quote")
    public ResponseEntity<String> deleteQuoteById(@RequestParam int id) {

        boolean status = daoservice.deleteQuote(id);

        if (status) {

            String info = "Deletion of Quote with ID : " + id + " Successful";
            return ResponseEntity.ok().body(info);

        } else {

            String info = "Deletion of Quote with ID : " + id + " UnSuccessful";
            return ResponseEntity.unprocessableEntity().body(info);
        }

    }

}
