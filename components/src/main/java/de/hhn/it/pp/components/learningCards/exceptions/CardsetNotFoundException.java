package de.hhn.it.pp.components.learningCards.exceptions;

import de.hhn.it.pp.components.example.coffeemakerservice.provider.WnckCoffeeMakerService;

public class CardsetNotFoundException extends Exception {

  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(CardsetNotFoundException.class);



  public CardsetNotFoundException(String message) {
    super(message);
  }
}
