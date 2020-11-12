package de.hhn.it.pp.components.learningCards;

import java.util.*;

public class LearningCardManager {
    // Map for all created Card Objects
    Map<Integer, Card> cardCol = new HashMap<Integer, Card>();
    // Map for all created Cardsets
    Map<Integer, Cardset> cardsetCol = new HashMap<Integer, Cardset>();

    // Method to create a Cardset Object
    // returns the id of created cardset
    int createCardSet(String title) {
        Cardset cs = new Cardset(title);
        cardsetCol.put(cs.getId(), cs);
        return cs.getId();
    }

    // Method to create a Card Object
    // returns the id of created card
    int newCard(int cardsetId, String headline, String textQ, String textA) {
        Card name = new Card(headline, textQ, textA);
        cardsetCol.get(cardsetId).addCardtoSet(name);
        cardCol.put(name.getId(), name);
        return name.getId();
    }

    // Method to get a Card from ArrayList for all Cards
    Card getCardFromCol(int i) {
        return cardCol.get(i);
    }


    // Method to remove a Card from ArrayList for all Cards (to "delete" a card)
    int removeCardFromCol(int i) {
        if (!cardCol.containsKey(i)) {
            return -1;
        }
        Card card = cardCol.remove(i);
        for (Cardset cardset : cardsetCol.values()) {
            if (cardset.getCardIds().contains(card.getId())) {
                cardset.removeCardfromSet(card.getId());
                break;
            }
        }
        return card.getId();
    }

    // returns a cardset from CardsetCol
    Cardset getCardset(int i) {
        return cardsetCol.get(i);
    }

    // removes A cardset from CardsetCol
    void removeCardset(int i) {
        Cardset c = cardsetCol.remove(i);
    }

    // returns the indexes of all Cards
    List<Integer> getAllCardsIds() {
        List<Integer> results = new ArrayList<Integer>();
        Collection<Card> cards = cardCol.values();
        for (Card card : cards) {
            results.add(card.getId());
        }
        return results;
    }

    // returns the indexes of all Cardsets
    List<Integer> getCardsetIds() {
        List<Integer> results = new ArrayList<Integer>();
        Collection<Cardset> cardsets = cardsetCol.values();
        for (Cardset cardset : cardsets) {
            results.add(cardset.getId());
        }
        return results;
    }
}


