package com.dhivakar.QuoteGenerator.controller;

import com.dhivakar.QuoteGenerator.model.Quote;
import com.dhivakar.QuoteGenerator.model.QuoteUI;
import com.dhivakar.QuoteGenerator.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    @Autowired
    QuoteRepository repository;

    @RequestMapping(value = "/get")
    public List<QuoteUI> gethelp() {
        Iterable<Quote> quotes = repository.findAll();
        List<QuoteUI> quoteList = new ArrayList<>();
        for (Quote q : quotes) {
            quoteList.add(new QuoteUI(q.getAuthor(), q.getQuote()));
        }
        return quoteList;
    }

    @RequestMapping(value = "/")
    public String init() {
        return "hello world";

    }
}
