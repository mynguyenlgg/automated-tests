package com.client.request;

import com.client.config.Configuration;
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

public class BaseClient {
    protected final Configuration config;
    protected RequestSpecification requestSpec;
    protected ResponseSpecification responseSpec;

    public BaseClient() {
        this.config = Configuration.getInstance();
        this.requestSpec = new RequestSpecBuilder()
                .setConfig(RestAssured.config().objectMapperConfig(new ObjectMapperConfig(ObjectMapperType.GSON)))
                .setBaseUri(this.config.getBaseUrl())
                .setContentType(ContentType.JSON)
                .build()
                .filter(new AllureRestAssured());

        this.responseSpec = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }

    protected RequestSpecification getRequest() {
        return RestAssured.given(this.requestSpec);
    }
}
