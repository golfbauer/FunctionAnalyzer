package de.hhn.it.pp.components.learningCards;

import java.util.ArrayList;

public class LearningCardManager {
  // ArrayList for all created Card Objects
  ArrayList<Card> cardCol = new ArrayList<Card>();
  // ArrayList for all created Cardsets
  ArrayList<Cardset> cardsetCol = new ArrayList<Cardset>();

  // Method to create a Cardset Object
  void createCardSet(String title) {
    Cardset cs = new Cardset(title);
    cardsetCol.add(cs);
  }

  // Method to create a Card Object
  public void newCard(String headline, String textQ, String textA) {
    Card name = new Card(headline, textQ, textA);
    cardCol.add(name);
  }

  // Method to get a Card from ArrayList for all Cards
  Card getCardFromCol(int i) {

    return cardCol.get(i);
  }


  // Method to remove a Card from ArrayList for all Cards (to "delete" a card)
  void removeCardFromCol(int i) {
    cardCol.remove(i);
  }

  // returns a cardset from ArrayList CardsetCol
  Cardset getCardset(int i) {
    return cardsetCol.get(i);
  }

  // removes A cardset from Arraylist CardsetCol
  void removeCardset(int i) {
    cardsetCol.remove(i);
  }
}


