package com.epam.auto.homework_API03;

public class Card {

   private String cardId;
   private String cardName;
   private String coverColor;

   public Card() {

   }

   public Card(String cardId, String cardName) {
      this.cardId = cardId;
      this.cardName = cardName;
   }

   public String getCardId() {
      return cardId;
   }

   public void setCardId(String cardId) {
      this.cardId = cardId;
   }

   public String getCardName() {
      return cardName;
   }

   public void setCardName(String cardName) {
      this.cardName = cardName;
   }

   public String getCoverColor() {
      return coverColor;
   }

   public void setCoverColor(String coverColor) {
      this.coverColor = coverColor;
   }
}
