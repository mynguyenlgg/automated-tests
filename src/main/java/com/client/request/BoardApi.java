package com.client.request;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Map;


public class BoardApi {
    private final static String PATH = "boards";
    @Step
    public static Response createBoard(String name) {
        return RestResource.post(PATH, Map.of("name", name));
    }

    @Step
    public static Response getBoard(String id) {
        return RestResource.get(PATH, Map.of("id", id));
    }

    @Step
    public static Response deleteBoard(String id) {
        return RestResource.delete(PATH + "/{id}", Map.of("id", id));
    }
}
