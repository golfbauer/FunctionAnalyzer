package de.hhn.it.pp.components.learningCards.exceptions;

import de.hhn.it.pp.components.example.coffeemakerservice.provider.WnckCoffeeMakerService;

public class CardNotFoundException extends Exception {

  private static final org.slf4j.Logger logger =
       org.slf4j.LoggerFactory.getLogger(CardNotFoundException.class);

  public CardNotFoundException() {
  }

  public CardNotFoundException(String message) {
    super(message);
  }
}
