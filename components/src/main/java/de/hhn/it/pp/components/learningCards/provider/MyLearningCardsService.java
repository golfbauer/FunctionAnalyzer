package de.hhn.it.pp.components.learningCards.provider;

import de.hhn.it.pp.components.learningCards.Card;
import de.hhn.it.pp.components.learningCards.Cardset;
import de.hhn.it.pp.components.learningCards.LearningCardManager;
import de.hhn.it.pp.components.learningCards.LearningCardsService;
import de.hhn.it.pp.components.learningCards.SessionManager;
import de.hhn.it.pp.components.learningCards.Status;

import de.hhn.it.pp.components.learningCards.exceptions.CardNotFoundException;
import de.hhn.it.pp.components.learningCards.exceptions.CardsetNotFoundException;

import java.util.ArrayList;
import java.util.List;


public class MyLearningCardsService implements LearningCardsService {
  private LearningCardManager learningCardManager;

  public MyLearningCardsService() {
    learningCardManager = new LearningCardManager();
  }

  @Override
  public void addCardsets(Cardset... newCardsets) {
    for (Cardset cardset : newCardsets) {
      learningCardManager.addCardset(cardset);
      for (Card card : cardset.getCards()) {
        learningCardManager.addCard(card);
      }
    }
  }


  @Override
  public int getNumberOfCardsets() {
    return learningCardManager.getCardsetIds().size();
  }

  @Override
  public int createCardset(String cardsetTitle) {
    return learningCardManager.createCardSet(cardsetTitle);
  }

  @Override
  public void removeCardset(int cardsetId) throws CardsetNotFoundException {
    if (learningCardManager.removeCardset(cardsetId) == null) {
      throw new CardsetNotFoundException("there is no Cardset with ID " + cardsetId);
    }
  }

  @Override
  public int createCard(String cardHeadline, String cardTextQ, String cardTextA) {
    Card card = new Card(cardHeadline, cardTextQ, cardTextA);
    learningCardManager.addCard(card);
    return card.getId();
  }

  @Override
  public int createCard(String cardTextQ, String cardTextA) {
    Card card = new Card("", cardTextQ, cardTextA);
    learningCardManager.addCard(card);
    return card.getId();
  }

  @Override
  public int getNumberOfCards() {
    return learningCardManager.getAllCardsIds().size();
  }

  @Override
  public void addCardToCardset(int cardsetId, int cardId)
        throws CardsetNotFoundException, CardNotFoundException {
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
    if (!learningCardManager.getCardsetIds().contains(cardsetId)) {
      throw new CardsetNotFoundException("there is no Cardset with ID " + cardsetId);
    }
    return learningCardManager.newCard(cardsetId, cardHeadline, cardTextA, cardTextQ);
  }

  @Override
  public int addCardToCardset(int cardsetId, String cardTextQ, String cardTextA)
        throws CardsetNotFoundException {
    if (!learningCardManager.getCardsetIds().contains(cardsetId)) {
      throw new CardsetNotFoundException("there is no Cardset with ID " + cardsetId);
    }
    Card card = new Card("", cardTextQ, cardTextA);
    learningCardManager.addCard(card, cardsetId);
    return card.getId();
  }

  @Override
  public void deleteCard(int cardId) throws CardNotFoundException {
    if (!learningCardManager.getAllCardsIds().contains(cardId)) {
      throw new CardNotFoundException("there is no Card with ID " + cardId);
    }
    learningCardManager.removeCardFromCol(cardId);

  }

  @Override
  public void removeCardFromCardset(int cardsetId, int cardId)
        throws CardsetNotFoundException, CardNotFoundException {
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
    if (!learningCardManager.getAllCardsIds().contains(cardId)) {
      throw new CardNotFoundException("there is no Card with ID " + cardId);
    }
    learningCardManager.getCardFromCol(cardId).editTextQ(newCardTextQ);
  }

  @Override
  public void editCardAnswerTextFromCardset(int cardId, String newCardTextA)
        throws CardNotFoundException {
    if (!learningCardManager.getAllCardsIds().contains(cardId)) {
      throw new CardNotFoundException("there is no Card with ID " + cardId);
    }
    learningCardManager.getCardFromCol(cardId).editTextA(newCardTextA);
  }

  @Override
  public void startLearningSession(int cardsetId, Status[] status) throws CardsetNotFoundException {
    if (!learningCardManager.getCardsetIds().contains(cardsetId)) {
      throw new CardsetNotFoundException("there is no Cardset with ID " + cardsetId);
    }
    Cardset cardset = learningCardManager.getCardset(cardsetId);
    SessionManager sessionManager = new SessionManager();
    sessionManager.startLearningSession(cardset, status);
  }

  @Override
  public void repeatUnsolvedAndUnseenCards(int cardsetId) throws CardsetNotFoundException {
    if (!learningCardManager.getCardsetIds().contains(cardsetId)) {
      throw new CardsetNotFoundException("there is no Cardset with ID " + cardsetId);
    }
    Cardset cardset = learningCardManager.getCardset(cardsetId);
    SessionManager sessionManager = new SessionManager();
    sessionManager.startLearningSession(cardset, new Status[]{Status.UNSEEN, Status.UNSOLVED});
  }

  @Override
  public void stopLearningSession() {

  }

  @Override
  public List<Integer> getCardsetIds() {
    return learningCardManager.getCardsetIds();
  }

  @Override
  public List<Integer> getCardsIds() {
    return learningCardManager.getAllCardsIds();
  }

  @Override
  public List<Cardset> getCardsets() {
    ArrayList<Cardset> cardsets = new ArrayList<>();
    for (int cardsetId : learningCardManager.getCardsetIds()) {
      cardsets.add(learningCardManager.getCardset(cardsetId));
    }
    return cardsets;
  }

  @Override
  public List<Card> getCards() {
    ArrayList<Card> cards = new ArrayList<>();
    for (int cardId : learningCardManager.getAllCardsIds()) {
      cards.add(learningCardManager.getCardFromCol(cardId));
    }
    return cards;
  }

  @Override
  public Cardset getCardset(int i) {
    return learningCardManager.getCardset(i);
  }

  @Override
  public Card getCardFromCol(int i) {
    return learningCardManager.getCardFromCol(i);
  }


}
