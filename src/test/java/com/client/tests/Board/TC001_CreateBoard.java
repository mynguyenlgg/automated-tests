package com.client.tests.Board;

import com.client.model.Board;
import com.client.request.BoardClient;
import com.client.response.ResponseClient;
import com.client.utils.FakerUtils;
import org.testng.annotations.*;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class TC001_CreateBoard {
    private String boardName = FakerUtils.generateName();
    private String boardID;

    BoardClient boardApi = new BoardClient();

    @AfterClass
    public void cleanUp() {
        boardApi.deleteBoard(boardID);
    }

    @Test(description = "TC001 - Create a new board")
    public void createBoard() {
        ResponseClient<Board> response = boardApi.createBoard(boardName);
        boardID = response.getBody().getId();

        assertThat("Incorrect response code", response.getStatusCode(), is(200));
        assertThat("Incorrect Board Name", response.body.getName(), equalTo(boardName));
        assertThat(response.bodyString, matchesJsonSchema(new File("src/test/resources/schema/board.json")));
    }
}
