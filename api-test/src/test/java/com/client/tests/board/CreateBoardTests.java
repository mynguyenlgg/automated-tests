package com.client.tests.board;

import com.client.model.Board;
import com.client.response.ResponseClient;
import com.client.services.BoardService;
import com.client.utils.FakerUtils;
import com.client.utils.SoftHamcrestAssert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CreateBoardTests {
    private final String boardName = FakerUtils.generateName();
    private String boardID;
    private final BoardService boardService = new BoardService();

    SoftHamcrestAssert softHamcrestAssert = new SoftHamcrestAssert();

    @AfterClass
    public void cleanUp() {
        boardService.deleteBoard(boardID);
    }

    @Test(description = "TC001 - Create a new board")
    public void createBoard() {
        ResponseClient responseClient = boardService.createBoard(boardName);
        Board board = responseClient.getBody(Board.class);
        boardID = board.getId();

        softHamcrestAssert.assertThat("Incorrect response code", responseClient.getStatusCode(), is(200));
        softHamcrestAssert.assertThat("Incorrect Board Name", board.getName(), equalTo(boardName));
        softHamcrestAssert.assertThat("Incorrect Board schema", responseClient.getBodyString(), matchesJsonSchemaInClasspath("schemas/board.json"));
        softHamcrestAssert.assertAll();
    }
}
