package com.client.config;

import com.client.utils.JsonUtils;
import lombok.Setter;

@Setter
public class TestConfig {
    private String baseUrl;
    private String basePath;
    private String appKey;
    private String appToken;

    private static TestConfig instance = null;

    private TestConfig() {
    }

    public static TestConfig getInstance() {
        if (instance == null) {
            instance = JsonUtils.to("src/main/resources/config/test_config.json", TestConfig.class);
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
