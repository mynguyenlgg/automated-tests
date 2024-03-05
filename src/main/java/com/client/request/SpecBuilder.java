package com.client.request;

import com.client.config.PropertiesConfigLoader;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {
    private final static String BASE_URL = System.getProperty("baseUrl", "https://api.trello.com/");
    private final static String BASE_PATH= System.getProperty("basePath", "1/");
    public static RequestSpecification getRequestSpec(){
        return new RequestSpecBuilder().
                setBaseUri(BASE_URL).
                setBasePath(BASE_PATH).
                addQueryParam("token", PropertiesConfigLoader.getInstance().getProperty("app_token"))
                .addQueryParam("key", PropertiesConfigLoader.getInstance().getProperty("app_key"))
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL).
                build().filter(new AllureRestAssured());
    }

    public static ResponseSpecification getResponseSpec(){
        return new ResponseSpecBuilder().
                log(LogDetail.ALL).
                build();
    }
}
