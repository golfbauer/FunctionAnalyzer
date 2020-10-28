package de.hhn.it.pp.components.learningCards;

import de.hhn.it.pp.components.exceptions.CardNotFoundException;
import de.hhn.it.pp.components.exceptions.CardsetNotFoundException;

public interface LearningCardsService {

    void createCardSet(String cardsetTitle);

    void removeCardSet(int cardsetIndex) throws CardsetNotFoundException;

    void addCardToCardSet(int cardsetIndex, String cardHeadline, String cardText)
            throws CardsetNotFoundException, CardNotFoundException;

    void addCardToCardSet(int cardSetIndex, String cardText) throws CardsetNotFoundException;

    void removeCardFromCardSet(int cardSetIndex, int cardIndex)
            throws CardsetNotFoundException, CardNotFoundException;

    void editCardFromCardSet(int cardSetIndex, int cardIndex, String newCardText)
            throws CardsetNotFoundException, CardNotFoundException;

    void startLearningSession(int cardSetIndex) throws CardsetNotFoundException;

    void repeatUnseenAndUnsolvedCards(int cardSetIndex) throws CardsetNotFoundException;
}
