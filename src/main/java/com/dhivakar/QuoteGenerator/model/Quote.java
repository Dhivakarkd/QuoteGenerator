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
    public Quote(){

    }
    public Quote(String author, String quote) {
        this.author=author;
        this.quote=quote;
    }
    public Quote(long id,String author, String quote) {
        this.id=id;
        this.author=author;
        this.quote=quote;
    }
}
