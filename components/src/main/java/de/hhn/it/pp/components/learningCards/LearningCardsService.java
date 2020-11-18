package de.hhn.it.pp.components.learningCards;

import de.hhn.it.pp.components.learningCards.exceptions.CardNotFoundException;
import de.hhn.it.pp.components.learningCards.exceptions.CardsetNotFoundException;

public interface LearningCardsService {

    /**
     * creates a cardset
     *
     * @param cardsetTitle title of the cardset
     * @return id of the creating cardset
     */
    int createCardset(String cardsetTitle);


    /**
     * removes the cardset
     *
     * @param cardsetId id of the cardset
     * @throws CardsetNotFoundException if the cardset id does not exist
     */
    void removeCardset(int cardsetId) throws CardsetNotFoundException;

    /**
     * creates a card with headline and text
     *
     * @param cardsetId    the id of the cardset
     * @param cardHeadline the headline of the card
     * @throws CardsetNotFoundException if the given cardset id does not exist
     */
    int addCardToCardset(int cardsetId, String cardHeadline, String cardTextQ, String cardTextA)
            throws CardsetNotFoundException;

    /**
     * creates a card with just a text
     *
     * @param cardsetId the index of the cardset
     * @throws CardsetNotFoundException if cardset id cannot be found
     */
    int addCardToCardset(int cardsetId, String cardTextQ, String cardTextA) throws CardsetNotFoundException;

    /**
     * Deletes a card
     *
     * @param cardId the id of the card
     * @throws CardsetNotFoundException if cardset id does not exist
     * @throws CardNotFoundException    if card id does not exist
     */
    void removeCardFromCardset(int cardId)
            throws CardsetNotFoundException, CardNotFoundException;

    /**
     * Changes the text of a card
     *
     * @param cardId       index of the card
     * @param newCardTextQ new textQ
     * @throws CardsetNotFoundException if cardset id cannot be found
     * @throws CardNotFoundException    if card id cannot be found
     */
    void editCardQuestionTextFromCardset(int cardId, String newCardTextQ)
            throws CardsetNotFoundException, CardNotFoundException;

    /**
     * Changes the text of a card
     *
     * @param cardId       index of the card
     * @param newCardTextA new textA
     * @throws CardsetNotFoundException if cardset id cannot be found
     * @throws CardNotFoundException    if card id cannot be found
     */
    void editCardAnswerTextFromCardset(int cardId, String newCardTextA)
            throws CardsetNotFoundException, CardNotFoundException;

    /**
     * Shows the cards within a cardset
     *
     * @param cardsetId the id of the cardset
     * @param status    the status of the cards, which will be shown
     * @throws CardsetNotFoundException if cardset id cannot be found
     */
    void startLearningSession(int cardsetId, Status[] status) throws CardsetNotFoundException;

    /**
     * shows unseen and unsolved cards within a cardset
     *
     * @param cardsetId  the if of the cardset
     * @throws CardsetNotFoundException if cardset id cannot be found
     */
    void repeatUnsolvedAndUnseenCards (int cardsetId) throws CardsetNotFoundException;


}
