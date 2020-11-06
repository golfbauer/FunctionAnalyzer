package de.hhn.it.pp.components.learningCards;

public class LearningCards implements ILearningCards {

    LearningCardManager lerM;
    SessionManager sesM;

    public LearningCards() {
        lerM = new LearningCardManager();
        sesM = new SessionManager();
    }

    @Override
    // Method to create cardset
    public void createCardSet(String title) {
        lerM.createCardSet(title);
    }

    @Override
    // Method to remove cardset
    public void removeCardSet(int cardSetIndex) {
        lerM.removeCardset(cardSetIndex);
    }

    @Override
    public void editCardSetTitle(int cardSetIndex) {

    }



    @Override
    // Method to add a new card to cardset
    public void addCardToCardSet(int cardSetIndex, String headline, String textQ, String textA) {
        lerM.getCardset(cardSetIndex).addCardtoSet(new Card(headline, textQ, textA));
    }

    @Override
    // Method to add a card to cardset
    public void addCardToCardSet(int cardSetIndex, Card card) {
        lerM.getCardset(cardSetIndex).addCardtoSet(card);
    }

    @Override
    // Method to remove a Card to cardset
    public void removeCardFromCardSet(int cardSetIndex, int cardIndex) {
        lerM.getCardset(cardSetIndex).removeCardfromSet(cardIndex);
    }

    @Override
    // Method to edit the Questiontext of the card
    public void editCardQuestionTextFromCardSet(int cardSetIndex, int cardIndex, String textQ) {
        lerM.getCardset(cardSetIndex).getCardfromSet(cardIndex).setTextQ(textQ);
    }
    
    @Override
    // Method to edit the Questiontext of the card
    public void  editCardAnswerTextFromCardSet(int cardSetIndex, int cardIndex, String textA) {
        lerM.getCardset(cardSetIndex).getCardfromSet(cardIndex).setTextA(textA);
    }

    @Override
    // Method to start learning session
    public void startLearningSession(int cardSetIndex) {
        sesM.startLearningSession(lerM.getCardset(cardSetIndex));

    }

    @Override
    // Method to repeat unsolved and unseen Cards
    public void repeatUnseenAndUnsolvedCards(int cardSetIndex) {
        sesM.repeatUnseenAndUnsolvedCards(lerM.getCardset(cardSetIndex));
    }


}
