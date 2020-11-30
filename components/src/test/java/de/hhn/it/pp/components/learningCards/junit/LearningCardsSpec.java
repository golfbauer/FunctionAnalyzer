package de.hhn.it.pp.components.learningCards.junit;

import de.hhn.it.pp.components.learningCards.Card;
import de.hhn.it.pp.components.learningCards.Cardset;
import de.hhn.it.pp.components.learningCards.LearningCardsService;
import de.hhn.it.pp.components.learningCards.exceptions.CardsetNotFoundException;
import de.hhn.it.pp.components.learningCards.provider.MYLearningCardsService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

@ExtendWith(LearningCardsParameterResolver.class)
public class LearningCardsSpec {

    LearningCardsService learningCardsService;
    Cardset emptyCardset, populationsCardset, capitalsCardset;
    int numberOfCardsOfPopulations, numberOfCardsOfCapitals, numberOfCardsOfEmptyCardset;


    @BeforeAll
    static void showNumberOfCardsOfCardsets(Map<String, Cardset> cardsets){
        for(Cardset cardset : cardsets.values()){
            System.out.println("Cartset '" + cardset.getTitle() + "' has " + cardset.getNumberOfCard() + " cards");
        }
    }

    @BeforeEach
    void setup(Map<String, Cardset> cardsets){
        MYLearningCardsService myLearningCardsService = new MYLearningCardsService();
        learningCardsService = myLearningCardsService;

        emptyCardset = cardsets.get("Empty Cardset");
        // 0
        numberOfCardsOfEmptyCardset = emptyCardset.getNumberOfCard();

        populationsCardset = cardsets.get("Populations");
        // 2
        numberOfCardsOfPopulations = populationsCardset.getNumberOfCard();

        capitalsCardset = cardsets.get("Capitals");
        // 7
        numberOfCardsOfCapitals = capitalsCardset.getNumberOfCard();
    }

    @Test
    @DisplayName("There is no cardset when no cardsets is added to it")
    void thereIsNoCardsetsWhenNoCardsetAdded(){
        assertEquals(0, learningCardsService.getNumberOfCardsets(),
                () -> "it should have no cardset");
    }

    @Test
    @DisplayName("There are three cardsets when three cardsets are added to it")
    void thereAreThreeCardsetsWhenThreeCardsetsAdded(){
        learningCardsService.addCardsets(emptyCardset, populationsCardset, capitalsCardset);
        assertEquals(3, learningCardsService.getNumberOfCardsets(),
               () -> "it should have 3 cardsets");
    }

    @Test
    @DisplayName("There is one cardset when one cardset is created")
    void thereIsOneCardWhenOneCardsetCreater(){
        learningCardsService.createCardset("Cardset");
        assertEquals(1, learningCardsService.getNumberOfCardsets());
    }

    @Test
    @DisplayName("There is no card when no card is added to it")
    void thereIsNoCardWhenNoCardAdded(){
        assertEquals(0, learningCardsService.getNumberOfCards(),
                () -> "it should have no card");
    }

    @Test
    @DisplayName("There are two cards when two cards are created")
    void thereAreTwoCardsWhenTwoCardsCreate(){
        learningCardsService.createCard("","");
        learningCardsService.createCard("","");
        assertEquals(2, learningCardsService.getNumberOfCards(),
                () -> "it should have 2 cards");
    }

    @Test
    @DisplayName("There is one Cardset when one of two cardset is removed")
    void thereIsOneCardsetWhenOneOfTwoCardsetsRemoved() throws CardsetNotFoundException {
        learningCardsService.addCardsets(emptyCardset, populationsCardset);
        assertEquals(2, learningCardsService.getNumberOfCardsets());
        learningCardsService.removeCardset(emptyCardset.getId());
        assertEquals(1,learningCardsService.getNumberOfCardsets());
    }

    @Test
    @DisplayName("Cards of the added cardset are added automatically")
    void cardsOfCardsetAreAddedWhenCardsetWasAdded(){
        learningCardsService.addCardsets(populationsCardset);
        assertEquals(numberOfCardsOfPopulations, learningCardsService.getNumberOfCards());
    }




}
