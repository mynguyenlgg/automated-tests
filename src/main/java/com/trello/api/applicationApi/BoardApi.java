package com.trello.api.applicationApi;

import com.trello.api.RestResource;
import com.trello.utils.config.TrelloApiRoute;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Map;


public class BoardApi {
    @Step
    public static Response createBoard(String name) {
        return RestResource.post(TrelloApiRoute.trelloApiPath().getBoards(), Map.of("name", name));
    }

    @Step
    public static Response getBoard(String id) {
        return RestResource.get(TrelloApiRoute.trelloApiPath().getBoards() + "/{id}", Map.of("id", id));
    }

    @Step
    public static Response deleteBoard(String id) {
        return RestResource.delete(TrelloApiRoute.trelloApiPath().getBoards() + "/{id}", Map.of("id", id));
    }
}
