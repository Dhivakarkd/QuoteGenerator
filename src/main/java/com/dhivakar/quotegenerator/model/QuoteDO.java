package com.dhivakar.quotegenerator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Quote")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuoteDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String author;
    private String quote;

    public QuoteDO(String author, String quote) {
        this.author = author;
        this.quote = quote;
    }
}
