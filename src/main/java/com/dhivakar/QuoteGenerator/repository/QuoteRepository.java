package com.dhivakar.QuoteGenerator.repository;

import com.dhivakar.QuoteGenerator.model.Quote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends CrudRepository<Quote, Integer> {

    Quote findbyAuthor(String author);
}
