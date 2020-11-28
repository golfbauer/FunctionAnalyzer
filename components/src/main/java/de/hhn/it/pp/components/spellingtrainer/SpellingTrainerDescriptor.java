package de.hhn.it.pp.components.spellingtrainer;

import de.hhn.it.pp.components.spellingtrainer.exceptions.CounterNotFoundException;
import java.util.ArrayList;


public class SpellingTrainerDescriptor {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SpellingTrainerDescriptor.class);

  private static int currentWordIndex;
  private static int counterWrongWords;
  private static int counterRightWords;
  private static int counterRemainingWords;
  static LearningSet activeLearningSet;
  private static ArrayList<LearningSet> learningSets = new ArrayList<>();


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
    logger.info("Das Aktive LearningSet wurde auf " + learningSet + " gesetzt");
  }

  public LearningSet getActiveLearningSet() {
    logger.info("Successfully returned active learning set.");
    return activeLearningSet;


  }

  public static void addLearningSet(LearningSet learningSet){
    learningSets.add(learningSet);
  }

  public static void removeLearningSet(LearningSet learningSet){
    learningSets.remove(learningSet);
  }

  public static ArrayList<LearningSet> getLearningSets(){
    return learningSets;
  }

  public static int getCurrentWordIndex(){
    return currentWordIndex;
  }

  public static void setCurrentWordIndex(int index){
    currentWordIndex = index;
  }

  public static void resetInts(){
    currentWordIndex = 0;
    counterWrongWords = 0;
    counterRemainingWords = 0;
    counterRightWords = 0;
  }

}
