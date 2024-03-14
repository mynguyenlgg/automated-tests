package com.client.tests.Board;

import com.client.model.Board;
import com.client.response.ResponseClient;
import com.client.services.BoardService;
import com.client.utils.FakerUtils;
import com.client.utils.Log;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TC002_GetBoard {
    private String boardName = FakerUtils.generateName();
    private String boardID;
    BoardService boardService = new BoardService();

    @BeforeClass
    public void prepareData() {
        Log.preStep("Create a board");
        Board board = boardService.createBoard(boardName).getBody(Board.class);
        boardID = board.getId();
    }

    @AfterClass
    public void cleanUp() {
        Log.postStep("Delete board was created");
        boardService.deleteBoard(boardID);
    }

    @Test(description = "TC002 - Get a created board")
    public void getCreatedBoard() {
        Log.step("Get a board");
        ResponseClient response = boardService.getBoard(boardID);
        Board board = response.getBody(Board.class);

        Log.verify("Status code is 200");
        assertThat("Incorrect response code", response.getStatusCode(), is(200));

        Log.verify("Board name is correct");
        assertThat("Incorrect Board name", board.getName(), equalTo(boardName));

        Log.verify("Board ID is correct");
        assertThat("Incorrect Board ID", board.getId(), equalTo(boardID));
    }
}
