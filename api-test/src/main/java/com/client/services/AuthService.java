package com.client.services;

import com.client.config.Configuration;
import io.restassured.specification.RequestSpecification;

public class AuthService extends BaseService {
    private final String key;
    private final String token;

    public AuthService() {
        Configuration config = Configuration.getInstance();
        this.key = config.getAppKey();
        this.token = config.getAppToken();
    }

    @Override
    protected RequestSpecification getRequestSpec() {
        if (key == null || token == null) {
            throw new RuntimeException("Key and token can not be null");
        }
        return super.getRequestSpec().queryParams("key", key).queryParams("token", token);
    }
}
