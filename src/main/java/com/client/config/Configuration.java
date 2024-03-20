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
            instance.setAppKey("cdceab0688f6033931a439e74f68bc78");
            instance.setAppToken("ATTA41c7f6ff603c19b49e385104f6c5a26f8945065fd2bd7bd1e3559d0296ada647CE382428");
        }
        return instance;
    }
}
