package de.hhn.it.pp.components.learningCards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SessionManager {

    LearningProgress progress = new LearningProgress();
    Cardset cardSet = null;

    int cardIndex = 0;
    Scanner scanner = new Scanner(System.in);

    // Method to start learning session
    public void startLearningSession(Cardset cardSet, Status[] status) {
        this.cardSet = cardSet;
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int cardId : cardSet.getCardIds()) {
            Card card = cardSet.getCardfromSet(cardId);
            if (Arrays.asList(status).contains(card.getStatus())) {
                cards.add(card);
            }
        }
        while (cards.size() > cardIndex) {
            askQuestion(cards.get(cardIndex));
            cardIndex++;
            if (stopLearningSession()) {
                break;
            }
        }
        cardIndex = 0;
        System.out.println("Result: " + progress.toString());
        System.out.println("******************************");
        progress.reset();

    }


    // Method to show and mark the question
    public void askQuestion(Card card) {
        System.out.println(card.getTextQ());
        System.out.println("If it is solved correctly, enter 1.\nOtherwise enter something else:");
        String input = scanner.nextLine();
        if (input.equals("1")) {
            card.setStatus(Status.SOLVED);
            progress.updateRight();
        } else {
            card.setStatus(Status.UNSOLVED);
            progress.updateWrong();
        }
    }

    // Method to stop learning session
    public boolean stopLearningSession() {
        System.out.println("If you want to quit, enter 'q'. If you want to see next Question, enter something else:");
        String input = scanner.nextLine();
        if (input.equals("q")) {
            this.cardSet = null;
            cardIndex = 0;
            return true;
        }
        return false;
    }
}