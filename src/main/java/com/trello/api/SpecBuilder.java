package com.trello.api;

import com.trello.utils.RestAssuredRequestFilter;
import com.trello.utils.config.PropertiesConfigLoader;
import com.trello.utils.config.TrelloApiRoute;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {

    public static RequestSpecification getRequestSpec(){
//        RestAssured.config = RestAssured.config()
//                .logConfig(LogConfig.logConfig()
//                        .enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)
//                        .enablePrettyPrinting(true));
//        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        return new RequestSpecBuilder().
                setBaseUri(TrelloApiRoute.trelloApiPath().getBaseUrl()).
                setBasePath(TrelloApiRoute.trelloApiPath().getBasePath()).
                addQueryParam("token", PropertiesConfigLoader.getInstance().getProperty("app_token"))
                .addQueryParam("key", PropertiesConfigLoader.getInstance().getProperty("app_key"))
//                .setContentType(ContentType.JSON).build().filter(new AllureRestAssured());
                .setContentType(ContentType.JSON).build();
    }

    public static ResponseSpecification getResponseSpec(){
        return new ResponseSpecBuilder().
                build();
    }
}
