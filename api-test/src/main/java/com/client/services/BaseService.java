package com.client.services;

import com.client.config.Configuration;
import com.client.param.ParamsBuilder;
import com.client.response.ResponseClient;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.Getter;

@Getter
public class BaseService {
    private final RequestSpecification requestSpec;

    public BaseService() {
        LogConfig logConfig = RestAssured.config().getLogConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
        RestAssuredConfig config = RestAssured.config()
                .objectMapperConfig(new ObjectMapperConfig(ObjectMapperType.GSON)).logConfig(logConfig);

        this.requestSpec = new RequestSpecBuilder()
                .setConfig(config)
                .setBaseUri(Configuration.getInstance().getBaseUrl())
                .setContentType(ContentType.JSON)
                .build()
                .filter(new AllureRestAssured().setResponseAttachmentName("Response"));
    }

    protected RequestSpecification getRequestSpec() {
        return requestSpec;
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
                getRequestSpec().pathParams(params.getPathParams());
            }
            if (params.getQueryParams() != null) {
                getRequestSpec().queryParams(params.getQueryParams());
            }
        }

        Response response = RestAssured.given(getRequestSpec())
                .request(method, path)
                .then()
                .spec(getResponseSpecification())
                .extract().response();
        return new ResponseClient(response);
    }
}
