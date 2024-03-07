package com.client.tests.Board;

import com.client.model.Board;
import com.client.request.BoardClient;
import com.client.utils.FakerUtils;
import io.restassured.response.Response;
import org.testng.annotations.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TC003_DeleteBoard {
    private String boardName = FakerUtils.generateName();
    private String boardID;

    BoardClient boardApi = new BoardClient();

    @BeforeClass
    public void prepareData() {
        Board board = boardApi.createBoard(boardName).getBody();
        boardID = board.getId();
    }

    @Test(description = "TC003 - Delete a board")
    public void deleteBoard() {
        Response response = boardApi.deleteBoard(boardID);
        assertThat("Incorrect response code", response.getStatusCode(), is(200));
    }
}
