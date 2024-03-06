package com.client.tests.Board;

import com.client.model.Board;
import com.client.request.BoardApi;
import com.client.tests.BaseTest;
import com.client.utils.FakerUtils;
import io.restassured.response.Response;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class TC002_GetBoard extends BaseTest {
    private String boardName = FakerUtils.generateName();
    private String boardID;

    @BeforeTest
    public void prepareData() {
        Board board = BoardApi.createBoard(boardName).as(Board.class);
        boardID = board.getId();
    }

    @AfterTest
    public void cleanUp() {
        BoardApi.deleteBoard(boardID);
    }

    @Test(description = "TC002 - Get a created board")
    public void getCreatedBoard() {
        Response response = BoardApi.getBoard(boardID);
        Board board = response.as(Board.class);

        assertThat("Incorrect response code", response.getStatusCode(), is(200));
        assertThat("Incorrect Board Name", board.getName(), equalTo(boardName));
        assertThat("Incorrect Board ID", board.getId(), equalTo(boardID));
    }
}
