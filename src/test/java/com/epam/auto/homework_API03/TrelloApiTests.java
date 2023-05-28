package com.epam.auto.homework_API03;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class TrelloApiTests {

   private final static String URL = "https://api.trello.com/1/";
   private final static String KEY = "123";
   private final static String TOKEN = "123";

   private static Board board;
   private static Card card;

   @Test
   public void createBoardTest() {

      Response response = RestAssured.given()
              .spec(Specifications.requestSpec(URL))
              .when()
              .post("boards?key=" + KEY + "&token=" + TOKEN + "&name=Test Board")
              .then()
              .statusCode(HttpStatus.SC_OK)
              .body("name", Matchers.equalTo("Test Board"))
              .extract()
              .response();

      String id = response.jsonPath().getString("id");
      String name = response.jsonPath().getString("name");
      board = new Board();
      board.setBoardId(id);
      board.setBoardName(name);

      String toDolist = RestAssured.given()
              .spec(Specifications.requestSpec(URL))
              .when()
              .get("boards/" + board.getBoardId() + "/lists?key=" + KEY + "&token=" + TOKEN)
              .then()
              .extract()
              .response()
              .path("find { it.name == 'To Do' }.id");
      board.setToDoListId(toDolist);
   }

   @Test(dependsOnMethods = "createBoardTest")
   public void updateBoardTest() {

      Response response = RestAssured.given()
              .spec(Specifications.requestSpec(URL))
              .when()
              .put("boards/" + board.getBoardId() + "?key=" + KEY + "&token=" + TOKEN + "&prefs/background=pink")
              .then()
              .statusCode(HttpStatus.SC_OK)
              .body("prefs.background", Matchers.equalTo("pink"))
              .extract()
              .response();

      String color = response.jsonPath().getString("prefs.background");
      board.setBackground(color);
   }

   @Test(dependsOnMethods = "updateBoardTest")
   public void createCardTest() {

      Response response = RestAssured.given()
              .spec(Specifications.requestSpec(URL))
              .when()
              .post("cards?key=" + KEY + "&token=" + TOKEN + "&name=First card&idList=" + board.getToDoListId())
              .then()
              .statusCode(HttpStatus.SC_OK)
              .body("name", Matchers.equalTo("First card"))
              .extract()
              .response();

      String cardId = response.jsonPath().getString("id");
      String cardName = response.jsonPath().getString("name");
      card = new Card();
      card.setCardId(cardId);
      card.setCardName(cardName);
   }

   @Test(dependsOnMethods = "createCardTest")
   public void updateCardTest() {

      Map<String, Object> requestBody = new HashMap<>();
      Map<String, String> cover = new HashMap<>();
      cover.put("color", "green");
      requestBody.put("cover", cover);

      Response response = RestAssured.given()
              .spec(Specifications.requestSpec(URL))
              .body(requestBody)
              .when()
              .put("cards/" + card.getCardId() + "?key=" + KEY + "&token=" + TOKEN)
              .then()
              .statusCode(HttpStatus.SC_OK)
              .body("cover.color", Matchers.equalTo("green"))
              .extract()
              .response();

      String cardColor = response.jsonPath().getString("cover.color");
      card.setCoverColor(cardColor);
   }

   @Test(dependsOnMethods = "updateCardTest")
   public void deleteBoardTest() {

      Response response = RestAssured.given()
              .spec(Specifications.requestSpec(URL))
              .when()
              .delete("boards/" + board.getBoardId() + "?key=" + KEY + "&token=" + TOKEN)
              .then()
              .statusCode(HttpStatus.SC_OK)
              .body("$", Matchers.hasKey("_value"))
              .body("_value", Matchers.equalTo(null))
              .extract()
              .response();

      board.setBackground(null);
      board.setBoardName(null);
      board.setBoardId(null);
   }

}
