package de.hhn.it.pp.components.learningCards;

import de.hhn.it.pp.components.learningCards.exceptions.CardNotFoundException;
import de.hhn.it.pp.components.learningCards.exceptions.CardsetNotFoundException;

public interface LearningCardsService {

    /**
     * creates a cardset, puts into a hashmap according to the id of the cardset
     *
     * @param cardsetTitle title of the cardset
     * @return id of the creating cardset
     */
    int createCardset(String cardsetTitle);

    /**
     * Deletes a cardset, removes from the list
     *
     * @param cardsetIndex the index of the cardset in the list
     * @throws CardsetNotFoundException if cardset id cannot be found in the hashmap
     */
    /**
     * Deletes a cardset, removes from the hashmap according to its id
     *
     * @param cardsetId id of the cardset
     * @throws CardsetNotFoundException if cardset id cannot be found in the hashmap
     */
    void removeCardset(int cardsetId) throws CardsetNotFoundException;

    /**
     * Creates a card with headline and text
     * adds the card in the hashmaps, which belong to a Cardset and LearningCardManager
     * each card takes a different id and it cannot be changed
     *
     * @param cardsetId    the id of the cardset
     * @param cardHeadline the headline of the card
     * @throws CardsetNotFoundException if cardset id cannot be found in the hashmap
     */
    int addCardToCardset(int cardsetId, String cardHeadline, String cardTextQ, String cardTextA)
            throws CardsetNotFoundException;

    /**
     * Creates a card with just a text
     * adds the card in the hashmaps, which belong to a cardset and LearningCardManager
     * each card takes a different id and it cannot be changed
     *
     * @param cardsetId the index of the cardset
     * @throws CardsetNotFoundException if cardset id cannot be found in the hashmap
     */
    int addCardToCardset(int cardsetId, String cardTextQ, String cardTextA) throws CardsetNotFoundException;

    /**
     * Deletes a card
     * removes the card from the hashmaps, which belongs to a cardset and LearningCardManager
     *
     * @param cardId the text of the card
     * @throws CardsetNotFoundException if cardset id cannot be found in the hashmap
     * @throws CardNotFoundException    if card id cannot be found in the hashmap
     */
    void removeCardFromCardset(int cardId)
            throws CardsetNotFoundException, CardNotFoundException;

    /**
     * Changes the text of a card
     *
     * @param cardId       index of the card
     * @param newCardTextQ new textQ
     * @throws CardsetNotFoundException if cardset id cannot be found in the hashmap
     * @throws CardNotFoundException    if card id cannot be found in the hashmap
     */
    void editCardQuestionTextFromCardset(int cardId, String newCardTextQ)
            throws CardsetNotFoundException, CardNotFoundException;

    /**
     * Changes the text of a card
     *
     * @param cardId       index of the card
     * @param newCardTextA new textA
     * @throws CardsetNotFoundException if cardset id cannot be found in the hashmap
     * @throws CardNotFoundException    if card id cannot be found in the hashmap
     */
    void editCardAnswerTextFromCardset(int cardId, String newCardTextA)
            throws CardsetNotFoundException, CardNotFoundException;

    /**
     * Shows the cards within a cardset
     *
     * @param cardsetId
     * @param status    the status of the cards, which will be shown
     * @throws CardsetNotFoundException if cardset id cannot be found in the hashmap
     */
    void startLearningSession(int cardsetId, Status[] status) throws CardsetNotFoundException;


}
