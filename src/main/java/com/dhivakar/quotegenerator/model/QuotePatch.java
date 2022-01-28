package com.dhivakar.quotegenerator.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
@Data
public class QuotePatch {

    @NotEmpty
    private int id;
    private String quote;
    private String author;
}
