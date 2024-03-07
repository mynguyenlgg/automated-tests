package com.client.services;

import com.client.config.Configuration;
import com.client.response.ResponseClient;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class BoardService extends BaseService {
    private final String PATH = "boards/";


    @Override
    protected RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setConfig(RestAssured.config().objectMapperConfig(new ObjectMapperConfig(ObjectMapperType.GSON)))
                .setBaseUri(Configuration.getInstance().getBaseUrl())
                .setContentType(ContentType.JSON)
                .addQueryParam("key", this.configuration.getAppKey())
                .addQueryParam("token", this.configuration.getAppToken())
                .build()
                .filter(new AllureRestAssured());
    }

    @Step
    public ResponseClient createBoard(String boardName) {
        return this.post(Map.of("name", boardName), PATH);
    }

    @Step
    public ResponseClient deleteBoard(String id) {
        if (id == null) {
            throw new RuntimeException("Board ID cannot be null.");
        }
        return this.delete(Map.of("id", id), PATH + "{id}");
    }

    @Step
    public ResponseClient getBoard(String id) {
        if (id == null) {
            throw new RuntimeException("Board ID cannot be null.");
        }
        return this.get(Map.of("id", id), PATH + "{id}");
    }
}
