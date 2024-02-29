package com.trello.tests;

import com.github.javafaker.Faker;
import org.testng.annotations.*;
import com.trello.base.BaseTest;
import com.trello.pojo.Board;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Listeners(com.trello.base.Listeners.class)
public class TrelloAPIs extends BaseTest {

    private static String listID;
    private static String cardID;
    private static Faker faker = new Faker();
    private static String boardName = faker.funnyName().name();
    Board board = new Board();

    @Test(priority = 0, description = "Create a new board")
    public void createBoard() {
        board = given().spec(requestSpec)
                .queryParams("name", boardName, "prefs_background", "orange"
                        , "prefs_background_url", "https://images.unsplash.com/photo-1676694090990-b9e29828fdd3?ixid=Mnw3MDY2fDB8MXxjb2xsZWN0aW9ufDN8MzE3MDk5fHx8fHwyfHwxNjc2OTc4MzA4&ixlib=rb-4.0.3&w=2560&h=2048&q=90")
                .when().post("1/boards").
                then().log().all().assertThat().statusCode(200).extract().response().as(Board.class);
        boardID = board.getId();
    }

    @Test(dependsOnMethods = "createBoard", priority = 1, description = "Get a created board")
    public void getCreatedBoard() {
        given().spec(requestSpec)
                .pathParam("id", boardID)
                .when().get("1/boards/{id}").
                then().log().all().assertThat().statusCode(200)
                .body("id", equalTo(boardID)).body("name", equalTo(boardName));
    }

    @Test(dependsOnMethods = "createBoard", priority = 2, description = "Get memberships of the board")
    public void getMembershipsOfABoard() {
        given().spec(requestSpec)
                .pathParam("id", boardID)
                .when().get("1/boards/{id}/memberships").
                then().log().all().assertThat().statusCode(200).body("[0].memberType", equalTo("admin"));
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

    @Test(dependsOnMethods = "createBoard", priority = 3, dataProvider = "BoardField",  description = "Get a field of the board")
    public void getAFieldOnABoard(String field) {
        given().spec(requestSpec)
                .pathParam("id", boardID)
                .when().get("1/boards/{id}/" + field).
                then().log().all().assertThat().statusCode(200);
    }

    @Test(dependsOnMethods = "createBoard", priority = 4, description = "Update the board")
    public void updateABoard() throws IOException {
        given().spec(requestSpec)
                .pathParam("id", boardID)
                .body(new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "//body.json"))))
                .when().put("1/boards/{id}")
                .then().log().body().assertThat().statusCode(200);
    }

    @Test(dependsOnMethods = "createBoard", priority = 5)
    public void createList() {
        listID = given().spec(requestSpec)
                .pathParam("id", boardID)
                .queryParam("name", "list1")
                .when().post("1/boards/{id}/lists").
                then().log().all().assertThat().statusCode(200).extract().path("id");
    }

    @Test(dependsOnMethods = "createBoard", priority = 6)
    public void createCard() {
        cardID = given().spec(requestSpec)
                .queryParam("name", "card")
                .queryParam("idList", listID)
                .when().post("1/cards").
                then().log().all().assertThat().statusCode(200).assertThat().body("name", equalTo("card"))
                .extract().path("id");
    }

    @Test(dependsOnMethods = "createBoard", priority = 7)
    public void uploadAttachment() {
        given().spec(requestSpec)
                .pathParam("id", cardID)
                .multiPart("file", new File(System.getProperty("user.dir") + "//body.txt"))
                .header("Content-Type", "multipart/form-data")
                .when().post("1/cards/{id}/attachments").
                then().log().all().assertThat().statusCode(200);
    }

    @Test(dependsOnMethods = "createBoard", priority = 8)
    public void getCreatedBoardFail() {
        given().spec(requestSpec)
                .pathParam("id", boardID)
                .when().get("1/boards/{id}").
                then().log().all().assertThat().statusCode(200)
                .body("id", equalTo(boardID)).body("name", equalTo(boardName));
    }

    /*@Test(dependsOnMethods = "createBoard",priority = 9)
    public void deleteBoard() {
        given().spec(requestSpec)
                .pathParam("id", boardID)
                .body("")
                .when().delete("1/boards/{id}").
                then().log().all().assertThat().statusCode(200);
    }*/
}
