package com.client.response;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class ResponseClient {

    private static String SCHEMA_PATH = "src/test/resources/schemas/";

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

    public Set<ValidationMessage> getSchemaValidations(String filePath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
            JsonNode schemaNode = mapper.readTree(new File(SCHEMA_PATH + filePath));
            JsonNode jsonNode = mapper.readTree(this.getBodyString());
            return factory.getSchema(schemaNode).validate(jsonNode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
