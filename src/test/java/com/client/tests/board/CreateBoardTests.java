package com.client.tests.board;

import com.client.model.Board;
import com.client.response.ResponseClient;
import com.client.services.BoardService;
import com.client.utils.FakerUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CreateBoardTests {
    private final String boardName = FakerUtils.generateName();
    private String boardID;
    private final BoardService boardService = new BoardService();

    @AfterClass
    public void cleanUp() {
        boardService.deleteBoard(boardID);
    }

    @Test(description = "TC001 - Create a new board")
    public void createBoard() {
        ResponseClient responseClient = boardService.createBoard(boardName);
        Board board = responseClient.getBody(Board.class);
        boardID = board.getId();

        assertThat("Incorrect response code", responseClient.getStatusCode(), is(200));
        assertThat("Incorrect Board Name", board.getName(), equalTo(boardName));
        assertThat("Incorrect Board schema", responseClient.isMatchesSchema("board.json"));
    }
}
