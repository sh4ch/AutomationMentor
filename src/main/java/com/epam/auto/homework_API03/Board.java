package com.epam.auto.homework_API03;

public class Board {

   private String boardId;
   private String boardName;
   private String background = "blue";
   private String toDoListId;

   public Board(String boardId, String boardName) {
      this.boardId = boardId;
      this.boardName = boardName;

   }

   public Board() {

   }

   public String getBoardId() {
      return boardId;
   }

   public void setBoardId(String boardId) {
      this.boardId = boardId;
   }

   public String getBoardName() {
      return boardName;
   }

   public void setBoardName(String boardName) {
      this.boardName = boardName;
   }

   public String getBackground() {
      return background;
   }

   public void setBackground(String background) {
      this.background = background;
   }

   public String getToDoListId() {
      return toDoListId;
   }

   public void setToDoListId(String toDoListId) {
      this.toDoListId = toDoListId;
   }
}
