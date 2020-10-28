package de.hhn.it.pp.components.spellingtrainer;

import de.hhn.it.pp.components.spellingtrainer.exceptions.CounterNotFoundException;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


public class SpellingTrainerDescriptor {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SpellingTrainerDescriptor.class);

  private HashMap<String, File> audioFiles = new HashMap<>();
  private ArrayList<String> spellingWords = new ArrayList<>();
  private int counterWrongWords;
  private int counterRightWords;
  private int counterRemainingWords;

  public void setAudioFiles(String spellingWord, File audioFile) {
    audioFiles.put(spellingWord, audioFile);
  }

  public File getAudioFile(String spellingWord) {
    return audioFiles.get(spellingWord);
  }

  public void setSpellingWord(String spellingWord) {
    spellingWords.add(spellingWord);
  }

  public String getSpellingWord(int index) {
    return spellingWords.get(index);
  }

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
}
