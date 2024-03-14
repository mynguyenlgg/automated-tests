package com.client.tests.Board;

import com.client.model.Board;
import com.client.response.ResponseClient;
import com.client.services.BoardService;
import com.client.utils.FakerUtils;
import com.client.utils.Log;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class TC001_CreateBoard {
    private String boardName = FakerUtils.generateName();
    private String boardID;
    BoardService boardService = new BoardService();

    @AfterClass
    public void cleanUp() {
        Log.postStep("Delete board was created");
        boardService.deleteBoard(boardID);
    }

    @Test(description = "TC001 - Create a new board")
    public void createBoard() {
        Log.step("Create a board");
        ResponseClient responseClient = boardService.createBoard(boardName);
        Board board = responseClient.getBody(Board.class);
        boardID = board.getId();

        Log.verify("Status code is 200");
        assertThat("Incorrect response code", responseClient.getStatusCode(), is(200));

        Log.verify("Board name is correct");
        assertThat("Incorrect Board name", board.getName(), equalTo(boardName));

        Log.verify("Board schema matches");
        assertThat("Incorrect Board schema", responseClient.isMatchesSchema("board.json"));
    }
}
