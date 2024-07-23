package org.example.System;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions. * ;

public class PropertiesTakerTest {

    private static PropertiesTaker propertiesTaker;

    @BeforeAll
    public static void setup() {
        propertiesTaker = new PropertiesTaker();
    }

    @Test
    void testGetPropertiesWithValidPropertyName() {
        final String URL = "url";
        String expectedValue = "jdbc:mysql://127.0.0.1:3306/rest?serverTimezone=Europe/Moscow";
        String actualValue = propertiesTaker.getProperties(URL);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void testGetPropertiesWithInvalidPropertyName() {
        try {
            propertiesTaker.getProperties("invalid.property");
        } catch (RuntimeException e) {
            assertTrue(e.getMessage().contains("Данное свойство не найдено в файле"));
        }
    }
}
