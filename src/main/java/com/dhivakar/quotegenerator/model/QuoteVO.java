package com.dhivakar.quotegenerator.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuoteVO {

    private String quote;
    private String author;

}

