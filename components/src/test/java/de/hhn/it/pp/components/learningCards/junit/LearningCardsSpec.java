package de.hhn.it.pp.components.learningCards.junit;

import de.hhn.it.pp.components.learningCards.Card;
import de.hhn.it.pp.components.learningCards.Cardset;
import de.hhn.it.pp.components.learningCards.LearningCardsService;
import de.hhn.it.pp.components.learningCards.exceptions.CardNotFoundException;
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
    static void showNumberOfCardsOfCardsets(Map<String, Cardset> cardsets) {
        for (Cardset cardset : cardsets.values()) {
            System.out.println("Cartset '" + cardset.getTitle() + "' has " + cardset.getNumberOfCard() + " cards");
        }
    }

    @BeforeEach
    void setup(Map<String, Cardset> cardsets) {
        MYLearningCardsService myLearningCardsService = new MYLearningCardsService();
        learningCardsService = myLearningCardsService;

        emptyCardset = cardsets.get("Empty Cardset");
        // 0
        numberOfCardsOfEmptyCardset = emptyCardset.getNumberOfCard();

        populationsCardset = cardsets.get("Populations");
        // 2
        numberOfCardsOfPopulations = populationsCardset.getNumberOfCard();

        capitalsCardset = cardsets.get("Capitals");
        // 5
        numberOfCardsOfCapitals = capitalsCardset.getNumberOfCard();
    }

    @Test
    @DisplayName("There is no cardset when no cardsets is added to it")
    void thereIsNoCardsetsWhenNoCardsetAdded() {
        assertEquals(0, learningCardsService.getNumberOfCardsets(),
                () -> "it should have no cardset");
    }

    @Test
    @DisplayName("There are three cardsets when three cardsets are added to it")
    void thereAreThreeCardsetsWhenThreeCardsetsAdded() {
        learningCardsService.addCardsets(emptyCardset, populationsCardset, capitalsCardset);
        assertEquals(3, learningCardsService.getNumberOfCardsets(),
                () -> "it should have 3 cardsets");
    }

    @Test
    @DisplayName("There is one cardset when one cardset is created")
    void thereIsOneCardWhenOneCardsetCreated() {
        learningCardsService.createCardset("Cardset");
        assertEquals(1, learningCardsService.getNumberOfCardsets());
    }

    @Test
    @DisplayName("There is no card when no card is added to it")
    void thereIsNoCardWhenNoCardAdded() {
        assertEquals(0, learningCardsService.getNumberOfCards(),
                () -> "it should have no card");
    }

    @Test
    @DisplayName("There are two cards when two cards are created")
    void thereAreTwoCardsWhenTwoCardsCreated() {
        learningCardsService.createCard("", "");
        learningCardsService.createCard("", "");
        assertEquals(2, learningCardsService.getNumberOfCards(),
                () -> "it should have 2 cards");
    }

    @Test
    @DisplayName("There is one Cardset when one of two cardset is removed")
    void thereIsOneCardsetWhenOneOfTwoCardsetsRemoved() throws CardsetNotFoundException {
        learningCardsService.addCardsets(emptyCardset, populationsCardset);
        assertEquals(2, learningCardsService.getNumberOfCardsets());
        learningCardsService.removeCardset(emptyCardset.getId());
        assertEquals(1, learningCardsService.getNumberOfCardsets());
    }

    @Test
    @DisplayName("Cards of the added cardset are added automatically")
    void cardsOfCardsetAreAddedWhenCardsetWasAdded() {
        learningCardsService.addCardsets(populationsCardset);
        assertEquals(numberOfCardsOfPopulations, learningCardsService.getNumberOfCards());
    }

    @DisplayName("Add created card to cardset by its ID")
    void addCardToCardsetWithID() throws CardNotFoundException, CardsetNotFoundException {
        int cardsetId = emptyCardset.getId();
        learningCardsService.addCardsets(emptyCardset);
        int cardId = learningCardsService.createCard("Headline", "Question", "Answer");
        learningCardsService.addCardToCardset(cardsetId, cardId);
        assertEquals(1 + numberOfCardsOfEmptyCardset, learningCardsService.getCardset(cardsetId).getNumberOfCard());
    }

    @Test
    @DisplayName("Create cards with infos and put it directly to cardset")
    void addTwoCardsToCardsetWithCardInfos() throws CardsetNotFoundException {
        int cardsetId = populationsCardset.getId();
        learningCardsService.addCardsets(populationsCardset);
        learningCardsService.addCardToCardset(cardsetId, "Card 1", "Question 1", "Answer 1");
        learningCardsService.addCardToCardset(cardsetId, "Card 2", "Question 2", "Answer 2");
        assertEquals(2 + numberOfCardsOfPopulations, learningCardsService.getCardset(cardsetId).getNumberOfCard());
    }

    @Test
    @DisplayName("Throw exception when the card was adding to non existing cardset")
    void throwExceptionIfCardsetNotExistWhileCardWasAddingToCardset() throws CardsetNotFoundException {
        int cardsetIDPlusOne = capitalsCardset.getId() + 1;
        learningCardsService.addCardsets(capitalsCardset);
        try {
            learningCardsService.addCardToCardset(cardsetIDPlusOne
                    , "Headline", "Question", "Answer");
            fail("cardset ID doesn't exist, last intruction should throw an exception");
        } catch (CardsetNotFoundException e) {

        }
    }

    @Test
    @DisplayName("Deleted dards don't exist in map")
    void deletedCardNotExistInMap() throws CardNotFoundException {
        int cardID = learningCardsService.createCard("Headline", "Question", "Answer");
        assertTrue(learningCardsService.getCardsIds().contains(cardID));
        learningCardsService.deleteCard(cardID);
        assertFalse(learningCardsService.getCardsIds().contains(cardID));
    }

    @Test
    @DisplayName("Deleted cards don't exist in cardset")
    void deletedCardNotExistInCardset() throws CardsetNotFoundException, CardNotFoundException {
        int cardsetID = emptyCardset.getId();
        learningCardsService.addCardsets(emptyCardset);
        int cardID = learningCardsService.addCardToCardset(cardsetID, "Headline", "Question", "Answer");
        assertTrue(learningCardsService.getCardset(cardsetID).getCardIds().contains(cardID));
        learningCardsService.deleteCard(cardID);
        assertFalse(learningCardsService.getCardset(cardsetID).getCardIds().contains(cardID));
    }

    @Test
    @DisplayName("Throw exception if the deleting card doesn't exist")
    void throwExceptionWhileDeletingCardIDNotExist() {
        try {
            learningCardsService.deleteCard(999);
            fail("card ID doesn't exist, last intruction should throw an exception");
        } catch (CardNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Card exist when it was removed just from the cardset")
    void cardExistWhenItRemovedFromCardset() throws CardsetNotFoundException, CardNotFoundException {
        int cardsetID = emptyCardset.getId();
        learningCardsService.addCardsets(emptyCardset);
        int cardID = learningCardsService.addCardToCardset(cardsetID, "Headline", "Wuestion", "Answer");
        learningCardsService.removeCardFromCardset(cardsetID, cardID);
        assertTrue(learningCardsService.getCardsIds().contains(cardID));
    }

    @Test
    @DisplayName("Card doesn't exist in cardset when it removed from cardset")
    void cardNotExistInCardsetWhenItRemovedFromCardset() throws CardsetNotFoundException, CardNotFoundException {
        int cardsetID = learningCardsService.createCardset("Cardset");
        int cardID = learningCardsService.addCardToCardset(cardsetID, "Headline", "Question", "Answer");
        assertTrue(learningCardsService.getCardset(cardsetID).getCardIds().contains(cardID));
        learningCardsService.removeCardFromCardset(cardsetID, cardID);
        assertFalse(learningCardsService.getCardset(cardsetID).getCardIds().contains(cardID));
    }

    @Test
    @DisplayName("Throw exception if the cardset ID cannot be found")
    void throwExceptionWhenCardsetIDNotExistWhileCardWasRemovingFromCardset() throws CardNotFoundException, CardsetNotFoundException {
        int cardsetID = learningCardsService.createCardset("Cardset");
        int cardID = learningCardsService.addCardToCardset(cardsetID, "Headline", "Question", "Answer");
        cardsetID += 1;

        try {
            learningCardsService.removeCardFromCardset(cardsetID, cardID);
            fail("cardsetID should be wrong and throw an exception");
        } catch (CardsetNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Throw exception if card doesn't exist in Cardset")
    void throwExceptionWhenCardIDNotExistWhileCardWasRemovingFromCardset() throws CardsetNotFoundException {
        int cardsetID = learningCardsService.createCardset("Cardset");
        int cardID = learningCardsService.addCardToCardset(cardsetID, "Headline", "Question", "Answer");
        cardID += 1;

        try {
            learningCardsService.removeCardFromCardset(cardsetID, cardID);
            fail("cardID should be wrong and throw an exception");
        } catch (CardNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Change question text of a card")
    void questionTextChangedWhenItWasEdited() throws CardNotFoundException {
        learningCardsService.addCardsets(populationsCardset, emptyCardset, capitalsCardset);
        int populationsCardsetID = populationsCardset.getId();
        int firstCardIDOfPopulations = learningCardsService.getCardset(populationsCardsetID).getCardIds().get(0);
        String cardQuestionText = learningCardsService.getCardFromCol(firstCardIDOfPopulations).getTextQ();
        assertEquals(cardQuestionText, learningCardsService.getCardFromCol(firstCardIDOfPopulations).getTextQ());
        String changedCardQuestionText = "(changed )" + cardQuestionText;
        learningCardsService.editCardQuestionTextFromCardset(firstCardIDOfPopulations, changedCardQuestionText);
        assertEquals(changedCardQuestionText, learningCardsService.getCardFromCol(firstCardIDOfPopulations).getTextQ());
    }

    @Test
    @DisplayName("Change answer text of a card")
    void answerTextChangedWhenItWasEdited() throws CardNotFoundException {
        int cardID = learningCardsService.createCard("Headline", "Question", "Antwort");
        String cardAnswerText = learningCardsService.getCardFromCol(cardID).getTextA();
        assertEquals(cardAnswerText, learningCardsService.getCardFromCol(cardID).getTextA());
        String editedCardAnswerText = ("edited") + learningCardsService.getCardFromCol(cardID).getTextA();
        learningCardsService.editCardAnswerTextFromCardset(cardID, editedCardAnswerText);
        assertEquals(editedCardAnswerText, learningCardsService.getCardFromCol(cardID).getTextA());
    }

    @Test
    void editCardQuestionTextThrowExceptionWhenCardIDNotExist() {
        try {
            learningCardsService.editCardQuestionTextFromCardset(1, "new Question");
            fail("Card ID should not exist in map");
        } catch (CardNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void editCardAnswerTextThrowExceptionWhenCardIDNotExist() {
        try {
            learningCardsService.editCardAnswerTextFromCardset(1, "new Answer");
            fail("Card ID should not exist in map");
        } catch (CardNotFoundException e) {
            e.printStackTrace();
        }
    }

}
