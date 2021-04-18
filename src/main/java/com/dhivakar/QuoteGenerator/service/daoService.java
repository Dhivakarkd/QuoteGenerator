package com.dhivakar.QuoteGenerator.service;

import com.dhivakar.QuoteGenerator.model.Quote;
import com.dhivakar.QuoteGenerator.model.QuoteUI;
import com.dhivakar.QuoteGenerator.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class daoService {

    @Autowired
    QuoteRepository repository;

    public List<QuoteUI> findall(){
        Iterable<Quote> quotes = repository.findAll();
        List<QuoteUI> quoteList = new ArrayList<>();
        for (Quote q : quotes) {
            quoteList.add(new QuoteUI(q.getAuthor(), q.getQuote()));
        }
        return quoteList;
    }
}
