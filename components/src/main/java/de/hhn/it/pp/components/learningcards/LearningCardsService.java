package de.hhn.it.pp.components.learningcards;

import de.hhn.it.pp.components.learningcards.exceptions.CardNotFoundException;
import de.hhn.it.pp.components.learningcards.exceptions.CardsetNotFoundException;

import java.util.List;

public interface LearningCardsService {

  /**
   * add cardsets and their cards.
   *
   * @param newCardsets cardsets
   */
  void addCardsets(Cardset... newCardsets);


  /**
   * returns number of cardsets.
   * @return the number of added cardsets
   */
  int getNumberOfCardsets();

  /**
   * creates a cardset.
   *
   * @param cardsetTitle title of the cardset
   * @return id of the creating cardset
   */
  int createCardset(String cardsetTitle);


  /**
   * deletes the cardset.
   *
   * @param cardsetId id of the cardset
   * @throws CardsetNotFoundException if the cardset id does not exist
   */
  void removeCardset(int cardsetId) throws CardsetNotFoundException;

  /**
   * creates a card with headline, answer and question text.
   *
   * @param cardHeadline the headline of the card
   * @param cardTextQ    the question text
   * @param cardTextA    the answer text
   * @return id of the creating card
   */
  int createCard(String cardHeadline, String cardTextQ, String cardTextA);

  /**
   * creates a card with answer and question text.
   *
   * @param cardTextQ the question text
   * @param cardTextA the answer text
   * @return id of the creating card
   */
  int createCard(String cardTextQ, String cardTextA);

  /**
   *  returns the number of cards.
   * @return the number of added cards
   */
  int getNumberOfCards();

  /**
   * puts the created card into the cardset.
   *
   * @param cardsetId the id of the carset in which the card is added
   * @param cardId    id of the card
   * @throws CardsetNotFoundException if no cardset was found
   * @throws CardNotFoundException if no card was found
   */
  void addCardToCardset(int cardsetId, int cardId)
        throws CardsetNotFoundException, CardNotFoundException;

  /**
   * creates a card with headline, answer and question text.
   * puts the card into a cardset.
   *
   * @param cardsetId    the id of the cardset
   * @param cardHeadline the headline of the card
   * @param cardTextQ    the question text
   * @param cardTextA    the answer text
   * @return id of the creating cardset
   * @throws CardsetNotFoundException if the given cardset id does not exist
   */
  int addCardToCardset(int cardsetId, String cardHeadline, String cardTextQ, String cardTextA)
        throws CardsetNotFoundException;

  /**
   * creates a card with answer and question text.
   * puts the card into a cardset.
   *
   * @param cardsetId the id of the cardset
   * @param cardTextQ the question text
   * @param cardTextA the answer text
   * @return id of the creating cardset
   * @throws CardsetNotFoundException if the given cardset id does not exist
   */
  int addCardToCardset(int cardsetId, String cardTextQ, String cardTextA)
        throws CardsetNotFoundException;

  /**
   * deletes the card completely.
   *
   * @param cardId the index of the card
   * @throws CardNotFoundException if card id does not exist
   */
  void deleteCard(int cardId) throws CardNotFoundException;

  /**
   * removes the card from the cardset.
   *
   * @param cardId the id of the card
   * @throws CardsetNotFoundException if cardset id does not exist
   * @throws CardNotFoundException    if card id does not exist
   */
  void removeCardFromCardset(int cardsetId, int cardId)
        throws CardsetNotFoundException, CardNotFoundException;

  /**
   * Changes the question text of a card.
   *
   * @param cardId       index of the card
   * @param newCardTextQ new textQ
   * @throws CardsetNotFoundException if cardset id cannot be found
   * @throws CardNotFoundException    if card id cannot be found
   */
  void editCardQuestionTextFromCardset(int cardId, String newCardTextQ)
        throws CardNotFoundException;

  /**
   * Changes the answer text of a card.
   *
   * @param cardId       index of the card
   * @param newCardTextA new textA
   * @throws CardsetNotFoundException if cardset id cannot be found
   * @throws CardNotFoundException    if card id cannot be found
   */
  void editCardAnswerTextFromCardset(int cardId, String newCardTextA)
        throws CardNotFoundException;

  /**
   * Shows the cards within a cardset.
   *
   * @param cardsetId the id of the cardset
   * @param status    the status of the cards, which will be shown
   * @throws CardsetNotFoundException if cardset id cannot be found
   */
  void startLearningSession(int cardsetId, Status[] status) throws CardsetNotFoundException;

  /**
   * shows unseen and unsolved cards within a cardset.
   *
   * @param cardsetId the if of the cardset
   * @throws CardsetNotFoundException if cardset id cannot be found
   */
  void repeatUnsolvedAndUnseenCards(int cardsetId) throws CardsetNotFoundException;

  /**
   * stops the running session.
   */
  void stopLearningSession();


  /**
   * Returns a list of added cardset IDs.
   *
   * @return List of id of added cardsets
   */
  List<Integer> getCardsetIds();

  /**
   * Returns a list of added card IDs.
   *
   * @return List of id of added cards
   */
  List<Integer> getCardsIds();

  /**
   * Returns a list of added cardsets.
   *
   * @return List of added cardsets
   */
  List<Cardset> getCardsets();

  /**
   * Returns a list of added cards.
   *
   * @return List of added cards
   */
  List<Card> getCards();

  /**
   * Returns a cardset according to its ID.
   *
   * @return a cardset
   */
  Cardset getCardset(int cardsetId);

  /**
   * Returns a card according to its ID.
   *
   * @return a card
   */
  Card getCardFromCol(int cardId);

  /**
   * Changes the headline of a card according its ID
   */
  void setCardHeadline(int cardId, String newHeadline) throws CardNotFoundException;

  /**
   * Returns the headline of a card according its ID
   *
   * @return  card headline
   */
  String getCardHeadline(int cardId) throws CardNotFoundException;
}
