package com.client.services;

import com.client.response.ResponseClient;
import io.qameta.allure.Step;

import java.util.Map;

public class BoardService extends BaseService {
    private final String PATH = "boards/";

    public BoardService () {
        this.requestSpecification = super.getRequestSpec()
                .queryParams("key", this.configuration.getAppKey())
                .queryParams("token", this.configuration.getAppToken());
    }

    @Step
    public ResponseClient deleteBoard(String id) {
        if (id != null) {
            throw new RuntimeException("Board ID cannot be null.");
        }
        ParamsBuilder paramsBuilder = ParamsBuilder.builder().pathParams(Map.of("id", id)).build();
        return this.delete(paramsBuilder, PATH + "{id}");
    }

    @Step
    public ResponseClient getBoard(String id) {
        if (id == null) {
            throw new RuntimeException("Board ID cannot be null.");
        }
        return this.get(Map.of("id", id), PATH + "{id}");
    }

    @Step
    public ResponseClient createBoard(String boardName) {
        ParamsBuilder paramsBuilder = ParamsBuilder.builder().queryParams(Map.of("name", boardName)).build();
        return this.post(paramsBuilder, PATH);
}
}
