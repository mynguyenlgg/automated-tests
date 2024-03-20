package com.client.response;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import lombok.Getter;
import lombok.Setter;

public class ResponseClient {

    @Setter
    @Getter
    private Response response;

    private ResponseBody body;

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
}
