package com.dhivakar.quotegenerator.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommonUtilTest {

    @Test
    void isValidURL() {

        CommonUtil util = new CommonUtil();

        String urlString = "https://i.pinimg.com/564x/3d/7e/54/3d7e544b68f75c14493d9d24ef429d81.jpg";
        assertTrue(util.isValidURL(urlString));
    }

    @Test
    void isInValidURL() {

        CommonUtil util = new CommonUtil();

        String urlString = "http:www.example.com/main.html";
        assertFalse(util.isValidURL(urlString));
    }
}