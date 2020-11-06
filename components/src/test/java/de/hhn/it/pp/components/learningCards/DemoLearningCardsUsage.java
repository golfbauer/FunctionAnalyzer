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
            public void addCardToCardSet(int cardsetIndex, String cardHeadline, String cardTextQ, String cardTextA) throws CardsetNotFoundException, CardNotFoundException {

            }

            @Override
            public void addCardToCardSet(int cardSetIndex, String cardTextQ,String cardTextA) throws CardsetNotFoundException {

            }

            @Override
            public void removeCardFromCardSet(int cardSetIndex, int cardIndex) throws CardsetNotFoundException, CardNotFoundException {

            }

            @Override
            public void editCardQuestionTextFromCardSet(int cardSetIndex, int cardIndex, String newCardTextQ) throws CardsetNotFoundException, CardNotFoundException {

            }
            
            @Override
            public void editCardAnswerTextFromCardSet(int cardSetIndex, int cardIndex, String newCardTextA) throws CardsetNotFoundException, CardNotFoundException {

            }
            @Override
            public void startLearningSession(int cardSetIndex) throws CardsetNotFoundException {

            }

            @Override
            public void repeatUnseenAndUnsolvedCards(int cardSetIndex) throws CardsetNotFoundException {

            }
        };

        // create a cardset, cardset takes automatically the index 0
        service.createCardSet("Populations");
        // create a card, add into the list, which belongs to cardset "Populations". Card take the index 0
        service.addCardToCardSet(0, "Whats the population of Berlin?", "The population of Berlin is 3,562,000");

        // create 2. cardset, cardset takes automatically the index 1
        service.createCardSet("Capitals");
        // create cards and add them into the list of the cardset "Capitals"
        service.addCardToCardSet(1,"Whats the Capital of Germany?",  "Berlin is the capital of Germany");
        service.addCardToCardSet(1,"Whats the Capital of France?", "Paris is the capital of France");
        service.addCardToCardSet(1,"Whats the Capital of United Kingdom?", "London is the capital of United Kingdom");
        service.addCardToCardSet(1,"Whats the Capital of China?", "Beijing is the capital of China");
        // this card is wrong
        service.addCardToCardSet(1,"Whats the Capital of Russia?" , "Heilbronn is the capital of Russia");
        // last added card is edited
        service.editCardAnswerTextFromCardSet(1, 4, "Moscow is the capital of Russia");

        //  this card shouldn't belong to cardset "Populations"
        service.addCardToCardSet(1,"Whats the population of Heilbronn?" , "The population of Heilbronn is 120,000");
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
