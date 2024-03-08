package com.client.services;

import com.google.common.collect.ImmutableMap;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
public class ParamsBuilder {
    private Map<?, ?> pathParams;
    private Map<?, ?> queryParams;

    private ParamsBuilder(Map<?, ?> pathParams, Map<?, ?> queryParams) {
        this.pathParams = pathParams;
        this.queryParams = queryParams;
    }

    public <K, V> void setPathParams(K k1, V v1) {
        this.pathParams = ImmutableMap.of(k1, v1);
    }
}
