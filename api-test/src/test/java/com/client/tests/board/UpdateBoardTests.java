package com.client.tests.board;

import com.client.model.Board;
import com.client.response.ResponseClient;
import com.client.services.BoardService;
import com.client.utils.FakerUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class UpdateBoardTests {
    private final String boardName = FakerUtils.generateName();
    private final String boardNewName = FakerUtils.generateName();
    private Board board;
    private final BoardService boardService = new BoardService();

    @BeforeClass
    public void prepareData() {
        board = boardService.createBoard(boardName).getBody(Board.class);
    }

    @AfterClass
    public void cleanUp() {
        boardService.deleteBoard(board.getId());
    }

    @Test(description = "TC004 - Update a board")
    public void updateBoard() {
        board.setName(boardNewName);
        ResponseClient response = boardService.updateBoard(board);
        Board board = response.getBody(Board.class);

        assertThat("Incorrect response code", response.getStatusCode(), is(200));
        assertThat("Incorrect Board Name", board.getName(), equalTo(boardNewName));
    }
}
