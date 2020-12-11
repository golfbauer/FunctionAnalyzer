package de.hhn.it.pp.components.learningCards;

import de.hhn.it.pp.components.example.coffeemakerservice.provider.WnckCoffeeMakerService;

import java.util.ArrayList;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Cardset {

  private static final org.slf4j.Logger logger =
       org.slf4j.LoggerFactory.getLogger(Cardset.class);

  String title;

  Map<Integer, Card> cardset = new HashMap<Integer, Card>();

  int id;
  static int idCounter = 0;

  /**
   * Constructor of the Cardset class.
   *
   * @param title title to indentify what the cardset is about
   */
  public Cardset(String title) {
    setTitle(title);
    id = ++idCounter;
  }

  /**
   * Sets the title of the cardset.
   *
   * @param title title to indentify what the cardset is about
   */
  void setTitle(String title) {
    this.title = title;
  }

  /**
   * Returns the title of the cardset.
   *
   * @return title of the cardset
   */
  public String getTitle() {
    return title;
  }

  /**
   * adds card to the cardset.
   *
   * @param card which gets added to the cardset
   */
  public void addCardtoSet(Card card) {
    cardset.put(card.getId(), card);
  }

  /**
   * returns a card with the id i from the cardset.
   *
   * @param i id to identify a card in cardset
   * @return the card with id i
   */
  Card getCardfromSet(int i) {
    return cardset.get(i);
  }

  /**
   * removes a card with the id i from the cardset.
   *
   * @param i id to identify a card in cardset
   */
  public void removeCardfromSet(int i) {
    cardset.remove(i);
  }

  /**
   * returns the id of cardset.
   *
   * @return id of cardset
   */
  public int getId() {
    return id;
  }

  /**
   * returns a list of all card ids in the cardset.
   *
   * @return all ids of all cards in cardset in a list
   */
  public List<Integer> getCardIds() {
    List<Integer> results = new ArrayList<Integer>();
    Collection<Card> cards = cardset.values();
    for (Card card : cards) {
      results.add(card.getId());
    }
    return results;
  }

  /**
   * returns a list of all cards in the cardset.
   *
   * @return all of the cards in cardset in a list
   */
  public List<Card> getCards() {

    //List<Value> list = new ArrayList<Value>(map.values());
    List<Card> cards = new ArrayList<Card>(cardset.values());

    return cards;
  }

  public int getNumberOfCard() {
    return cardset.size();
  }


}
