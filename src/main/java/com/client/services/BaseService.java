package com.client.services;

import com.client.config.AllureRestAssuredLogger;
import com.client.config.Configuration;
import com.client.config.RestAssuredRequestLogger;
import com.client.config.RestAssuredResponseLogger;
import com.client.response.ResponseClient;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseService {
    protected final Configuration configuration = Configuration.getInstance();
    protected RequestSpecification requestSpecification;

    protected RequestSpecification request;

    protected RequestSpecification getRequestSpec() {
        LogConfig logConfig = RestAssured.config().getLogConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
        RestAssuredConfig config = RestAssured.config()
                .objectMapperConfig(new ObjectMapperConfig(ObjectMapperType.GSON)).logConfig(logConfig);

        return new RequestSpecBuilder()
                .setConfig(config)
                .setBaseUri(this.configuration.getBaseUrl())
                .setContentType(ContentType.JSON)
                .build();
    }

    protected ParamsBuilder<String, String> getParamsBuilder() {
        return new ParamsBuilder<>();
    }

    protected ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder().build();
    }

    protected <V> ResponseClient get(String path, ParamsBuilder<String, V> params) {
        return request(Method.GET, path, params);
    }

    protected <V> ResponseClient delete(String path, ParamsBuilder<String, V> params) {
        return request(Method.DELETE, path, params);
    }

    protected <V> ResponseClient post(String path, ParamsBuilder<String, V> params) {
        return request(Method.POST, path, params);
    }

    protected <V> ResponseClient request(Method method, String path, ParamsBuilder<String, V> params) {
        this.request = RestAssured.given(this.requestSpecification);
        if (params != null) {
            if (params.getPathParams() != null) {
                this.request.pathParams(params.getPathParams());
            }
            if (params.getQueryParams() != null) {
                this.request.queryParams(params.getQueryParams());
            }
        }

        Response response = this.request
                .filters(new AllureRestAssured().setResponseAttachmentName("Response"))
                .request(method, path)
                .then()
                .spec(getResponseSpec())
                .extract().response();

        return new ResponseClient(response);
    }
}
