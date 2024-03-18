package com.client.param;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
public class ParamsBuilder<String, T> {
    private Map<String, T> pathParams, queryParams;

    public ParamsBuilder() {
        pathParams = new HashMap<>();
        queryParams = new HashMap<>();
    }

    public ParamsBuilder<String, T> pathParam(String key, T value) {
        this.pathParams.put(key, value);
        return this;
    }

    public ParamsBuilder<String, T> queryParam(String key, T value) {
        this.queryParams.put(key, value);
        return this;
    }
}
