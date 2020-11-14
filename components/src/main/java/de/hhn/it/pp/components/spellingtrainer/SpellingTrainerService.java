package de.hhn.it.pp.components.spellingtrainer;

import de.hhn.it.pp.components.spellingtrainer.exceptions.WordAlreadyAddedException;
import de.hhn.it.pp.components.spellingtrainer.exceptions.WordNotFoundException;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * The SpellingTrainerInterface shows the basic functionalities of the Spellingtrainer.
 */
public interface SpellingTrainerService {

  /**
   * Checks the spelling of the entered word.
   *
   * @param learningEntry learning entry to be spelled
   * @param enteredWord   word, that the user entered
   * @return Returns true if the spelling of the word was correct, false if the spelling was wrong
   */
  boolean checkSpelling(String enteredWord, LearningEntry learningEntry);

  /**
   * Adds the given word to the learning set and maps the audio file to the word.
   *
   * @param word        word that will be added
   * @param audio       audio that will be mapped to the word
   * @param learningSet set, that the word should be added to
   */
  void addWord(String word, File audio, LearningSet learningSet)
      throws WordAlreadyAddedException, FileNotFoundException;

  /**
   * Removes the given word from the Learning List and removes the mapping.
   *
   * @param word        word that is to be removed
   * @param learningSet set, that the word is deleted from
   */
  void deleteWord(String word, LearningSet learningSet) throws WordNotFoundException;
}
