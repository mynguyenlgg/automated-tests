package com.client.response;

import io.restassured.response.Response;
import lombok.Getter;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class ResponseClient<T>{
    private final Response response;
    private final Class<T> responseType;

    public final T body;

    public final String bodyString;

    public final int statusCode;

    public final Map<String, T> headers;

    public ResponseClient(Response response, Class<T> responseType) {
        this.response = response;
        this.responseType = responseType;
        this.body = response.as(responseType);
        this.bodyString = response.asString();
        this.statusCode = response.getStatusCode();
        this.headers = response.getHeaders().asList().stream()
                .collect(Collectors.toMap(header -> header.getName(), header -> (T) header.getValue()));
    }

    public String getStatusMessage() {
        return this.response.getStatusLine();
    }

    public String getHeader(String headerName) {
        return (String) this.headers.get(headerName);
    }
}
