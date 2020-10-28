package de.hhn.it.pp.components.learningCards;

import java.util.ArrayList;
import java.util.Scanner;

public class Cardset {
  // Scanner for the Console inputs
  Scanner sc = new Scanner(System.in);
  String title;
  // ArrayList for Card Objectives
  ArrayList<Card> cardset = new ArrayList<Card>();

  // Constructor for Cardset, sets Title
  public Cardset() {
    setTitle();
  }

  // sets the Title (console input)
  void setTitle() {
    System.out.println("Title:");
    title = sc.nextLine();
  }

  // returns Title
  String getTitle() {
    return title;
  }

  // Adds Cardobject to ArrayList "cardset"
  void addCardtoSet(Card card) {
    cardset.add(card);
  }

  // Returns Card with entered index from the Cardset
  Card getCardfromSet(int i) {
    return cardset.get(i);
  }

  // removes Card with entered index from the Cardset
  void removeCardfromSet(int i) {
    cardset.remove(i);
  }
}
