package com.client.services;

import com.client.config.Configuration;
import com.client.param.ParamsBuilder;
import com.client.response.ResponseClient;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.Getter;

@Getter
public class BaseService {
    protected RequestSpecification requestSpecification;

    public BaseService() {
        this.requestSpecification = new RequestSpecBuilder()
                .setConfig(RestAssured.config().objectMapperConfig(new ObjectMapperConfig(ObjectMapperType.GSON)))
                .setBaseUri(Configuration.getInstance().getBaseUrl())
                .setContentType(ContentType.JSON)
                .build()
                .filter(new AllureRestAssured());
    }

    public BaseService(String key, String token) {
        this();
        this.requestSpecification
                .queryParams("key", key)
                .queryParams("token", token);
    }

    protected ParamsBuilder<String, String> getParamsBuilder() {
        return new ParamsBuilder<>();
    }

    protected ResponseSpecification getResponseSpecification() {
        return new ResponseSpecBuilder().build();
    }

    protected <T> ResponseClient get(String path, ParamsBuilder<String, T> params) {
        return request(Method.GET, path, params);
    }

    protected <T> ResponseClient delete(String path, ParamsBuilder<String, T> params) {
        return request(Method.DELETE, path, params);
    }

    protected <T> ResponseClient post(String path, ParamsBuilder<String, T> params) {
        return request(Method.POST, path, params);
    }

    protected <T> ResponseClient request(Method method, String path, ParamsBuilder<String, T> params) {
        if (params != null) {
            if (params.getPathParams() != null) {
                this.requestSpecification.pathParams(params.getPathParams());
            }
            if (params.getQueryParams() != null) {
                this.requestSpecification.queryParams(params.getQueryParams());
            }
        }

        Response response = RestAssured.given(this.requestSpecification)
                .request(method, path)
                .then()
                .spec(getResponseSpecification())
                .extract().response();
        return new ResponseClient(response);
    }
}
