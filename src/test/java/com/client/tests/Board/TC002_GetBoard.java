package com.client.tests.Board;

import com.client.model.Board;
import com.client.response.ResponseClient;
import com.client.services.BoardService;
import com.client.utils.FakerUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TC002_GetBoard {
    private String boardName = FakerUtils.generateName();
    private String boardID;
    BoardService boardClient = new BoardService();

    @BeforeClass
    public void prepareData() {
        Board board = boardClient.createBoard(boardName).getBody(Board.class);
        boardID = board.getId();
    }

    @AfterClass
    public void cleanUp() {
        boardClient.deleteBoard(boardID);
    }

    @Test(description = "TC002 - Get a created board")
    public void getCreatedBoard() {
        ResponseClient response = boardClient.getBoard(boardID);
        Board board = response.getBody(Board.class);

        assertThat("Incorrect response code", response.getStatusCode(), is(200));
        assertThat("Incorrect Board Name", board.getName(), equalTo(boardName));
        assertThat("Incorrect Board ID", board.getId(), equalTo(boardID));
    }
}
