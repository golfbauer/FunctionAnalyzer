package de.hhn.it.pp.components.learningCards;


import de.hhn.it.pp.components.example.coffeemakerservice.provider.WnckCoffeeMakerService;

public class Card {

  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(Card.class);

  Status status = Status.UNSEEN;

  // Questiontext String from the Card
  String textQ;
  // Answertext String from the Card
  String textA;
  // identifier for Card
  int id;
  static int idCounter = 0;
  // Card Headline
  String headline;


  /**
   * Constructor of the Card class.
   *
   * @param headline to classify the card topic
   * @param textQ    question text of the card
   * @param textA    answer to the card question
   */
  public Card(String headline, String textQ, String textA) {
    setHeadline(headline);
    setTextQ(textQ);
    setTextA(textA);
    id = ++idCounter;
  }

  /**
   * Sets the headline of the Card class.
   *
   * @param headline to classify the card topic
   */
  public void setHeadline(String headline) {

    this.headline = headline;
  }

  /**
   * Method not intended for the facade.
   */
  void addPicture() {
  }

  /**
   * Sets the Questiontext of the Card class.
   *
   * @param question question text of the card
   */
  void setTextQ(String question) {
    textQ = question;
  }

  /**
   * Sets the Answertext of the Card class.
   *
   * @param answer answer text of the card
   */
  void setTextA(String answer) {
    textA = answer;
  }

  /**
   * edits the questiontext of an existing card.
   *
   * @param changedQ the changed questiontext
   */
  public void editTextQ(String changedQ) {
    setTextQ(changedQ);
  }

  /**
   * edits the answertext of an existing card.
   *
   * @param changedA the changed answertext
   */
  public void editTextA(String changedA) {

    setTextA(changedA);
  }

  /**
   * returns the id of the card.
   *
   * @return id of card
   */
  public int getId() {
    return id;
  }

  /**
   * returns the questiontext of the card.
   *
   * @return questiontext
   */
  public String getTextQ() {
    return textQ;
  }

  /**
   * returns the answer text of the card.
   *
   * @return textA of card
   */
  public String getTextA() {
    return textA;
  }

  /**
   * returns the headline of card.
   *
   * @return headline of card
   */
  public String getHeadline() {
    return headline;
  }

  /**
   * returns the information of the card.
   *
   * @return all information put into the card class (headline, questiontext, answertext) in an
   * combined String
   */
  @SuppressWarnings("checkstyle:JavadocTagContinuationIndentation")
  String getCardinfo() {
    String info;
    info = "Headline: " + getHeadline() + "\n Question: " + getTextQ() + "\n Answer: " + getTextA();
    return info;
  }

  /**
   * returns the current status of the card.
   *
   * @return status of card
   */
  public Status getStatus() {
    return status;
  }

  /**
   * Sets status of the card to solved.
   */
  public void setStatusToSolved() {
    status = Status.SOLVED;
  }

  /**
   * Sets status of the card to unsolved.
   */
  public void setStatusToUnSolved() {
    status = Status.UNSOLVED;
  }
}


