package de.hhn.it.pp.components.learningCards.provider;

import de.hhn.it.pp.components.learningCards.*;
import de.hhn.it.pp.components.learningCards.exceptions.CardNotFoundException;
import de.hhn.it.pp.components.learningCards.exceptions.CardsetNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class MYLearningCardsService implements LearningCardsService {
    private Map<Integer, Cardset> cardsets;
    private Map<Integer, Card> cards;

    public MYLearningCardsService(){
        cardsets = new HashMap<>();
        cards = new HashMap<>();
    }

    @Override
    public void addCardsets(Cardset... newCardsets) {
        for(Cardset cardset : newCardsets){
            this.cardsets.put(cardset.getId(), cardset);
            for(Card card : cardset.getCards()){
                cards.put(card.getId(), card);
            }
        }
    }

    @Override
    public int getNumberOfCardsets() {
        return cardsets.size();
    }

    @Override
    public int createCardset(String cardsetTitle) {
        Cardset cardset = new Cardset(cardsetTitle);
        cardsets.put(cardset.getId(), cardset);
        return cardset.getId();
    }

    @Override
    public void removeCardset(int cardsetId) throws CardsetNotFoundException {
        if(cardsets.remove(cardsetId) == null)
            throw new CardsetNotFoundException("there is no Cardset with ID " + cardsetId);
    }

    @Override
    public int createCard(String cardHeadline, String cardTextQ, String cardTextA) {
        Card card = new Card(cardHeadline, cardTextQ, cardTextA);
        cards.put(card.getId(), card);
        return card.getId();
    }

    @Override
    public int createCard(String cardTextQ, String cardTextA) {
        Card card = new Card("", cardTextQ, cardTextA);
        cards.put(card.getId(), card);
        return card.getId();
    }

    @Override
    public int getNumberOfCards() {
        return cards.size();
    }

    @Override
    public void addCardToCardset(int cardsetId, int cardId) throws CardsetNotFoundException, CardNotFoundException {
        if(!cards.containsKey(cardId))
            throw new CardNotFoundException("there is no Card with ID " + cardId);
        if(!cardsets.containsKey(cardsetId))
            throw new CardsetNotFoundException("there is no Cardset with ID " + cardsetId);

        Cardset cardset = cardsets.get(cardsetId);
        Card card = cards.get(cardId);
        cardset.addCardtoSet(card);
    }

    @Override
    public int addCardToCardset(int cardsetId, String cardHeadline, String cardTextQ, String cardTextA) throws CardsetNotFoundException {
        Cardset cardset = cardsets.get(cardsetId);
        Card card = new Card(cardHeadline, cardTextQ, cardTextA);
        cards.put(card.getId(), card);
        cards.put(cardset.getId(), card);
        return card.getId();
    }

    @Override
    public int addCardToCardset(int cardsetId, String cardTextQ, String cardTextA) throws CardsetNotFoundException {
        Cardset cardset = cardsets.get(cardsetId);
        if(!cardsets.containsKey(cardsetId))
            throw new CardsetNotFoundException("there is no Cardset with ID " + cardsetId);
        Card card = new Card("", cardTextQ, cardTextA);
        cards.put(card.getId(), card);
        cards.put(cardset.getId(), card);
        return card.getId();
    }

    @Override
    public void deleteCard(int cardId) throws CardNotFoundException {
        if(!cards.containsKey(cardId))
            throw new CardNotFoundException("there is no Card with ID " + cardId);
        cards.remove(cardId);
        for(Cardset cardset : cardsets.values()){
            cardset.removeCardfromSet(cardId);
        }

    }

    @Override
    public void removeCardFromCardset(int cardsetId, int cardId) throws CardsetNotFoundException, CardNotFoundException {
        if(!cards.containsKey(cardId))
            throw new CardNotFoundException("there is no Card with ID " + cardId);
        if(!cardsets.containsKey(cardsetId))
            throw new CardsetNotFoundException("there is no Cardset with ID " + cardsetId);
        cardsets.remove(cardId);
    }

    @Override
    public void editCardQuestionTextFromCardset(int cardId, String newCardTextQ) throws CardNotFoundException {
        if(!cards.containsKey(cardId))
            throw new CardNotFoundException("there is no Card with ID " + cardId);
        Card card = cards.get(cardId);
        card.editTextQ(newCardTextQ);
    }

    @Override
    public void editCardAnswerTextFromCardset(int cardId, String newCardTextA) throws CardNotFoundException {
        if(!cards.containsKey(cardId))
            throw new CardNotFoundException("there is no Card with ID " + cardId);
        Card card = cards.get(cardId);
        card.editTextQ(newCardTextA);
    }

    @Override
    public void startLearningSession(int cardsetId, Status[] status) throws CardsetNotFoundException {
        if(!cardsets.containsKey(cardsetId))
            throw new CardsetNotFoundException("there is no Cardset with ID " + cardsetId);
        Cardset cardset = cardsets.get(cardsetId);
        SessionManager sessionManager = new SessionManager();
        sessionManager.startLearningSession(cardset,status);
    }

    @Override
    public void repeatUnsolvedAndUnseenCards(int cardsetId) throws CardsetNotFoundException {
        if(!cardsets.containsKey(cardsetId))
            throw new CardsetNotFoundException("there is no Cardset with ID " + cardsetId);
        Cardset cardset = cardsets.get(cardsetId);
        SessionManager sessionManager = new SessionManager();
        sessionManager.startLearningSession(cardset,new Status[]{Status.UNSEEN, Status.UNSOLVED});
    }

    @Override
    public void stopLearningSession() {

    }
}
