package de.hhn.it.pp.components.learningCards;

public interface ILearningCards {

    void createCardSet(String title);

    void removeCardSet(int cardSetIndex);

    void editCardSetTitle(int cardSetIndex);

    void addCardToCardSet(int cardSetIndex, String headline, String textQ, String textA);

    void addCardToCardSet(int cardSetIndex, Card card);

    void removeCardFromCardSet(int cardSetIndex, int cardIndex);

    void editCardQuestionTextFromCardSet(int cardSetIndex, int cardIndex, String textQ);
    
    void editCardAnswerTextFromCardSet(int cardSetIndex, int cardIndex, String textA);

    void startLearningSession(int cardSetIndex);

    void repeatUnseenAndUnsolvedCards(int cardSetIndex);

}
