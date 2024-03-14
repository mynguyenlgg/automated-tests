package com.client.tests.Board;

import com.client.model.Board;
import com.client.response.ResponseClient;
import com.client.services.BoardService;
import com.client.utils.FakerUtils;
import com.client.utils.Log;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TC003_DeleteBoard {
    private String boardName = FakerUtils.generateName();
    private String boardID;
    BoardService boardService = new BoardService();

    @BeforeClass
    public void prepareData() {
        Log.preStep("Create a board");
        Board board = boardService.createBoard(boardName).getBody(Board.class);
        boardID = board.getId();
    }

    @Test(description = "TC003 - Delete a board")
    public void deleteBoard() {
        Log.step("Delete a board");
        ResponseClient response = boardService.deleteBoard(boardID);

        Log.verify("Status code is 200");
        assertThat("Incorrect response code", response.getStatusCode(), is(200));
    }
}
