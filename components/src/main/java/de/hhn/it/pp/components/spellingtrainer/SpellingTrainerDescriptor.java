package de.hhn.it.pp.components.spellingtrainer;

import de.hhn.it.pp.components.spellingtrainer.Provider.LearningSet;
import de.hhn.it.pp.components.spellingtrainer.exceptions.NoWordException;
import java.util.ArrayList;

public class SpellingTrainerDescriptor {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SpellingTrainerDescriptor.class);

  private boolean isLearning;
  private int currentWordIndex;
  private int counterWrongWords;
  private int counterRightWords;
  private int counterRemainingWords;
  private LearningSet activeLearningSet;
  private ArrayList<LearningSet> learningSets;

  /**
   * Constructor of the SpellingTrainerDescriptor object, which initializes the instance variables.
   */
  public SpellingTrainerDescriptor() {
    this.isLearning = false;
    this.currentWordIndex = 0;
    this.counterRightWords = 0;
    this.counterWrongWords = 0;
    this.learningSets = new ArrayList<>();
    logger.info("Constructor from class SpellingTrainerDescriptor successfully run.");
  }

  /**
   * Returns the counter value of the addressed counter.
   *
   * @param counterName name of the counter (wrong, right, remaining)
   * @return counter value
   * @throws NoWordException is thrown when no counter with the entered name is found
   */
  public int getCounter(String counterName) throws NoWordException {

    switch (counterName.toLowerCase()) {
      case "wrong":
        return counterWrongWords;

      case "right":
        return counterRightWords;

      case "remaining":
        return counterRemainingWords;

      default:
        throw new NoWordException();
    }
  }

  /**
   * Updates the counter value by adding the entered value.
   *
   * @param counterName counterName name of the counter (wrong, right, remaining)
   * @param value       value that the counter ist updated with
   * @throws NoWordException is thrown when no counter with the entered name is found
   */
  public void updateCounter(String counterName, int value) throws NoWordException {

    switch (counterName.toLowerCase()) {
      case "wrong":
        counterWrongWords += value;
        break;

      case "right":
        counterRightWords += value;
        break;

      case "remaining":
        counterRemainingWords += value;
        break;

      default:
        throw new NoWordException();
    }
  }

  public LearningSet getActiveLearningSet() {
    logger.info("Successfully returned active learning set.");
    return activeLearningSet;
  }

  public void setActiveLearningSet(LearningSet learningSet) {
    activeLearningSet = learningSet;
    logger.info("Das Aktive LearningSet wurde auf " + learningSet + " gesetzt");
  }

  public void addLearningSet(LearningSet learningSet) {
    learningSets.add(learningSet);
  }

  public void removeLearningSet(LearningSet learningSet) {
    learningSets.remove(learningSet);
  }

  public ArrayList<LearningSet> getLearningSets() {
    return learningSets;
  }

  public int getCurrentWordIndex() {
    return currentWordIndex;
  }

  public void setCurrentWordIndex(int index) {
    currentWordIndex = index;
  }

  /**
   * Method that resets all counters to 0.
   */
  public void resetInts() {
    currentWordIndex = 0;
    counterWrongWords = 0;
    counterRemainingWords = 0;
    counterRightWords = 0;
  }

  public boolean getIsLearning() {
    return isLearning;
  }

  public void setIsLearning(boolean learning) {
    isLearning = learning;
  }
}