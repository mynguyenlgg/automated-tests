package com.client.tests.Board;

import com.client.model.Board;
import com.client.request.BoardApi;
import com.client.tests.BaseTest;
import com.client.utils.FakerUtils;
import io.restassured.response.Response;
import org.testng.annotations.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TC003_DeleteBoard extends BaseTest {
    private String boardName = FakerUtils.generateName();
    private String boardID;

    @BeforeClass
    public void prepareData() {
        Board board = BoardApi.createBoard(boardName).as(Board.class);
        boardID = board.getId();
    }

    @Test(description = "TC003 - Delete a board")
    public void deleteBoard() {
        Response response = BoardApi.deleteBoard(boardID);
        assertThat("Incorrect response code", response.getStatusCode(), is(200));
    }
}
