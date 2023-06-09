package com.epam.auto.homework_API03;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Board {

   @JsonAlias("id")
   private String boardId;
   @JsonAlias("name")
   private String boardName;
   private Prefs prefs;
   private String toDoListId;

   @Data
   @JsonIgnoreProperties(ignoreUnknown = true)
   public static class Prefs {
      private String background;
   }
}