package com.client.services;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.Map;

@Setter
@Getter
@Builder
public class ParamsBuilder<String, V> {
    private Map<String, V> pathParams;
    private Map<String, V> queryParams;
}
