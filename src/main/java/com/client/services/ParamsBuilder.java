package com.client.services;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@Builder
public class ParamsBuilder<String, V> {
    private Map<String, V> pathParams;
    private Map<String, V> queryParams;

    public ParamsBuilder<String, V> pathParam(String key, V value) {
        if (this.pathParams == null) {
            this.pathParams = new HashMap<>();
        }
        this.pathParams.put(key, value);
        return this;
    }

    public ParamsBuilder<String, V> queryParam(String key, V value) {
        if (this.queryParams == null) {
            this.queryParams = new HashMap<>();
        }
        this.queryParams.put(key, value);
        return this;
    }
}
