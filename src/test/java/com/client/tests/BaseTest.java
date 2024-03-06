package com.client.tests;

import com.client.request.SpecBuilder;
import com.client.request.BoardApi;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected static RequestSpecification requestSpec;

    @BeforeClass
    public static void createRequestSpecifications() {
        requestSpec = SpecBuilder.getRequestSpec();
    }

}
