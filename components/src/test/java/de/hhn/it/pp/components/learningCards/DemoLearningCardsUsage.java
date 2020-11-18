package de.hhn.it.pp.components.learningCards;

import de.hhn.it.pp.components.learningCards.exceptions.CardNotFoundException;
import de.hhn.it.pp.components.learningCards.exceptions.CardsetNotFoundException;


public class DemoLearningCardsUsage {

    public static void main(String[] args) throws CardsetNotFoundException, CardNotFoundException {

        LearningCardsService service = new LearningCardsService() {


            @Override
            public int createCardset(String cardsetTitle) {
                return 0;
            }

            @Override
            public void removeCardset(int cardsetId) throws CardsetNotFoundException {

            }

            @Override
            public int addCardToCardset(int cardsetId, String cardHeadline, String cardTextQ, String cardTextA) throws CardsetNotFoundException {
                return 0;
            }

            @Override
            public int addCardToCardset(int cardsetId, String cardTextQ, String cardTextA) throws CardsetNotFoundException {
                return 0;
            }

            @Override
            public void removeCardFromCardset(int cardId) throws CardsetNotFoundException, CardNotFoundException {

            }

            @Override
            public void editCardQuestionTextFromCardset(int cardId, String newCardTextQ) throws CardsetNotFoundException, CardNotFoundException {

            }

            @Override
            public void editCardAnswerTextFromCardset(int cardId, String newCardTextA) throws CardsetNotFoundException, CardNotFoundException {

            }

            @Override
            public void startLearningSession(int cardsetId, Status[] status) throws CardsetNotFoundException {

            }

            @Override
            public void repeatUnsolvedAndUnseenCards(int cardsetId) throws CardsetNotFoundException {

            }
        };

        // create a cardset
        int cardsetPopulationId = service.createCardset("Populations");
        // add a card
        service.addCardToCardset(cardsetPopulationId, "Whats the population of Berlin?", "The population of Berlin is 3,562,000");

        // create 2. cardset
        int cardsetCapitalsId = service.createCardset("Capitals");
        // add cards
        service.addCardToCardset(cardsetCapitalsId, "Whats the Capital of Germany?", "Berlin is the capital of Germany");
        service.addCardToCardset(cardsetCapitalsId, "Whats the Capital of France?", "Paris is the capital of France");
        service.addCardToCardset(cardsetCapitalsId, "Whats the Capital of United Kingdom?", "London is the capital of United Kingdom");
        service.addCardToCardset(cardsetCapitalsId, "Whats the Capital of China?", "Beijing is the capital of China");

        int cardId = service.addCardToCardset(cardsetCapitalsId, "Whats the Capital of Russia?", "Heilbronn is the capital of Russia");
        // last added card is edited
        service.editCardAnswerTextFromCardset(cardId, "Moscow is the capital of Russia");

        cardId = service.addCardToCardset(cardsetPopulationId, "Whats the population of Heilbronn?", "The population of Heilbronn is 120,000");
        // the card is removed
        service.removeCardFromCardset(cardId);

        // start a learning session with all cards within the cardset "Capitals"
        service.startLearningSession(cardsetCapitalsId, Status.values());
        // repeat the cards, which are unseen or unsolved, within the cardset "Capitals"
        service.repeatUnsolvedAndUnseenCards(cardsetCapitalsId);
        // cardset "Capitals" is deleted
        service.removeCardset(cardsetCapitalsId);


    }
}
