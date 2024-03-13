package com.client.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ParamsBuilder<String, T> {
    private Map<String, T> pathParams = new HashMap<>();
    private Map<String, T> queryParams = new HashMap<>();

    public ParamsBuilder<String, T> pathParam(String key, T value) {
        this.pathParams.put(key, value);
        return this;
    }

    public ParamsBuilder<String, T> queryParam(String key, T value) {
        this.queryParams.put(key, value);
        return this;
    }
}
