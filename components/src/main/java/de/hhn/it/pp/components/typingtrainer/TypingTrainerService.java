package de.hhn.it.pp.components.typingtrainer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/***
 * @author Tobias Maraci, Robert Pistea
 * @version 1.1
 * @since 1.0
 */

public interface TypingTrainerService {

  /**
   * Checks if a word is written correctly
   * @param word word to check
   * @return true when word is correct
   */
  boolean checkWord(String word);

  /**
   * Plays a sound
   * @param soundFile file that is played
   */
  void audioOutput(File soundFile);

  /**
   * Quits learning session and return to main menu
   */
  void quitSession();

  /**
   * Shows feedback
   * @param feedback feedback to show
   */
  void showFeedback(Feedback feedback);

  /**
   * saves feedback (score)
   * @param score feedback to save
   */
  void saveScore(Feedback score);

  /**
   * loads saved feedbacks (scores)
   */
  void loadScore();

  /**
   * Gets user input
   * @param practiceText text that user writes down
   */
  void userInput(PracticeText practiceText);

  /**
   * Print a countdown
   * @param seconds seconds from where to start counting down
   * @throws InterruptedException If an interruption exception occurred
   */
  void countdown(int seconds) throws InterruptedException;
}