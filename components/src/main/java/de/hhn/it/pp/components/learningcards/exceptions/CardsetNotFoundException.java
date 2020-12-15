package de.hhn.it.pp.components.learningcards.exceptions;

public class CardsetNotFoundException extends Exception {

  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(CardsetNotFoundException.class);



  public CardsetNotFoundException(String message) {
    super(message);
  }
}
