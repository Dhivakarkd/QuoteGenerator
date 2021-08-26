package com.dhivakar.quotegenerator.service;

import com.dhivakar.quotegenerator.model.Quote;
import com.dhivakar.quotegenerator.repository.QuoteRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class DAOservice {

    @Autowired
    QuoteRepository repository;

    public Quote findbyid(int i) {

        Optional<Quote> quoteOptional = repository.findById(i);
        return quoteOptional.orElse(null);
    }

    public boolean insertQuote(@NonNull Quote quote) {

        try {
            Quote updatedQuote = repository.save(quote);
            log.info("Quote Inserted with ID : {}", updatedQuote.getId());
            return true;
        } catch (Exception e) {
            log.error("Exception during Database Operation due to {}", e.getMessage());
            return false;
        }
    }


}
