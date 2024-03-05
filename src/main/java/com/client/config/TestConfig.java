package com.client.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestConfig {
    private String baseUrl;
    private String basePath;
    private String appKey;
    private String appToken;

    private static TestConfig instance = null;

    private TestConfig() {
        this.baseUrl = "https://api.trello.com/";
        this.basePath = "1/";
        this.appKey = "cdceab0688f6033931a439e74f68bc78";
        this.appToken = "ATTA41c7f6ff603c19b49e385104f6c5a26f8945065fd2bd7bd1e3559d0296ada647CE382428";
    }

    public static TestConfig getInstance() {
        if (instance == null) {
            instance = new TestConfig();
        }
        return instance;
    }

    public String getBaseUrl() {
        return System.getProperty("baseUrl", this.baseUrl);
    }

    public String getBasePath() {
        return System.getProperty("basePath", this.basePath);
    }

    public String getAppKey() {
        return System.getProperty("appKey", this.appKey);
    }

    public String getAppToken() {
        return System.getProperty("appToken", this.appToken);
    }
}
