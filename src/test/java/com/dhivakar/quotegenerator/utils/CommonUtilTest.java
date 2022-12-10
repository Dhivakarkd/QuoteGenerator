package com.dhivakar.quotegenerator.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommonUtilTest {

    @Test
    void isValidURL() {

        CommonUtil util = new CommonUtil();

        String urlString = "http://MVSXX.COMPANY.COM:44455/CICSPLEXSM//TOXTETH/VIEW/EYUSTARTPROGRAM.TABULAR\n" +
                "       ?FILTERC=1";
        assertTrue(util.isValidURL(urlString));
    }

    @Test
    void isInValidURL() {

        CommonUtil util = new CommonUtil();

        String urlString = "http:www.example.com/main.html";
        assertFalse(util.isValidURL(urlString));
    }
}