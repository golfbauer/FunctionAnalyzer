package de.hhn.it.pp.components.typingtrainer;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/***
 * @author Tobias Maraci, Robert Pistea
 * @version 1.2
 * @since 1.0
 */

public interface TypingTrainerService {

  /**
   * Checks if a word is written correctly and calls markWord to either mark the current Word or marks the correct Word
   * @param word word to check
   * @return true when word is correct
   */
  boolean checkWord(String word, int index);

  /**
   * Plays the sound for wrong Words Game goes brrrrrt.
   */
  void audioOutput();

  /**
   * Quits learning session and return to main menu
   */
  void quitSession();

  /**
   * Shows feedback
   * @param feedback feedback to show
   */
  void showFeedback(Feedback feedback) throws IOException;

  /**
   * saves feedback (score)
   * @param score feedback to save
   */
  void saveScore(Feedback score) throws IOException;

  /**
   * loads saved feedbacks (scores)
   */
  void loadScore();

  /**
   * Gets the userinput aka keystrokes through a scanner and is potentially used for Feedback, CheckWord etc
   */
  void userInput() throws IOException;

  /**
   * Print a countdown
   * @param seconds seconds from where to start counting down
   * @throws InterruptedException If an interruption exception occurred
   */
  void countdown(int seconds) throws InterruptedException;

  /**
   * Marks either the currentWord or if the word is written correctly depending on checkWord.
   * @param index
   */
  void markWord(int index, Color color) throws IOException;

  /**
   * Selecting the text you want to train your typing in(preset texts not individual texts from User)
   */
  void selectionOfText();
}