package de.hhn.it.pp.components.spellingtrainer;

import de.hhn.it.pp.components.spellingtrainer.exceptions.CounterNotFoundException;
import java.util.ArrayList;


public class SpellingTrainerDescriptor {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SpellingTrainerDescriptor.class);


  private int counterWrongWords;
  private int counterRightWords;
  private int counterRemainingWords;
  LearningSet activeLearningSet;
  private ArrayList<LearningSet> learningSets = new ArrayList<>();


  /**
   * Returns the counter value of the addressed counter.
   *
   * @param counterName name of the counter (wrong, right, remaining)
   * @return counter value
   * @throws CounterNotFoundException is thrown when no counter with the entered name is found
   */
  public int getCounter(String counterName) throws CounterNotFoundException {

    switch (counterName.toLowerCase()) {
      case "wrong":
        return counterWrongWords;

      case "right":
        return counterRightWords;

      case "remaining":
        return counterRemainingWords;

      default:
        throw new CounterNotFoundException();

    }
  }

  /**
   * Updates the counter value by adding the entered value.
   *
   * @param counterName counterName name of the counter (wrong, right, remaining)
   * @param value       value that the counter ist updated with
   * @throws CounterNotFoundException is thrown when no counter with the entered name is found
   */
  public void updateCounter(String counterName, int value) throws CounterNotFoundException {

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
        throw new CounterNotFoundException();

    }
  }

  public void setActiveLearningSet(LearningSet learningSet) {
    this.activeLearningSet = learningSet;
  }

  public LearningSet getActiveLearningSet() {
    logger.info("Successfully returned active learning set.");
    return activeLearningSet;


  }


}
