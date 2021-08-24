package com.dhivakar.quotegenerator.service;

import com.dhivakar.quotegenerator.model.Quote;
import com.dhivakar.quotegenerator.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DAOservice {

    @Autowired
    QuoteRepository repository;

    public Quote findbyid(int i) {

        Optional<Quote> quoteOptional = repository.findById(i);
        return quoteOptional.orElse(null);
    }


}
