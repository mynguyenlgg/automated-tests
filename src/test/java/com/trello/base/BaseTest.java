package com.trello.base;

import com.trello.api.SpecBuilder;
import com.trello.api.applicationApi.BoardApi;
import com.trello.tests.TrelloAPIs;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import static io.restassured.RestAssured.given;

public class BaseTest {
    protected static RequestSpecification requestSpec;
    protected static String boardID;

    @BeforeClass
    public static void createRequestSpecifications() {
        requestSpec = SpecBuilder.getRequestSpec();
    }

    @AfterClass
    public static void deleteBoard() {
        BoardApi.deleteBoard(boardID);
    }
}
