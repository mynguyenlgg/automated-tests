package com.client.config;

import lombok.Setter;
@Setter
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

    public String getBaseUrl() {
        return System.getProperty("baseUrl", this.baseUrl);
    }

    public String getAppKey() {
        return System.getProperty("appKey", this.appKey);
    }

    public String getAppToken() {
        return System.getProperty("appToken", this.appToken);
    }
}
