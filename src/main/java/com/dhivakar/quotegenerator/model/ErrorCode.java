package com.dhivakar.quotegenerator.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorCode {

    QT_GEN_API001("QTGENAPI001", "Invalid URL Provided");

    private final String code;
    private final String message;


    @Override
    public String toString() {
        return code + " : " + message;
    }
}
