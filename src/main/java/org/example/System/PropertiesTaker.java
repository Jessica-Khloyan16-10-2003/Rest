package org.example.System;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesTaker {
    private static final String PROPERTIES_FILE_NAME = "D:\\REST_DZ\\src\\main\\java\\resources\\database.properties";
    private static final Properties PROPERTIES = new Properties();
    public static String getProperties(String propertyName) {
        try {
            PROPERTIES.load(new FileReader(PROPERTIES_FILE_NAME));
            return PROPERTIES.getProperty(propertyName);
        } catch (IOException e) {
            throw new RuntimeException("Данное свойство не найдено в файле");
        }
    }
}
