package com.client.services;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@Builder
public class ParamsBuilder<String, T> {
    private Map<String, T> pathParams;
    private Map<String, T> queryParams;

    public ParamsBuilder(Map<String, T> pathParams, Map<String, T> queryParams) {
        this.pathParams = pathParams != null ? new HashMap<>(pathParams) : new HashMap<>();
        this.queryParams = queryParams != null ? new HashMap<>(queryParams) : new HashMap<>();
    }

    public ParamsBuilder<String, T> pathParam(String key, T value) {
        if (this.pathParams != null) {
            this.pathParams.put(key, value);
        }
        return this;
    }

    public ParamsBuilder<String, T> queryParam(String key, T value) {
        if (this.queryParams != null) {
            this.queryParams.put(key, value);
        }
        return this;
    }
}
