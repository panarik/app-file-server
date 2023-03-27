package com.github.panarik.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyConfig implements Config {

    private final Properties properties = new Properties();

    public PropertyConfig() {
        try {
            properties.load(new FileInputStream("src/main/resources/config.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getPath() {
        return properties.getProperty("SERVER.PATH");
    }

    public int getPort() {
        return Integer.parseInt(properties.getProperty("SERVER.PORT"));
    }
}
