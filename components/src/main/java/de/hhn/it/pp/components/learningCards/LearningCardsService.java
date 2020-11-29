package de.hhn.it.pp.components.learningCards;

import de.hhn.it.pp.components.learningCards.exceptions.CardNotFoundException;
import de.hhn.it.pp.components.learningCards.exceptions.CardsetNotFoundException;

public interface LearningCardsService {

    /**
     * add cardsets and their cards
     *
     * @param newCardsets cardsets
     */
    void addCardsets(Cardset... newCardsets);

    /**
     * creates a cardset
     *
     * @param cardsetTitle title of the cardset
     * @return id of the creating cardset
     */
    int createCardset(String cardsetTitle);


    /**
     * deletes the cardset
     *
     * @param cardsetId id of the cardset
     * @throws CardsetNotFoundException if the cardset id does not exist
     */
    void removeCardset(int cardsetId) throws CardsetNotFoundException;

    /**
     * creates a card with headline, answer and question text
     *
     * @param cardHeadline the headline of the card
     * @param cardTextQ the question text
     * @param cardTextA the answer text
     * @return id of the creating card
     */
    int createCard(String cardHeadline, String cardTextQ, String cardTextA);

    /**
     * creates a card with answer and question text
     *
     * @param cardTextQ the question text
     * @param cardTextA the answer text
     * @return id of the creating card
     */
    int createCard(String cardTextQ, String cardTextA);

    /**
     * puts the created card into the cardset
     *
     * @param cardsetId the id of the carset in which the card is added
     * @param cardId id of the card
     * @throws CardsetNotFoundException
     * @throws CardNotFoundException
     */
    void addCardToCardset(int cardsetId, int cardId) throws CardsetNotFoundException, CardNotFoundException;

    /**
     * creates a card with headline, answer and question text
     * puts the card into a cardset
     *
     * @param cardsetId    the id of the cardset
     * @param cardHeadline the headline of the card
     * @param cardTextQ the question text
     * @param cardTextA the answer text
     * @return id of the creating cardset
     * @throws CardsetNotFoundException if the given cardset id does not exist
     */
    int addCardToCardset(int cardsetId, String cardHeadline, String cardTextQ, String cardTextA)
            throws CardsetNotFoundException;

    /**
     * creates a card with answer and question text
     * puts the card into a cardset
     *
     * @param cardsetId    the id of the cardset
     * @param cardTextQ the question text
     * @param cardTextA the answer text
     * @return id of the creating cardset
     * @throws CardsetNotFoundException if the given cardset id does not exist
     */
    int addCardToCardset(int cardsetId, String cardTextQ, String cardTextA) throws CardsetNotFoundException;

    /**
     * deletes the card completely
     *
     * @param cardId the index of the card
     * @throws CardNotFoundException    if card id does not exist
     */
    void deleteCard(int cardId) throws CardNotFoundException;

    /**
     * removes the card from the cardset
     *
     * @param cardId the id of the card
     * @throws CardsetNotFoundException if cardset id does not exist
     * @throws CardNotFoundException    if card id does not exist
     */
    void removeCardFromCardset(int cardsetId, int cardId)
            throws CardsetNotFoundException, CardNotFoundException;

    /**
     * Changes the question text of a card
     *
     * @param cardId       index of the card
     * @param newCardTextQ new textQ
     * @throws CardsetNotFoundException if cardset id cannot be found
     * @throws CardNotFoundException    if card id cannot be found
     */
    void editCardQuestionTextFromCardset(int cardId, String newCardTextQ)
            throws CardNotFoundException;

    /**
     * Changes the answer text of a card
     *
     * @param cardId       index of the card
     * @param newCardTextA new textA
     * @throws CardsetNotFoundException if cardset id cannot be found
     * @throws CardNotFoundException    if card id cannot be found
     */
    void editCardAnswerTextFromCardset(int cardId, String newCardTextA)
            throws CardNotFoundException;

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

    /**
     * stops the running session
     */
    void stopLearningSession();

}
