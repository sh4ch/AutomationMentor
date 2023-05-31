package com.epam.auto.homework_API03;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Card {

   @JsonAlias("id")
   private String cardId;
   @JsonAlias("name")
   private String cardName;
   @JsonAlias("desc")
   private String cardDescription;
   private Cover cover;

   @Data
   @JsonIgnoreProperties(ignoreUnknown = true)
   public static class Cover {

      private String color;
   }

   public Card(String cardId, String cardName) {
      this.cardId = cardId;
      this.cardName = cardName;
   }
}