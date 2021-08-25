package com.dhivakar.quotegenerator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

@SpringBootTest
@Profile(value = "dev")
class QuoteGeneratorApplicationTests {

    @Test
    void contextLoads() {
    }

}
