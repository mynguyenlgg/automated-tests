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

    protected RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setConfig(RestAssured.config().objectMapperConfig(new ObjectMapperConfig(ObjectMapperType.GSON)))
                .setBaseUri(this.configuration.getBaseUrl())
                .setContentType(ContentType.JSON)
                .build()
                .filter(new AllureRestAssured());
    };

    protected ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder().build();
    }

    protected ResponseClient get(Map<String, ?> params, String path) {
        return pathParamsRequest(Method.GET, params, path);
    }

    protected ResponseClient delete(Map<String, ?> params, String path) {
        return pathParamsRequest(Method.DELETE, params, path);
    }

    protected ResponseClient post(Map<String, ?> params, String path) {
        return queryParamsRequest(Method.POST, params, path);
    }

    private RequestSpecification getRequest() {
        return RestAssured.given(this.getRequestSpec());
    }

    protected ResponseClient pathParamsRequest(Method method, Map<String, ?> params, String path) {
        Response response = this.getRequest()
                .pathParams(params)
                .when()
                .request(method, path)
                .then()
                .spec(getResponseSpec())
                .extract().response();
        return new ResponseClient(response);
    }

    protected ResponseClient queryParamsRequest(Method method, Map<String, ?> params, String path) {
        Response response =  this.getRequest()
                .queryParams(params)
                .when()
                .request(method, path)
                .then()
                .spec(getResponseSpec())
                .extract().response();
        return new ResponseClient(response);
    }
}
