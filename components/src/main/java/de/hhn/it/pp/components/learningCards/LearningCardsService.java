package de.hhn.it.pp.components.learningCards;

import de.hhn.it.pp.components.exceptions.CardNotFoundException;
import de.hhn.it.pp.components.exceptions.CardsetNotFoundException;

public interface LearningCardsService {

    /**
     * Creates a cardset, adds into a list
     *
     * @param cardsetTitle name of the creating cardset
     */
    void createCardSet(String cardsetTitle);

    /**
     * Deletes a cardset, removes from the list
     *
     * @param cardsetIndex the index of the cardset in the list
     * @throws CardsetNotFoundException
     */
    void removeCardSet(int cardsetIndex) throws CardsetNotFoundException;

    /**
     * Creates a card with headline and text
     * adds the card in the list, which belongs to a cardset
     *
     * @param cardsetIndex the index of the cardset in the list
     * @param cardHeadline the headline of the card
     * @param cardText     the text of the card
     * @throws CardsetNotFoundException
     * @throws CardNotFoundException
     */
    void addCardToCardSet(int cardsetIndex, String cardHeadline, String cardText)
            throws CardsetNotFoundException, CardNotFoundException;

    /**
     * Creates a card with just a text
     * adds the card in the list, which belongs to a cardset
     *
     * @param cardSetIndex the index of the cardset in the list
     * @param cardText     the text of the card
     * @throws CardsetNotFoundException
     */
    void addCardToCardSet(int cardSetIndex, String cardText) throws CardsetNotFoundException;

    /**
     * Deletes a card
     * removes the card from the list, which belongs to a cardset
     *
     * @param cardSetIndex the index of the cardset in the list
     * @param cardIndex    the text of the card
     * @throws CardsetNotFoundException
     * @throws CardNotFoundException
     */
    void removeCardFromCardSet(int cardSetIndex, int cardIndex)
            throws CardsetNotFoundException, CardNotFoundException;

    /**
     * Changes the text of a card
     *
     * @param cardSetIndex index of the cardset
     * @param cardIndex    index of the card
     * @param newCardText  new text
     * @throws CardsetNotFoundException
     * @throws CardNotFoundException
     */
    void editCardFromCardSet(int cardSetIndex, int cardIndex, String newCardText)
            throws CardsetNotFoundException, CardNotFoundException;

    /**
     * Shows all the cards within a cardset
     *
     * @param cardSetIndex the index of the cardset, which should be learned
     * @throws CardsetNotFoundException
     */
    void startLearningSession(int cardSetIndex) throws CardsetNotFoundException;

    /**
     * Shows just unseen and unsolved cards within a cardset
     *
     * @param cardSetIndex the index of the cardset, which should be learned
     * @throws CardsetNotFoundException
     */
    void repeatUnseenAndUnsolvedCards(int cardSetIndex) throws CardsetNotFoundException;
}
