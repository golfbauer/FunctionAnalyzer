package de.hhn.it.pp.components.typingtrainer;

import java.io.File;
import java.io.FileNotFoundException;

/***
 * @author Tobias Maraci, Robert Pistea
 * @version 1.01
 */

public interface TypingTrainerService {
  /***
   * Checks the written Word if its correct
   * @param word: word that needs to be checked
   * @return : true, if its right
   * @throws WordNotFoundException
   */
  boolean checkWord(String word) throws WordNotFoundException;

  /***
   * Plays a sound, if the word is wrong
   * @param soundFile: Sound that gets played
   * @throws FileNotFoundException
   */
  void audioOutput(File soundFile) throws FileNotFoundException;

  /***
   * Marks a word that is written correct
   * @param word: Word that gets marked
   * @throws WordNotFoundException
   */
  void markWord(String word) throws WordNotFoundException;

  /***
   * Selects the text that you want to practice
   * @param selectedText: Selected Text
   */
  void selectionOfText(String selectedText);

  /***
   * Quits a learning session
   */
  void quitSession();

  /***
   * Shows Feedback
   * @param time Time for completion
   * @param wordsPerMinute Words that are correct in a minute
   */
  void showFeedback(long time, float wordsPerMinute);

  /***
   * Saves the Progress of the User in a text file
   * @param saveFile: File in which it gets saved
   * @throws FileNotFoundException
   */
  void saveScore(File saveFile) throws FileNotFoundException;

  /***
   * Loads die txt file, if she exists
   * @param saveFile: File that gets loaded
   * @throws FileNotFoundException
   */
  void loadScore(File saveFile) throws FileNotFoundException;

}