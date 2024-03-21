package com.client.tests.board;

import com.client.model.Board;
import com.client.response.ResponseClient;
import com.client.services.BoardService;
import com.client.utils.FakerUtils;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CreateBoardTests {
    private final String boardName = FakerUtils.generateName();
    private String boardID;
    private final BoardService boardService = new BoardService();

    SoftAssertions softAssertions = new SoftAssertions();

    @AfterClass
    public void cleanUp() {
        boardService.deleteBoard(boardID);
    }

    @Test(description = "TC001 - Create a new board")
    public void createBoard() {
        ResponseClient responseClient = boardService.createBoard(boardName);
        Board board = responseClient.getBody(Board.class);
        boardID = board.getId();

        assertThat(responseClient.getStatusCode()).as("Incorrect response code").isEqualTo(200);
        softAssertions.assertThat(board.getName()).as("Incorrect Board Name").isEqualTo(boardName);
        softAssertions.assertThat(responseClient.getSchemaValidations("board.json")).as("Incorrect Board schema").isEmpty();
        softAssertions.assertAll();
    }
}
