package com.dhivakar.quotegenerator.service;

import com.dhivakar.quotegenerator.model.QuoteDO;
import com.dhivakar.quotegenerator.repository.QuoteRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class QuoteDAO {

    @Autowired
    QuoteRepository repository;


    /**
     * @param id ID of Quote to be fetched
     * @return {@link QuoteDO} for the provided ID
     */
    public QuoteDO findbyid(int id) {

        Optional<QuoteDO> quoteOptional = repository.findById(id);
        return quoteOptional.orElse(null);
    }

    /**
     * @param quoteDO Quote to be Inserted
     * @return {@literal true} when inserted ,{@literal false} otherwise.
     */
    public boolean insertQuote(@NonNull QuoteDO quoteDO) {

        try {
            QuoteDO updatedQuoteDO = repository.save(quoteDO);
            log.info("Quote Inserted with ID : {}", updatedQuoteDO.getId());
            return true;
        } catch (Exception e) {
            log.error("Exception during Database Operation due to {}", e.getMessage());
            return false;
        }
    }


    public boolean updateQuote(@NonNull QuoteDO quoteDO) {

        try {
            QuoteDO updatedQuoteDO = repository.save(quoteDO);
            log.info("Quote Updated with ID : {}", updatedQuoteDO.getId());
            return true;
        } catch (Exception e) {
            log.error("Exception during Database Operation due to {}", e.getMessage());
            return false;
        }

    }

    public boolean deleteQuote(int quoteID) {

        try {
            repository.deleteById(quoteID);
            log.info("Quote Deleted with ID : {}", quoteID);
            return true;
        } catch (Exception e) {
            log.error("Exception during Database Operation due to {}", e.getMessage());
            return false;
        }

    }

}
