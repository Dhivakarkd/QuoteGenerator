package com.dhivakar.QuoteGenerator.service;

import com.dhivakar.QuoteGenerator.model.Quote;
import com.dhivakar.QuoteGenerator.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DAOservice {

    @Autowired
    QuoteRepository repository;

    public List<Quote> findall() {
        Iterable<Quote> quotes = repository.findAll();
        List<Quote> quoteList = new ArrayList<>();
        for (Quote q : quotes) {
            quoteList.add(new Quote(q.getId(), q.getAuthor(), q.getQuote()));
        }
        return quoteList;
    }

    public Quote findbyid(int i) {

        Optional<Quote> quoteOptional = repository.findById(i);
        return quoteOptional.orElse(null);
    }


}
