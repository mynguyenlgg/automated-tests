package com.client.response;

import io.restassured.http.Headers;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import lombok.Getter;
import lombok.Setter;
import org.hamcrest.Matcher;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class ResponseClient {

    private static final String SCHEMA_FILE = "src/test/resources/schema/";

    @Setter
    @Getter
    private Response response;

    private ResponseBody body;

    private String bodyString;

    private int statusCode;

    private Headers headers;

    private JsonSchemaValidator matchesSchema;

    public ResponseClient(Response response) {
        this.response = response;
        this.body = response.getBody();
    }

    public <T> T getBody(Class<T> type) {
        return this.response.as(type);
    }

    public String getBodyString() {
        if (this.body == null) {
            throw new RuntimeException("The body is not found. Please check the request!");
        }
        return this.body.asString();
    }

    public int getStatusCode() {
        return this.response.getStatusCode();
    }

    public boolean isMatchesSchema(String fileName) {
        String actual = getBodyString();
        Matcher<String> matcher = matchesJsonSchema(new File(SCHEMA_FILE + fileName));
        return matcher.matches(actual);
    }
}
