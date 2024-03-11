package com.client.services;

import com.client.response.ResponseClient;
import io.qameta.allure.Step;

import java.util.Map;

public class BoardService extends BaseService {
    private final String BOARD_PATH = "boards/";
    private final String BOARD_ID_PATH = BOARD_PATH + "{id}";

    public BoardService() {
        this.requestSpecification = super.getRequestSpec()
                .queryParams("key", this.configuration.getAppKey())
                .queryParams("token", this.configuration.getAppToken());
    }

    private ParamsBuilder<String, String> getPathParamBoard(String boardId) {
        return ParamsBuilder.<String, String>builder().pathParams(Map.of("id", boardId)).build();
    }

    @Step
    public ResponseClient deleteBoard(String id) {
        if (id == null) {
            throw new RuntimeException("Board ID cannot be null.");
        }
        return this.delete(BOARD_ID_PATH, getPathParamBoard(id));
    }

    @Step
    public ResponseClient getBoard(String id) {
        if (id == null) {
            throw new RuntimeException("Board ID cannot be null.");
        }
        return this.get(BOARD_ID_PATH, getPathParamBoard(id));
    }

    @Step
    public ResponseClient createBoard(String boardName) {
        ParamsBuilder<String, String> paramsBuilder = ParamsBuilder.<String, String>builder().queryParams(Map.of("name", boardName)).build();
        return this.post(BOARD_PATH, paramsBuilder);
    }
}
