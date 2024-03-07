package com.client.tests.Board;

import com.client.model.Board;
import com.client.request.BoardClient;
import com.client.response.ResponseClient;
import com.client.utils.FakerUtils;
import org.testng.annotations.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class TC002_GetBoard {
    private String boardName = FakerUtils.generateName();
    private String boardID;
    BoardClient boardApi = new BoardClient();

    @BeforeClass
    public void prepareData() {
        Board board = boardApi.createBoard(boardName).getBody();
        boardID = board.getId();
    }

    @AfterClass
    public void cleanUp() {
        boardApi.deleteBoard(boardID);
    }

    @Test(description = "TC002 - Get a created board")
    public void getCreatedBoard() {
        ResponseClient<Board> response = boardApi.getBoard(boardID);
        Board board = response.getBody();

        assertThat("Incorrect response code", response.getStatusCode(), is(200));
        assertThat("Incorrect Board Name", board.getName(), equalTo(boardName));
        assertThat("Incorrect Board ID", board.getId(), equalTo(boardID));
    }
}
