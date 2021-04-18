package com.dhivakar.QuoteGenerator.service;

import com.dhivakar.QuoteGenerator.model.Quote;
import com.dhivakar.QuoteGenerator.model.QuoteUI;
import com.dhivakar.QuoteGenerator.repository.QuoteRepository;

import java.util.ArrayList;
import java.util.List;


public class daoService {


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
