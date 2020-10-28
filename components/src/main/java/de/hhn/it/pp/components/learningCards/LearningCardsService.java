package de.hhn.it.pp.components.learningCards;

public interface LearningCardsService {

    void createCardSet(String cardsetTitle);

    void removeCardSet(int cardsetIndex);

    void addCardToCardSet(int cardsetIndex, String cardHeadline, String cardText);

    void addCardToCardSet(int cardSetIndex, String cardText);

    void removeCardFromCardSet(int cardSetIndex, int cardIndex);

    void editCardFromCardSet(int cardSetIndex, int cardIndex, String newCardText);

    void startLearningSession(int cardSetIndex);

    void repeatUnseenAndUnsolvedCards(int cardSetIndex);
}
