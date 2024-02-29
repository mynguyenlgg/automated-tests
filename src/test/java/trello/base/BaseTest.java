package trello.base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import static io.restassured.RestAssured.given;

public class BaseTest {
    protected static RequestSpecification requestSpec;
    protected static String boardID;


    @BeforeClass
    public static void createRequestSpecifications() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://api.trello.com")
                .addQueryParam("token", "ATTA41c7f6ff603c19b49e385104f6c5a26f8945065fd2bd7bd1e3559d0296ada647CE382428")
                .addQueryParam("key", "cdceab0688f6033931a439e74f68bc78")
                .addHeader("Content-Type", "application/json")
                .build();
    }

    @AfterClass
    public static void deleteBoard() {
        given().spec(requestSpec)
                .pathParam("id", boardID)
                .body("")
                .when().delete("1/boards/{id}");

    }

    @DataProvider(name = "BoardField")
    public Object[][] getData() {
        return new Object[][]{
                {"idMemberCreator"},
                {"idOrganization"},
                {"desc"},
                {"name"}
        };
    }

}
