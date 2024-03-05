package com.client.config;

import com.client.utils.PropertyUtils;

import java.util.Properties;

public class PropertiesConfigLoader {
    private final Properties properties;
    private static PropertiesConfigLoader propertiesConfigLoader;

    private PropertiesConfigLoader() {
        properties = PropertyUtils.propertyLoader("src/test/resources/config.properties");
    }

    public static PropertiesConfigLoader getInstance() {
        if (propertiesConfigLoader == null) {
            propertiesConfigLoader = new PropertiesConfigLoader();
        }
        return propertiesConfigLoader;
    }

    public String getProperty(String prop) {
        String value = properties.getProperty(prop);
        if (value != null) return value;
        else throw new RuntimeException(String.format("property %s is not specified in the config.properties file", prop));
    }
}
