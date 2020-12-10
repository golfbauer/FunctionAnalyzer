package de.hhn.it.pp.components.learningCards.provider;

import de.hhn.it.pp.components.example.coffeemakerservice.provider.WnckCoffeeMakerService;
import de.hhn.it.pp.components.learningCards.Card;
import de.hhn.it.pp.components.learningCards.Cardset;
import de.hhn.it.pp.components.learningCards.LearningCardManager;
import de.hhn.it.pp.components.learningCards.LearningCardsService;
import de.hhn.it.pp.components.learningCards.SessionManager;
import de.hhn.it.pp.components.learningCards.Status;

import de.hhn.it.pp.components.learningCards.exceptions.CardNotFoundException;
import de.hhn.it.pp.components.learningCards.exceptions.CardsetNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class MyLearningCardsService implements LearningCardsService {

  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(MyLearningCardsService.class);

  private LearningCardManager learningCardManager;

  public MyLearningCardsService() {
    learningCardManager = new LearningCardManager();
  }

  @Override
  public void addCardsets(Cardset... newCardsets) {
    String cardsettitles = Arrays.stream(newCardsets).map(e -> e.getTitle() + " ").collect(Collectors.joining());
    logger.info("addCardsets, newCardsets = {}",cardsettitles);
    for (Cardset cardset : newCardsets) {
      learningCardManager.addCardset(cardset);
      for (Card card : cardset.getCards()) {
        learningCardManager.addCard(card);
      }
    }
  }


  @Override
  public int getNumberOfCardsets() {
    logger.info("getNumberOfCardsets: no params");
    return learningCardManager.getCardsetIds().size();
  }

  @Override
  public int createCardset(String cardsetTitle) {
    logger.info("createCardset, cardsetTitle = {}", cardsetTitle);
    return learningCardManager.createCardSet(cardsetTitle);
  }

  @Override
  public void removeCardset(int cardsetId) throws CardsetNotFoundException {
    logger.info("removeCardset, cardsetId = {}", cardsetId);
    if (learningCardManager.removeCardset(cardsetId) == null) {
      throw new CardsetNotFoundException("there is no Cardset with ID " + cardsetId);
    }
  }

  @Override
  public int createCard(String cardHeadline, String cardTextQ, String cardTextA) {
    logger.info("createCard, cardHeadline = {}, cardTextQ = {}, cardTextA = {}", cardHeadline, cardTextQ, cardTextA);
    Card card = new Card(cardHeadline, cardTextQ, cardTextA);
    learningCardManager.addCard(card);
    return card.getId();
  }

  @Override
  public int createCard(String cardTextQ, String cardTextA) {
    logger.info("createCard, cardTextQ = {}, cardTextA = {}", cardTextQ, cardTextA);
    Card card = new Card("", cardTextQ, cardTextA);
    learningCardManager.addCard(card);
    return card.getId();
  }

  @Override
  public int getNumberOfCards() {
    logger.info("getNumberOfCards: no params");
    return learningCardManager.getAllCardsIds().size();
  }

  @Override
  public void addCardToCardset(int cardsetId, int cardId)
        throws CardsetNotFoundException, CardNotFoundException {
    logger.info("addCardToCardset, cardsetId = {}, cardId = {}", cardsetId, cardId);
    if (!learningCardManager.getAllCardsIds().contains(cardId)) {
      throw new CardNotFoundException("there is no Card with ID " + cardId);
    }
    if (!learningCardManager.getCardsetIds().contains(cardsetId)) {
      throw new CardsetNotFoundException("there is no Cardset with ID " + cardsetId);
    }
    learningCardManager.getCardset(cardsetId)
          .addCardtoSet(learningCardManager.getCardFromCol(cardId));
  }

  @Override
  public int addCardToCardset(int cardsetId, String cardHeadline, String cardTextQ,
                              String cardTextA) throws CardsetNotFoundException {
    logger.info("addCardToCardset, cardsetId = {}, cardHeadline = {}, cardTextQ = {}, cardTextA = {}"
            , cardsetId, cardHeadline, cardTextQ, cardTextA);
    if (!learningCardManager.getCardsetIds().contains(cardsetId)) {
      throw new CardsetNotFoundException("there is no Cardset with ID " + cardsetId);
    }
    return learningCardManager.newCard(cardsetId, cardHeadline, cardTextA, cardTextQ);
  }

  @Override
  public int addCardToCardset(int cardsetId, String cardTextQ, String cardTextA)
        throws CardsetNotFoundException {
    logger.info("addCardToCardset, cardsetId = {}, cardTextQ = {}, cardTextA = {}"
            , cardsetId, cardTextQ, cardTextA);
    if (!learningCardManager.getCardsetIds().contains(cardsetId)) {
      throw new CardsetNotFoundException("there is no Cardset with ID " + cardsetId);
    }
    Card card = new Card("", cardTextQ, cardTextA);
    learningCardManager.addCard(card, cardsetId);
    return card.getId();
  }

  @Override
  public void deleteCard(int cardId) throws CardNotFoundException {
    logger.info("deleteCard, cardId = {}", cardId);
    if (!learningCardManager.getAllCardsIds().contains(cardId)) {
      throw new CardNotFoundException("there is no Card with ID " + cardId);
    }
    learningCardManager.removeCardFromCol(cardId);

  }

  @Override
  public void removeCardFromCardset(int cardsetId, int cardId)
        throws CardsetNotFoundException, CardNotFoundException {
    logger.info("removeCardFromCardset, cardsetId = {}, cardId = {}", cardsetId, cardId);
    if (!learningCardManager.getAllCardsIds().contains(cardId)) {
      throw new CardNotFoundException("there is no Card with ID " + cardId);
    }
    if (!learningCardManager.getCardsetIds().contains(cardsetId)) {
      throw new CardsetNotFoundException("there is no Cardset with ID " + cardsetId);
    }
    learningCardManager.getCardset(cardsetId).removeCardfromSet(cardId);
  }

  @Override
  public void editCardQuestionTextFromCardset(int cardId, String newCardTextQ)
        throws CardNotFoundException {
    logger.info("editCardQuestionTextFromCardset, cardId = {}, newCardTextQ = {}", cardId, newCardTextQ);

    if (!learningCardManager.getAllCardsIds().contains(cardId)) {
      throw new CardNotFoundException("there is no Card with ID " + cardId);
    }
    learningCardManager.getCardFromCol(cardId).editTextQ(newCardTextQ);
  }

  @Override
  public void editCardAnswerTextFromCardset(int cardId, String newCardTextA)
        throws CardNotFoundException {
    logger.info("editCardAnswerTextFromCardset, cardId = {}, newCardTextA = {}", cardId, newCardTextA);
    if (!learningCardManager.getAllCardsIds().contains(cardId)) {
      throw new CardNotFoundException("there is no Card with ID " + cardId);
    }
    learningCardManager.getCardFromCol(cardId).editTextA(newCardTextA);
  }

  @Override
  public void startLearningSession(int cardsetId, Status[] status) throws CardsetNotFoundException {
    logger.info("startLearningSession, cardsetId = {}, status = {}", cardsetId, status);
    if (!learningCardManager.getCardsetIds().contains(cardsetId)) {
      throw new CardsetNotFoundException("there is no Cardset with ID " + cardsetId);
    }
    Cardset cardset = learningCardManager.getCardset(cardsetId);
    SessionManager sessionManager = new SessionManager();
    sessionManager.startLearningSession(cardset, status);
  }

  @Override
  public void repeatUnsolvedAndUnseenCards(int cardsetId) throws CardsetNotFoundException {
    logger.info("repeatUnsolvedAndUnseenCards, cardsetId = {}", cardsetId);
    if (!learningCardManager.getCardsetIds().contains(cardsetId)) {
      throw new CardsetNotFoundException("there is no Cardset with ID " + cardsetId);
    }
    Cardset cardset = learningCardManager.getCardset(cardsetId);
    SessionManager sessionManager = new SessionManager();
    sessionManager.startLearningSession(cardset, new Status[]{Status.UNSEEN, Status.UNSOLVED});
  }

  @Override
  public void stopLearningSession() {
    logger.info("stopLearningSession, no params");
  }

  @Override
  public List<Integer> getCardsetIds() {
    logger.info("getCardsetIds, no params");
    return learningCardManager.getCardsetIds();
  }

  @Override
  public List<Integer> getCardsIds() {
    logger.info("getCardsIds, no params");
    return learningCardManager.getAllCardsIds();
  }

  @Override
  public List<Cardset> getCardsets() {
    logger.info("getCardsets, no params");
    ArrayList<Cardset> cardsets = new ArrayList<>();
    for (int cardsetId : learningCardManager.getCardsetIds()) {
      cardsets.add(learningCardManager.getCardset(cardsetId));
    }
    return cardsets;
  }

  @Override
  public List<Card> getCards() {
    logger.info("getCards, no params");
    ArrayList<Card> cards = new ArrayList<>();
    for (int cardId : learningCardManager.getAllCardsIds()) {
      cards.add(learningCardManager.getCardFromCol(cardId));
    }
    return cards;
  }

  @Override
  public Cardset getCardset(int i) {
    logger.info("getCardset, id = {}", i);
    return learningCardManager.getCardset(i);
  }

  @Override
  public Card getCardFromCol(int i) {
    logger.info("getCardFromCol, id = {}", i);
    return learningCardManager.getCardFromCol(i);
  }


}
