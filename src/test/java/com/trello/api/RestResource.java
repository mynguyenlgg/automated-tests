package com.trello.api;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static com.trello.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestResource {

    public static Response post(String path, Map<String, ?> queryParams) {
        return given(getRequestSpec()).queryParams(queryParams).post(path).then().
                spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response get(String path, Map<String, ?> pathParams) {
        return given(getRequestSpec()).pathParams(pathParams).when().get(path).then().
                spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response delete(String path, Map<String, ?> pathParams) {
        return given(getRequestSpec()).pathParams(pathParams).when().delete(path).then().
                spec(getResponseSpec()).
                extract().
                response();
    }
}
