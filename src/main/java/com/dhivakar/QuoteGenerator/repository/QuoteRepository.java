package com.dhivakar.QuoteGenerator.repository;

import com.dhivakar.QuoteGenerator.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;


public interface QuoteRepository extends JpaRepository<Quote, Integer> {

}
