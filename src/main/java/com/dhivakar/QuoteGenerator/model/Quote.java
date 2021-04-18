package com.dhivakar.QuoteGenerator.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Quote")
@Data
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String author;
    private String quote;
}
