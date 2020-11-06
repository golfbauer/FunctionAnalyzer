package de.hhn.it.pp.components.learningCards;

import java.util.ArrayList;
import java.util.Scanner;

public class Cardset {
    // Scanner for the Console inputs
    String title;
    // ArrayList for Card Objectives
    ArrayList<Card> cardset = new ArrayList<Card>();

    // Constructor for Cardset, sets Title
    public Cardset(String title) {
        setTitle(title);
    }

    // sets the Title (console input)
    void setTitle(String title) {
     this.title = title
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
