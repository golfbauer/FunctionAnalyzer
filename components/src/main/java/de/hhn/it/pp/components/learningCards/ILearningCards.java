package de.hhn.it.pp.components.learningCards;

public interface ILearningCards {

    void createCardSet();

    void removeCardSet(int cardSetIndex);

    void editCardSetTitle(int cardSetIndex);

    void addCardToCardSet(int cardSetIndex);

    void addCardToCardSet(int cardSetIndex, Card card);

    void removeCardFromCardSet(int cardSetIndex, int cardIndex);

    void editCardFromCardSet(int cardSetIndex, int cardIndex);

    void startLearningSession(int cardSetIndex);

    void repeatUnseenAndUnsolvedCards(int cardSetIndex);

}
