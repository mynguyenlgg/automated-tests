package com.client.services;

import com.client.model.Board;
import com.client.param.ParamsBuilder;
import com.client.response.ResponseClient;
import com.client.utils.JsonUtils;
import io.qameta.allure.Step;

public class BoardService extends AuthService {
    private final String BOARD_PATH = "boards/";
    private final String BOARD_ID_PATH = BOARD_PATH + "{id}";

    public BoardService() {
        super();
    }

    private ParamsBuilder<String, String> getPathParamBoard(String boardId) {
        return this.getParamsBuilder().pathParam("id", boardId);
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
    public ResponseClient updateBoard(Board board) {
        if (board.getId() == null) {
            throw new RuntimeException("Board ID cannot be null.");
        }
        return this.put(BOARD_ID_PATH, getPathParamBoard(board.getId()), JsonUtils.toJsonObject(board));
    }

    @Step
    public ResponseClient createBoard(String boardName) {
        return this.post(BOARD_PATH, this.getParamsBuilder().queryParam("name", boardName));
    }
}
