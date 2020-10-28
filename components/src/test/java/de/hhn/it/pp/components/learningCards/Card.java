package de.hhn.it.pp.components.learningCards;

import java.util.Scanner;

class Card {
  // Scanner for the Console inputs
  Scanner sc = new Scanner(System.in);
  // Card Text
  String text;
  // Card Headline
  String headline;
  // marker if card is solved
  boolean solved = false;
  // marker if card has been seen
  boolean seen = false;

  // Card Constructor, sets Headline, Text and Cardnumber
  Card() {
    setHeadline();
    setText();
  }

  // Method to set Headline (console input)
  void setHeadline() {
    System.out.println("Add Headline: ");
    headline = sc.nextLine();
  }

  // not intended for demo version (empty method)
  void addPicture() {}

  // Method to set text (console input)
  void setText() {
    System.out.println("Add Text: ");
    text = sc.nextLine();
  }

  // Method to change allready set Text with output from the old text and console Input for new Text
  void editText() {
    System.out.println("Old Text:\n" + text + "\n new Text:");
    setText();
  }

  // returns text
  String getText() {
    return text;
  }



  // returns Headline
  String getHeadline() {
    return headline;
  }

  // Combines all Strings in one String and returns it.
  String getCardinfo() {
    String info;
    info = getHeadline() + getText();
    return info;
  }

  // sets seen status to true
  void cardSeen() {
    seen = true;
  }

  // sets solved status to true
  void cardSolved() {
    solved = true;
  }

  // sets solved status to false (used in case a Card was flagged by mistake (or something else))
  void cardUnsolved() {
    solved = false;
  }
}


