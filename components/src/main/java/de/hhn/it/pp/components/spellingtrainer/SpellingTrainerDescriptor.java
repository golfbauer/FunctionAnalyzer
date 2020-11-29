package de.hhn.it.pp.components.spellingtrainer;

import de.hhn.it.pp.components.spellingtrainer.Provider.LearningSet;
import de.hhn.it.pp.components.spellingtrainer.exceptions.NoWordException;
import java.util.ArrayList;


public class SpellingTrainerDescriptor {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SpellingTrainerDescriptor.class);

  private static boolean isLearning;
  private static int currentWordIndex;
  private static int counterWrongWords;
  private static int counterRightWords;
  private static int counterRemainingWords;
  private static LearningSet activeLearningSet;
  private static ArrayList<LearningSet> learningSets = new ArrayList<>();


  /**
   * Returns the counter value of the addressed counter.
   *
   * @param counterName name of the counter (wrong, right, remaining)
   * @return counter value
   * @throws NoWordException is thrown when no counter with the entered name is found
   */
  public static int getCounter(String counterName) throws NoWordException {

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
  public static void updateCounter(String counterName, int value) throws NoWordException {

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

  public static void setActiveLearningSet(LearningSet learningSet) {
    activeLearningSet = learningSet;
    logger.info("Das Aktive LearningSet wurde auf " + learningSet + " gesetzt");
  }

  public static LearningSet getActiveLearningSet() {
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

  public static boolean getIsLearning(){
    return isLearning;
  }

  public static void setIsLearning(boolean learning){
    isLearning = learning;
  }

}
