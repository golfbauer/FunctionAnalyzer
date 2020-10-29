package de.hhn.it.pp.components.learningCards;

import de.hhn.it.pp.components.exceptions.CardNotFoundException;
import de.hhn.it.pp.components.exceptions.CardsetNotFoundException;

public class DemoLearningCardsUsage {

    public static void main(String[] args) throws CardsetNotFoundException, CardNotFoundException {

        LearningCardsService service = new LearningCardsService() {
            @Override
            public void createCardSet(String cardsetTitle) {

            }

            @Override
            public void removeCardSet(int cardsetIndex) throws CardsetNotFoundException {

            }

            @Override
            public void addCardToCardSet(int cardsetIndex, String cardHeadline, String cardText) throws CardsetNotFoundException, CardNotFoundException {

            }

            @Override
            public void addCardToCardSet(int cardSetIndex, String cardText) throws CardsetNotFoundException {

            }

            @Override
            public void removeCardFromCardSet(int cardSetIndex, int cardIndex) throws CardsetNotFoundException, CardNotFoundException {

            }

            @Override
            public void editCardFromCardSet(int cardSetIndex, int cardIndex, String newCardText) throws CardsetNotFoundException, CardNotFoundException {

            }

            @Override
            public void startLearningSession(int cardSetIndex) throws CardsetNotFoundException {

            }

            @Override
            public void repeatUnseenAndUnsolvedCards(int cardSetIndex) throws CardsetNotFoundException {

            }
        };

        // create a cardset, cardset takes automatically the index 0
        service.createCardSet("Pupulations");
        // create a card, add into the list, which belongs to cardset "Populations". Card take the index 0
        service.addCardToCardSet(0, "The population of Berlin is 3,562,000");

        // create 2. cardset, cardset takes automatically the index 1
        service.createCardSet("Capitals");
        // create cards and add them into the list of the cardset "Capitals"
        service.addCardToCardSet(1, "Berlin is the capital of Germany");
        service.addCardToCardSet(1, "Paris is the capital of France");
        service.addCardToCardSet(1, "London is the capital of United Kingdom");
        service.addCardToCardSet(1, "Beijing is the capital of China");
        // this card is wrong
        service.addCardToCardSet(1, "Heilbronn is the capital of Russia");
        // last added card is edited
        service.editCardFromCardSet(1, 4, "Moscow");

        //  this card shouldn't belong to cardset "Populations"
        service.addCardToCardSet(1, "The population of Heilbronn is 120,000");
        // the card is deleted
        service.removeCardFromCardSet(1, 5);

        // start a learning session with all cards within the cardset "Populations"
        service.startLearningSession(1);
        // repeat the cards, which are unseen or unsolved, within the cardset "Population"
        service.repeatUnseenAndUnsolvedCards(1);

        // cardset "Populatios" is deleted
        service.removeCardSet(1);


    }
}
