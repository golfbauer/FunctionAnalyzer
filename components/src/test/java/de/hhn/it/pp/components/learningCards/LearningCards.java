package de.hhn.it.pp.components.learningCards;

public class LearningCards implements ILearningCards{
	
	LearningCardManager lerM;
	SessionManager sesM;

	public LearningCards() {
		lerM = new LearningCardManager();
		sesM = new SessionManager();
	}

	// Method to create cardset
	public void createCardSet() {
		lerM.createCardSet();
	}

	// Method to remove cardset
	public void removeCardSet(int cardSetIndex) {
		lerM.removeCardset(cardSetIndex);
	}

	// Method to edit  title of the cardset
	public void editCardSetTitle(int cardSetIndex) {
		lerM.getCardset(cardSetIndex).setTitle();
	}
	
	// Method to add card to cardset
	public void addCardToCardSet(int cardSetIndex) {
		lerM.getCardset(cardSetIndex).addCardtoSet(new Card());
	}

	// Method to add a card to cardset
	public void addCardToCardSet(int cardSetIndex, Card card) {
		lerM.getCardset(cardSetIndex).addCardtoSet(card);
	}

	// Method to add a Card to cardset
	public void removeCardFromCardSet(int cardSetIndex, int cardIndex) {
		lerM.getCardset(cardSetIndex).removeCardfromSet(cardIndex);
	}

	// Method to set the text of the card
	public void editCardFromCardSet(int cardSetIndex, int cardIndex) {
		lerM.getCardset(cardSetIndex).getCardfromSet(cardIndex).setText();
	}

	// Method to start learning session
	public void startLearningSession(int cardSetIndex) {
		sesM.startLearningSession(lerM.getCardset(cardSetIndex));

	}

	// Method to repeat unsolved and unseen Cards
	public void repeatUnseenAndUnsolvedCards(int cardSetIndex) {
		sesM.repeatUnseenAndUnsolvedCards(lerM.getCardset(cardSetIndex));
	}

	public static void main(String[] args) {

	}

}
