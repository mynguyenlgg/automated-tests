package com.client.tests.board;

import com.client.model.Board;
import com.client.response.ResponseClient;
import com.client.services.BoardService;
import com.client.utils.FakerUtils;
import com.client.utils.SoftHamcrestAssert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class GetBoardTests {
    private final String boardName = FakerUtils.generateName();
    private String boardID;
    private final BoardService boardService = new BoardService();
    private final SoftHamcrestAssert softHamcrestAssert = new SoftHamcrestAssert();

    @BeforeClass
    public void prepareData() {
        Board board = boardService.createBoard(boardName).getBody(Board.class);
        boardID = board.getId();
    }

    @AfterClass
    public void cleanUp() {
        boardService.deleteBoard(boardID);
    }

    @Test(description = "TC002 - Get a created board")
    public void getCreatedBoard() {
        ResponseClient response = boardService.getBoard(boardID);
        Board board = response.getBody(Board.class);

        softHamcrestAssert.assertThat("Incorrect response code", response.getStatusCode(), is(200));
        softHamcrestAssert.assertThat("Incorrect Board Name", board.getName(), equalTo(boardName));
        softHamcrestAssert.assertThat("Incorrect Board ID", board.getId(), equalTo(boardID));
        softHamcrestAssert.assertAll();
    }
}
