package com.client.services;

import com.client.config.Configuration;
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

import java.util.Map;

public class BaseService {
    protected final Configuration configuration = Configuration.getInstance();
    protected RequestSpecification requestSpecification;

    protected RequestSpecification request;

    protected RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setConfig(RestAssured.config().objectMapperConfig(new ObjectMapperConfig(ObjectMapperType.GSON)))
                .setBaseUri(this.configuration.getBaseUrl())
                .setContentType(ContentType.JSON)
                .build()
                .filter(new AllureRestAssured());
    }

    protected ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder().build();
    }

    protected ResponseClient get(ParamsBuilder params, String path) {
        return request(params, Method.GET, path);
    }

    protected ResponseClient delete(ParamsBuilder params, String path) {
        return request(params, Method.DELETE, path);
    }

    protected ResponseClient post(ParamsBuilder params, String path) {
        return request(params, Method.POST, path);
    }

    protected ResponseClient request(ParamsBuilder params, Method method, String path) {
        this.request =  RestAssured.given(this.requestSpecification);
        if (params.getPathParams() != null) {
            this.request.pathParams(params.getPathParams());
        }
        if (params.getQueryParams() != null) {
            this.request.queryParams(params.getQueryParams());
        }
        Response response = this.request
                .request(method, path)
                .then()
                .spec(getResponseSpec())
                .extract().response();
        return new ResponseClient(response);
    }
}
