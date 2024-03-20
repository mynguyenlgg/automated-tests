package com.client.tests.board;

import com.client.model.Board;
import com.client.response.ResponseClient;
import com.client.services.BoardService;
import com.client.utils.FakerUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DeleteBoardTests {
    private final String boardName = FakerUtils.generateName();
    private String boardID;
    private final BoardService boardService = new BoardService();

    @BeforeClass
    public void prepareData() {
        Board board = boardService.createBoard(boardName).getBody(Board.class);
        boardID = board.getId();
    }

    @Test(description = "TC003 - Delete a board")
    public void deleteBoard() {
        ResponseClient response = boardService.deleteBoard(boardID);
        assertThat("Incorrect response code", response.getStatusCode(), is(200));
    }
}
