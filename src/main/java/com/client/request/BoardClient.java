package com.client.request;

import com.client.model.Board;
import com.client.response.ResponseClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BoardClient extends BaseClient {
    private final String PATH = "boards/";

    public BoardClient() {
        super();
        this.requestSpec.queryParam("token", this.config.getAppToken())
                .queryParam("key", this.config.getAppKey());
    }

    @Step
    public ResponseClient<Board> getBoard(String id) {
        Response apiResponse = this.getRequest().pathParams("id", id).when().get(PATH + "{id}").then().
                spec(responseSpec).
                extract().
                response();
        return new ResponseClient<>(apiResponse, Board.class);
    }

    @Step
    public Response deleteBoard(String id) {
        if (id == null) {
            throw new RuntimeException("Board ID cannot be null.");
        }
        return this.getRequest().pathParams("id", id).when().delete(PATH + "{id}").then().
                spec(responseSpec).
                extract().
                response();
    }

    @Step
    public ResponseClient<Board> createBoard(String name) {
        Response apiResponse = this.getRequest().queryParams("name", name).post(PATH).then().
                spec(responseSpec).
                extract().
                response();
        return new ResponseClient<>(apiResponse, Board.class);
    }
}
