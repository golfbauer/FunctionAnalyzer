package de.hhn.it.pp.components.learningCards;

import java.util.*;

public class LearningCardManager {

  Map<Integer, Card> cardCol = new HashMap<Integer, Card>();
  // Map for all created Cardsets
  Map<Integer, Cardset> cardsetCol = new HashMap<Integer, Cardset>();

  /**
   * method to create a cardset and return the id of the new cardset
   * 
   * @param title title to indentify what the cardset is about
   * @return id of the cardset to identify the currently created cardset
   */
  int createCardSet(String title) {
    Cardset cs = new Cardset(title);
    cardsetCol.put(cs.getId(), cs);
    return cs.getId();
  }

  /**
   * method to create a new card and return the id of the new card
   * 
   * @param cardsetId identifer of cardset which the new cards get added to
   * @param headline headline of the classify the card topic
   * @param textQ questiontext of the new card
   * @param textA answertext of the new card
   * @return id of the card to identify the currently created card
   */
  int newCard(int cardsetId, String headline, String textQ, String textA) {
    Card name = new Card(headline, textQ, textA);
    cardsetCol.get(cardsetId).addCardtoSet(name);
    cardCol.put(name.getId(), name);
    return name.getId();
  }

  /**
   * method that returns the card with the id i from the cardCol list
   * 
   * @param i identifier of card
   * @return the card with id i in list cardCol
   * 
   */
  Card getCardFromCol(int i) {
    return cardCol.get(i);
  }


  /**
   * method to remove card from cardCol list and cardset
   * 
   * @param i id of the card that is about to be removed
   */
  void removeCardFromCol(int i) {
    Card card = cardCol.remove(i);
    for (Cardset cardset : cardsetCol.values()) {
      if (cardset.getCardIds().contains(card.getId())) {
        cardset.removeCardfromSet(card.getId());
        return;
      }
    }
  }

  /**
   * method to return a cardset from cardsetCol
   * 
   * @param i identifier of what carset should be returned
   * @return cardset out of cardsetCol with the id i
   */
  Cardset getCardset(int i) {
    return cardsetCol.get(i);
  }

  /**
   * method to remove a cardset from cardsetCol
   * 
   * @param i identifier of what cardset should be removed from cardsetCol
   */
  void removeCardset(int i) {
    Cardset c = cardsetCol.remove(i);
  }

  /**
   * method to return all cardIds
   * 
   * @return all ids in a integer list named results
   */
  List<Integer> getAllCardsIds() {
    List<Integer> results = new ArrayList<Integer>();
    Collection<Card> cards = cardCol.values();
    for (Card card : cards) {
      results.add(card.getId());
    }
    return results;
  }

  /**
   * method to return all cardsetIds
   * 
   * @return all ids in a integer list named results
   */
  List<Integer> getCardsetIds() {
    List<Integer> results = new ArrayList<Integer>();
    Collection<Cardset> cardsets = cardsetCol.values();
    for (Cardset cardset : cardsets) {
      results.add(cardset.getId());
    }
    return results;
  }
}


