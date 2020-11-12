package de.hhn.it.pp.components.learningCards;

import java.util.*;

public class Cardset {
    // Scanner for the Console inputs
    String title;
    // Map for Card Objectives
    Map<Integer,Card> cardset = new HashMap<Integer,Card>();

    int id;
    static int idCounter = 0;

    // Constructor for Cardset, sets Title
    public Cardset(String title) {
        setTitle(title);
        id = ++idCounter;
    }

    // sets the Title (console input)
    void setTitle(String title) {
     this.title = title;
    }

    // returns Title
    String getTitle() {
        return title;
    }

    // Adds Cardobject to ArrayList "cardset"
    void addCardtoSet(Card card) {
        cardset.put(card.getId(),card);
    }

    // Returns Card with entered index from the Cardset
    Card getCardfromSet(int i) {
        return cardset.get(i);
    }

    // removes Card with entered index from the Cardset
    void removeCardfromSet(int i) {
        cardset.remove(i);
    }

    int getId(){
        return id;
    }

    List<Integer> getCardIds(){
        List<Integer> results = new ArrayList<Integer>();
        Collection<Card> cards = cardset.values();
        for(Card card : cards){
            results.add(card.getId());
        }
        return results;
    }


}
