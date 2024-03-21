package com.client.config;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Configuration {
    private String baseUrl;
    private String appKey;
    private String appToken;
    private boolean showPassedTestsLogs;

    private static Configuration instance = null;

    private Configuration() {
    }

    public static Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
            instance.setBaseUrl(System.getProperty("BASE_URL", "https://api.trello.com/1/"));
            instance.setAppKey(System.getProperty("CLIENT_KEY"));
            instance.setAppToken(System.getProperty("CLIENT_TOKEN"));
            instance.setShowPassedTestsLogs(false);
        }
        return instance;
    }
}
