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


        service.createCardSet("Pupulations");
        service.addCardToCardSet(0,"The population of Berlin is 3,562,000");

        service.createCardSet("Capitals");
        service.addCardToCardSet(1,"Berlin is the capital of Germany");
        service.addCardToCardSet(1,"Paris is the capital of France");
        service.addCardToCardSet(1,"London is the capital of United Kingdom");
        service.addCardToCardSet(1,"Beijing is the capital of China");

        service.addCardToCardSet(1,"Heilbronn is the capital of Russia");
        service.editCardFromCardSet(1,4,"Moscow");

        service.addCardToCardSet(1,"The population of Heilbronn is 120,000");
        service.removeCardFromCardSet(1,5);

        service.startLearningSession(1);
        service.repeatUnseenAndUnsolvedCards(1);

        service.removeCardSet(1);





    }
}
