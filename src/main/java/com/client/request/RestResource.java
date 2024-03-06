package com.client.request;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

import static com.client.request.SpecBuilder.getRequestSpec;
import static com.client.request.SpecBuilder.getResponseSpec;

public class RestResource {

    public static Response post(String path, Map<String, ?> queryParams) {
        return RestAssured.given(getRequestSpec()).queryParams(queryParams).post(path).then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response get(String path, Map<String, ?> pathParams) {
        return RestAssured.given(getRequestSpec()).pathParams(pathParams).when().get(path).then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response delete(String path, Map<String, ?> pathParams) {
        return RestAssured.given(getRequestSpec()).pathParams(pathParams).when().delete(path).then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }
}
