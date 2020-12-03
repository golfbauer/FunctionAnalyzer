package de.hhn.it.pp.javafx.learningCards.controllers;

import de.hhn.it.pp.components.learningCards.Card;
import de.hhn.it.pp.components.learningCards.Cardset;
import de.hhn.it.pp.components.learningCards.LearningCardsService;
import de.hhn.it.pp.components.learningCards.provider.MYLearningCardsService;
import de.hhn.it.pp.javafx.learningCards.utilities.ButtonUtilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

import java.util.List;

public class LearningCardsServiceController {

    List<Card> cards;
    ObservableList<Button> observableCards;
    List<Cardset> cardsets;
    ObservableList<Button> observableCardsets;
    private LearningCardsService learningCardsService;


    public LearningCardsServiceController(){
        MYLearningCardsService myLearningCardsService = new MYLearningCardsService();
        learningCardsService = myLearningCardsService;


        Cardset cardset1 = new Cardset("Empty Cardset");

        Cardset cardset2 = new Cardset("Populations");
        Card card1 = new Card("Berlin", "Whats the population of Berlin?", "The population of Berlin is 3,562,000");
        Card card2 = new Card("Heilbronn", "Whats the population of Heilbronn?", "The population of Heilbronn is 120,000");
        cardset2.addCardtoSet(card1);
        cardset2.addCardtoSet(card2);

        Cardset cardset3 = new Cardset("Capitals");
        Card card3 = new Card("Germany", "Whats the Capital of Germany?", "Berlin is the capital of Germany");
        Card card4 = new Card("France", "Whats the Capital of France?", "Paris is the capital of France");
        Card card5 = new Card("United Kingdom", "Whats the Capital of United Kingdom?", "London is the capital of United Kingdom");
        Card card6 = new Card("China", "Whats the Capital of China?", "Beijing is the capital of China");
        Card card7 = new Card("Russia", "Whats the Capital of Russia?", "Moscow is the capital of Russia");
        cardset3.addCardtoSet(card3);
        cardset3.addCardtoSet(card4);
        cardset3.addCardtoSet(card5);
        cardset3.addCardtoSet(card6);
        cardset3.addCardtoSet(card7);

        myLearningCardsService.addCardsets(cardset1, cardset2, cardset3);

        cards = myLearningCardsService.getCards();
        cardsets = myLearningCardsService.getCardsets();

        observableCards = FXCollections.observableArrayList();
        for(Card card : cards){
            String cardID = "card" + card.getId();
            Button cardButton = ButtonUtilities.createButton(card.getHeadline(), cardID);
            observableCards.add(cardButton);
        }

        observableCardsets = FXCollections.observableArrayList();
        for(Cardset cardset : cardsets){
            String cardsetID = "cardset" + cardset.getId();
            Button cardsetButton = ButtonUtilities.createButton(cardset.getTitle(), cardsetID);
            observableCardsets.add(cardsetButton);
        }

    }

    public ObservableList<Button> getObservableCards() {
        return observableCards;
    }


    public ObservableList<Button> getObservableCardsets() {
        return observableCardsets;
    }

}
