package com.client.services;

import lombok.Builder;
import lombok.Data;
import java.util.Map;

@Data
@Builder
public class ParamsBuilder<String, V> {
    private Map<String, V> pathParams;
    private Map<String, V> queryParams;
}
