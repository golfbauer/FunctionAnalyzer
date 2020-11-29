package de.hhn.it.pp.components.learningCards.exceptions;

public class CardNotFoundException extends Exception {

    public CardNotFoundException() {
    }

    public CardNotFoundException(String message) {
        super(message);
    }
}
