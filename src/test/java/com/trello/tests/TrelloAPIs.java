package com.trello.tests;

import com.trello.api.StatusCode;
import com.trello.api.applicationApi.BoardApi;
import com.trello.utils.FakerUtils;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.annotations.*;
import com.trello.base.BaseTest;
import com.trello.pojo.Board;
import java.io.File;
import static org.hamcrest.Matchers.equalTo;

@Listeners(com.trello.base.Listeners.class)
public class TrelloAPIs extends BaseTest {

    private static String listID;
    private static String cardID;
    private static String boardName = FakerUtils.generateName();
    Board board = new Board();

    @Test(priority = 0, description = "Create a new board")
    public void createBoard() {
        Response res = BoardApi.createBoard(boardName);
        boardID = res.as(Board.class).getId();
        res.then().statusCode(StatusCode.CODE_200.code);
        res.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/java/com/trello/schema/board.json")));
        res.then().body("name", equalTo(boardName));
    }

    @Test(dependsOnMethods = "createBoard", priority = 1, description = "Get a created board")
    public void getCreatedBoard() {
        Response res = BoardApi.getBoard(boardID);
        res.then().statusCode(StatusCode.CODE_200.code);
        res.then().body("id", equalTo(boardID))
                  .body("name", equalTo(boardName));
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
