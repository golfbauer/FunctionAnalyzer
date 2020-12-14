package de.hhn.it.pp.components.learningCards;

import de.hhn.it.pp.components.example.coffeemakerservice.provider.WnckCoffeeMakerService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SessionManager {

  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(SessionManager.class);

  LearningProgress progress = new LearningProgress();
  Cardset cardSet = null;
  ArrayList<Card> cards = null;
  boolean isRunning = false;

  int cardIndex = 0;

  /**
   * starts a learning session according to given card status.
   * @param cardSet cardset object where cards are stored
   * @param status the status of the cards, which will be showed
   */
  public void startLearningSession(Cardset cardSet, Status[] status) {
    if(isRunning)
      return;
    isRunning = true;
    this.cardSet = cardSet;
    cards = new ArrayList<Card>();
    for (int cardId : cardSet.getCardIds()) {
      Card card = cardSet.getCardfromSet(cardId);
      if (Arrays.asList(status).contains(card.getStatus())) {
        cards.add(card);
      }
    }
    cardIndex = 0;


  }

  public Card getCard(){
    if(isRunning)
      return cards.get(cardIndex);
    return null;
  }

  public Card getNextCard(){
    if(isRunning && cards.size() > cardIndex + 1)
      return cards.get(++cardIndex);
    return null;
  }

  public Card getPreviousCard(){
    if(isRunning && cardIndex > 0)
      return cards.get(--cardIndex);
    return null;
  }

  public void answerRight(){
    if(isRunning){
      cards.get(cardIndex).setStatusToSolved();
    }
  }

  public void answerWrong(){
    if(isRunning){
      cards.get(cardIndex).setStatusToUnSolved();
    }
  }



  /**
   * stops the learning session, if the user types 'q'.
   */
  public int[] stopLearningSession() {
    if(!isRunning){
      return null;
    }
    int solved = 0;
    int unsolved = 0;
    int unseen = 0;

    for(Card card : cards){
      if(card.getStatus() == Status.SOLVED){
        solved++;
      }
      else if (card.getStatus() == Status.SOLVED){
        unsolved++;
      }
      else{
        unseen++;
      }
    }

    isRunning = false;
    return new int[]{solved, unsolved, unseen};
  }
}