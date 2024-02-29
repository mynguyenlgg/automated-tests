package trello.tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import trello.base.BaseTest;
import trello.pojo.GetBoard;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Listeners(trello.base.Listeners.class)
public class TrelloAPIs extends BaseTest {

private static String listID;
private static String cardID;
GetBoard gb= new GetBoard();

//    @Test(priority = 0)
//    public void createBoard() {
//         gb=given().spec(requestSpec)
//                .queryParams("name", "Test Case 1","prefs_background","orange"
//                        ,"prefs_background_url","https://images.unsplash.com/photo-1676694090990-b9e29828fdd3?ixid=Mnw3MDY2fDB8MXxjb2xsZWN0aW9ufDN8MzE3MDk5fHx8fHwyfHwxNjc2OTc4MzA4&ixlib=rb-4.0.3&w=2560&h=2048&q=90")
//                .when().post("1/boards").
//                then().log().all().assertThat().statusCode(200).extract().response().as(GetBoard.class);//.path("id");
//        boardID = gb.getId();
//    }

    @Test(priority = 0)
    public void createBoard() {
        gb=given().spec(requestSpec)
                .queryParams("name", "Test Case 1","prefs_background","orange"
                        ,"prefs_background_url","https://images.unsplash.com/photo-1676694090990-b9e29828fdd3?ixid=Mnw3MDY2fDB8MXxjb2xsZWN0aW9ufDN8MzE3MDk5fHx8fHwyfHwxNjc2OTc4MzA4&ixlib=rb-4.0.3&w=2560&h=2048&q=90")
                .when().post("1/boards").
                then().log().all().assertThat().statusCode(200).extract().response().as(GetBoard.class);//.path("id");
        boardID = gb.getId();
    }

    @Test(dependsOnMethods = "createBoard",priority = 1)
    public void getCreatedBoard() {

        given().spec(requestSpec)
                .pathParam("id", boardID)
                .when().get("1/boards/{id}").
                then().log().all().assertThat().statusCode(200)
                .body("id", equalTo(boardID)).body("name",equalTo("Test Case 1"));

    }

    @Test(dependsOnMethods = "createBoard",priority = 2)
    public void getMembershipsOfABoard() {
        given().spec(requestSpec)
                .pathParam("id", boardID)
                .when().get("1/boards/{id}/memberships").
                then().log().all().assertThat().statusCode(200).body("[0].memberType", equalTo("admin"));

    }

    @Test(dependsOnMethods = "createBoard",priority = 3,dataProvider = "BoardField")
    public void getAFieldOnABoard(String field) {
        given().spec(requestSpec)
                .pathParam("id", boardID)
                .when().get("1/boards/{id}/"+field).
                then().log().all().assertThat().statusCode(200);

    }

    @Test(dependsOnMethods = "createBoard",priority = 4)
    public void updateABoard() throws IOException {
        given().spec(requestSpec)
                .pathParam("id", boardID)
                .body(new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") +"//body.json"))))
                .when().put("1/boards/{id}")
                .then().log().body().assertThat().statusCode(200);
    }

    @Test(dependsOnMethods = "createBoard",priority = 5)
    public void createList() {
       listID= given().spec(requestSpec)
                .pathParam("id", boardID)
                .queryParam("name","list1")
                .when().post("1/boards/{id}/lists").
                then().log().all().assertThat().statusCode(200).extract().path("id");

    }

    @Test(dependsOnMethods = "createBoard",priority = 6)
    public void createCard() {
       cardID= given().spec(requestSpec)
                .queryParam("name", "card")
                .queryParam("idList", listID)
                .when().post("1/cards").
                then().log().all().assertThat().statusCode(200).assertThat().body("name", equalTo("card"))
               .extract().path("id");

    }

    @Test(dependsOnMethods = "createBoard",priority = 7)
    public void uploadAttachment() {
        given().spec(requestSpec)
                .pathParam("id", cardID)
                .multiPart("file", new File(System.getProperty("user.dir") +"//body.txt"))
                .header("Content-Type", "multipart/form-data")
                .when().post("1/cards/{id}/attachments").
                then().log().all().assertThat().statusCode(200);

    }
    @Test(dependsOnMethods = "createBoard",priority = 8)
    public void getCreatedBoardFail() {

        given().spec(requestSpec)
                .pathParam("id", boardID)
                .when().get("1/boards/{id}").
                then().log().all().assertThat().statusCode(201)
                .body("id", equalTo(boardID)).body("name",equalTo("Test Case 1"));
    }

    /*@Test(dependsOnMethods = "createBoard",priority = 9)
    public void deleteBoard() {
        given().spec(requestSpec)
                .pathParam("id", boardID)
                .body("")
                .when().delete("1/boards/{id}").
                then().log().all().assertThat().statusCode(200);
    }*/
}
