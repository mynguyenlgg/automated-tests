package com.client.request;

import com.client.config.TestConfig;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {
    public static RequestSpecification getRequestSpec() {
        TestConfig testconfig = TestConfig.getInstance();
        return new RequestSpecBuilder()
                .setConfig(RestAssured.config().objectMapperConfig(new ObjectMapperConfig(ObjectMapperType.GSON)))
                .setBaseUri(testconfig.getBaseUrl())
                .setBasePath(testconfig.getBasePath())
                .addQueryParam("token", testconfig.getAppToken())
                .addQueryParam("key", testconfig.getAppKey())
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build().filter(new AllureRestAssured());
    }

    public static ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }
}
