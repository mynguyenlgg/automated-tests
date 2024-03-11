package com.client.config;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class Configuration {
    private String baseUrl;
    private String appKey;
    private String appToken;

    private static Configuration instance = null;

    private Configuration() {

    }

    public static Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
            instance.setBaseUrl("https://api.trello.com/1/");
            instance.setAppKey(System.getenv("CLIENT_KEY"));
            instance.setAppToken(System.getenv("CLIENT_TOKEN"));
        }
        return instance;
    }
}
