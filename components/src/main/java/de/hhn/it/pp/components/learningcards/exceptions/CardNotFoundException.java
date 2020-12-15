package de.hhn.it.pp.components.learningcards.exceptions;

public class CardNotFoundException extends Exception {

  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(CardNotFoundException.class);



  public CardNotFoundException(String message) {
    super(message);
  }
}
