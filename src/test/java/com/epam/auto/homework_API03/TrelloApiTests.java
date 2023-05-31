package com.epam.auto.homework_API03;

import io.restassured.RestAssured;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class TrelloApiTests {

   private static Board board;
   private static Card card;
   private final String BOARD_PATH = "boards";
   private final String CARD_PATH = "cards";
   private static Specifications specification;

   @BeforeClass
   public static void setup() {
      specification = new Specifications();
      TrelloApiTests setupForTests = new TrelloApiTests();
      setupForTests.createBoard();
      setupForTests.createCard();
   }

   public void createBoard() {
      String boardName = "Test Board";
      Map<String, String> parameters = new HashMap<>();
      parameters.put("name", boardName);

      board = RestAssured.given().spec(specification.requestSpec()).queryParams(parameters).when().post(BOARD_PATH).then().extract().response().as(Board.class);
   }

   public void getBoardLists() {
      Map<String, String> pathParameters = new HashMap<>();
      pathParameters.put("boardPath", BOARD_PATH);
      pathParameters.put("boardId", board.getBoardId());
      pathParameters.put("listPath", "lists");

      String toDolist = RestAssured.given()
              .spec(specification.requestSpec())
              .pathParams(pathParameters)
              .when()
              .get("{boardPath}/{boardId}/{listPath}")
              .then()
              .extract()
              .response()
              .path("find { it.name == 'To Do' }.id");
      board.setToDoListId(toDolist);
   }

   public void createCard() {
      getBoardLists();
      String cardName = "First card";
      Map<String, String> parameters = new HashMap<>();
      parameters.put("name", cardName);
      parameters.put("idList", board.getToDoListId());

      card = RestAssured.given()
              .spec(specification.requestSpec())
              .queryParams(parameters)
              .pathParam("cardPath", CARD_PATH)
              .when()
              .post("{cardPath}")
              .then()
              .statusCode(HttpStatus.SC_OK)
              .body("name", Matchers.equalTo(cardName))
              .extract()
              .response()
              .as(Card.class);
   }

   @Test(description = "Change board's background color")
   public void updateBoardColorTest() {
      String boardBackground = "pink";
      Map<String, String> parameters = new HashMap<>();
      parameters.put("prefs/background", boardBackground);

      Map<String, String> pathParameters = new HashMap<>();
      pathParameters.put("boardPath", BOARD_PATH);
      pathParameters.put("boardId", board.getBoardId());

      board = RestAssured.given()
              .spec(specification.requestSpec())
              .queryParams(parameters)
              .pathParams(pathParameters)
              .pathParam("boardId", board.getBoardId())
              .when()
              .put("{boardPath}/{boardId}")
              .then()
              .statusCode(HttpStatus.SC_OK)
              .body("prefs.background", Matchers.equalTo(boardBackground))
              .extract()
              .response()
              .as(Board.class);
   }

   @Test(description = "Get a card by Id")
   public void getCardById() {
      Map<String, String> pathParameters = new HashMap<>();
      pathParameters.put("cardPath", CARD_PATH);
      pathParameters.put("cardId", card.getCardId());

      RestAssured.given()
              .spec(specification.requestSpec())
              .pathParams(pathParameters)
              .when()
              .get("{cardPath}/{cardId}")
              .then()
              .statusCode(HttpStatus.SC_OK)
              .body("id", Matchers.equalTo(card.getCardId()))
              .body("name", Matchers.equalTo(card.getCardName()));
   }

   @Test(description = "Get all cards on a board")
   public void getBoardCards() {
      Map<String, String> pathParameters = new HashMap<>();
      pathParameters.put("boardPath", BOARD_PATH);
      pathParameters.put("boardId", board.getBoardId());
      pathParameters.put("cardPath", CARD_PATH);

      RestAssured.given()
              .spec(specification.requestSpec())
              .pathParams(pathParameters)
              .when()
              .get("{boardPath}/{boardId}/{cardPath}")
              .then()
              .statusCode(HttpStatus.SC_OK)
              .body("size()", Matchers.equalTo(1))
              .body("[0].name", Matchers.equalTo(card.getCardName()));
   }

   @Test(description = "Change card's background color")
   public void updateCardCoverColorTest() {
      String cardCoverColor = "green";
      Map<String, Object> requestBody = new HashMap<>();
      Map<String, String> cover = new HashMap<>();
      cover.put("color", cardCoverColor);
      requestBody.put("cover", cover);

      Map<String, String> pathParameters = new HashMap<>();
      pathParameters.put("cardPath", CARD_PATH);
      pathParameters.put("cardId", card.getCardId());

      card = RestAssured.given()
              .spec(specification.requestSpec())
              .body(requestBody)
              .pathParams(pathParameters)
              .when()
              .put("{cardPath}/{cardId}")
              .then()
              .statusCode(HttpStatus.SC_OK)
              .body("cover.color", Matchers.equalTo(cardCoverColor))
              .extract()
              .response()
              .as(Card.class);
   }

   @Test(description = "Change card's description")
   public void updateCardDescriptionTest() {
      String cardDescription = "My card description";

      Map<String, String> pathParameters = new HashMap<>();
      pathParameters.put("cardPath", CARD_PATH);
      pathParameters.put("cardId", card.getCardId());

      card = RestAssured.given()
              .spec(specification.requestSpec())
              .queryParam("desc", cardDescription)
              .pathParams(pathParameters)
              .when()
              .put("{cardPath}/{cardId}")
              .then()
              .statusCode(HttpStatus.SC_OK)
              .body("desc", Matchers.equalTo(cardDescription))
              .extract()
              .response()
              .as(Card.class);
   }

   @AfterClass
   public void deleteBoard() {
      RestAssured.given()
              .spec(specification.requestSpec())
              .pathParam("boardId", board.getBoardId())
              .when()
              .delete(BOARD_PATH + "/{boardId}")
              .then()
              .statusCode(HttpStatus.SC_OK)
              .body("$", Matchers.hasKey("_value"))
              .body("_value", Matchers.equalTo(null));
      card = null;
      board = null;
   }

}