package com.dhivakar.quotegenerator.repository;

import com.dhivakar.quotegenerator.model.QuoteDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<QuoteDO, Integer> {

}
