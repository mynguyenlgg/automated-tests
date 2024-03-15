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
            instance.setAppKey(Utils.getEnv("CLIENT_KEY", "55cf0355c635df635828831ef4ea3ab8"));
            instance.setAppToken(Utils.getEnv("CLIENT_TOKEN", "ATTAb468c12820b3de25936d65837c0cc295f5f22213a9d5459db7422e64aad1ea63810E7184"));
        }
        return instance;
    }
}
