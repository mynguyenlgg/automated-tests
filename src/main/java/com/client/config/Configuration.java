package com.client.config;

import com.client.utils.Utils;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

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
            instance.setBaseUrl(Utils.getEnv("BASE_URL", "https://api.trello.com/1/"));
            instance.setAppKey(Utils.getEnv("CLIENT_KEY", StringUtils.EMPTY));
            instance.setAppToken(Utils.getEnv("CLIENT_TOKEN", StringUtils.EMPTY));
        }
        return instance;
    }
}
