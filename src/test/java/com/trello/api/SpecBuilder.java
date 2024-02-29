package com.trello.api;

import com.trello.utils.config.PropertiesConfigLoader;
import com.trello.utils.config.TrelloApiRoute;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {

    public static RequestSpecification getRequestSpec(){
        return new RequestSpecBuilder().
                setBaseUri(TrelloApiRoute.trelloApiPath().getBaseUrl()).
                setBasePath(TrelloApiRoute.trelloApiPath().getBasePath()).
                addQueryParam("token", PropertiesConfigLoader.getInstance().getProperty("app_token"))
                .addQueryParam("key", PropertiesConfigLoader.getInstance().getProperty("app_key"))
                .setContentType(ContentType.JSON).
                build();
    }

    public static ResponseSpecification getResponseSpec(){
        return new ResponseSpecBuilder().
                log(LogDetail.ALL).
                build();
    }
}
