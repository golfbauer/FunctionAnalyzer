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
   * Plays the audio file.
   *
   * @param audioFile Audiofile of the word the user needs to spell
   */
  void audioOutput(File audioFile) throws FileNotFoundException;

  /**
   * Checks the spelling of the entered word.
   *
   * @param writtenWord word that was entered by the user
   * @return Returns true if the spelling of the word was correct, false if the spelling was wrong
   */
  boolean checkSpelling(String writtenWord);

  /**
   * Adds the given word to the Learning List and maps the audio file to the word.
   *
   * @param word  word that will be added
   * @param audio audio that will be mapped to the word
   */
  void addWord(String word, File audio) throws WordAlreadyAddedException, FileNotFoundException;

  /**
   * Removes the given word from the Learning List and removes the mapping.
   *
   * @param word word that is to be removed
   */
  void deleteWord(String word) throws WordNotFoundException;
}
