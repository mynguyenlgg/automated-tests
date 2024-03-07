package com.client.tests.Board;

import com.client.request.BoardApi;
import com.client.model.Board;
import com.client.tests.BaseTest;
import com.client.utils.FakerUtils;
import io.restassured.response.Response;
import org.testng.annotations.*;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class TC001_CreateBoard extends BaseTest {
    private String boardName = FakerUtils.generateName();
    private String boardID;

    @AfterClass
    public void cleanUp() {
        BoardApi.deleteBoard(boardID);
    }

    @Test(description = "TC001 - Create a new board")
    public void createBoard() {
        Response response = BoardApi.createBoard(boardName);
        Board board = response.as(Board.class);
        boardID = board.getId();

        assertThat("Incorrect response code", response.getStatusCode(), is(200));
        assertThat("Incorrect Board Name", board.getName(), equalTo(boardName));
        assertThat(response.getBody().asString(), matchesJsonSchema(new File("src/test/resources/schema/board.json")));
    }
}
