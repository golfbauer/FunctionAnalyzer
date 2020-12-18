package de.hhn.it.pp.components.typingtrainer.provider;

import de.hhn.it.pp.components.typingtrainer.Feedback;
import de.hhn.it.pp.components.typingtrainer.FeedbackNotFoundException;
import de.hhn.it.pp.components.typingtrainer.SaveLoad;
import de.hhn.it.pp.components.typingtrainer.TypingTrainerDescriptor;
import de.hhn.it.pp.components.typingtrainer.TypingTrainerService;
import java.awt.Color;
import java.io.IOException;
import java.time.LocalTime;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Provider Interface to be filled with content(methods).
 *
 * @author Tobias Maraci, Robert Pistea
 * @version 1.1
 * @since 1.0
 */

public class ProviderTypingTrainer implements TypingTrainerService {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(ProviderTypingTrainer.class);

  public TypingTrainerDescriptor descriptor;
  public String data;
  public String path = "highscores.txt";

  /**
   * Checks if a word is written correctly and calls
   * markWord to either mark the current Word or marks the correct Word.
   *
   * @param word  word to check
   * @param index index of word to check
   * @return true when word is correct
   */
  @Override
  public boolean checkWord(String word, int index) {
    logger.debug("Check "+word+"at index "+index);

    String wordPt = descriptor.getPracticeText().getWordAtIndex(index).trim();
    return word.equals(wordPt) ? true : false;
  }

  /**
   * Plays the sound for wrong Words Game goes brrrrrt.
   */
  @Override
  public void audioOutput()
      throws IOException, UnsupportedAudioFileException, LineUnavailableException {
    logger.debug("Plays wrongWord-sound");

    String path = "javafx/src/main/resources/typingTrainerFiles/8BIT RETRO Beep.mp3";

    logger.debug("play media from path: " + path);
  }

  /**
   * Quits learning session and return to main menu.
   */
  @Override
  public void quitSession() {
    logger.debug("Return to StartScreen");
  }

  /**
   * Shows feedback.
   *
   * @param feedback feedback to show
   */
  @Override
  public void showFeedback(Feedback feedback) throws IOException, FeedbackNotFoundException {

    logger.debug("Show Feedback");

    if (feedback == null) {
      throw new FeedbackNotFoundException("feedback == null");
    }

    logger.debug("Time: " + feedback.getTime());
    logger.debug("WPM: " + feedback.getWordsPerMinute());

    feedback.calculateTime();
    feedback.calculateWordsPerMinute(descriptor.getTypedWords(),
        descriptor.getPracticeText().getText());

    if (feedback.getTime() < 0) {
      throw new FeedbackNotFoundException("time is < 0");
    }
    logger.debug("calculated time: " + feedback.getTime());
    logger.debug("wpm: " + feedback.getWordsPerMinute());
  }

  /**
   * saves feedback (score).
   *
   * @param score feedback to save
   */
  @Override
  public void saveScore(Feedback score) throws IOException {
    logger.debug("Save Score");

    SaveLoad save = new SaveLoad();
    //    save.save("selected text", String.valueOf(score.getTime()),
    //     String.valueOf(score.getWordsPerMinute()));
    ClassLoader classLoader;
    classLoader = getClass().getClassLoader();
    String filePath = classLoader.getResource(path).getFile();
    save.save(filePath);
  }

  /**
   * loads saved feedbacks (scores).
   */
  @Override
  public void loadScore() throws IOException {
    logger.debug("Load Score");

    SaveLoad load = new SaveLoad();
    ClassLoader classLoader;
    classLoader = getClass().getClassLoader();
    String filePath = classLoader.getResource(path).getFile();
    data = load.load(filePath);

    String[] datas = data.split(" ");

    for (int i = 0; i < datas.length; i++) {
      logger.debug(datas[i]);
    }
  }

  /**
   * Gets the userinput aka keystrokes through a scanner
   * and is potentially used for Feedback, CheckWord etc.
   */
  @Override
  public void userInput() throws IOException {
    logger.debug("Look for user input");

    int currentIndex = descriptor.getPracticeText().getCurrentWordIndex();

    descriptor.addTypedWords("typed word", currentIndex);

    if (checkWord(descriptor.getTypedWordsAtIndex(currentIndex), currentIndex)) {
      markWord(currentIndex, Color.GREEN);
    } else {
      markWord(currentIndex, Color.RED);
    }

    descriptor.getPracticeText().increaseCurrentWordIndex();
  }

  /**
   * Print a countdown.
   *
   * @param seconds seconds from where to start counting down
   * @throws InterruptedException If an interruption exception occurred
   */
  @Override
  public void countdown(int seconds) throws InterruptedException {
    logger.debug("Set start/endtime");

    if (seconds == 11) {
      descriptor.getFeedback().setStartTime(LocalTime.now().toNanoOfDay());
    } else if (seconds == 66) {
      descriptor.getFeedback().setEndTime(LocalTime.now().toNanoOfDay());
    }
  }

  /**
   * Marks either the currentWord or if the word is written correctly depending on checkWord.
   *
   * @param index index of word to check
   * @param color Color of supposed word to get marked
   */
  @Override
  public void markWord(int index, Color color) throws IOException {
    logger.debug("Mark word at index "+index+"in color "+color.toString());

    if (color.equals(Color.GREEN)) {
      logger.debug("Mark: " + descriptor.getTypedWordsAtIndex(index) + " green");
    } else {
      logger.debug("Mark: " + descriptor.getTypedWordsAtIndex(index) + " red");
      try {
        audioOutput();
      } catch (UnsupportedAudioFileException e) {
        logger.warn("Unsupported Audiofile");
      } catch (LineUnavailableException e) {
        logger.warn("Line Unavailable");
      }
    }
  }

  /**
   * Selecting the text you want to train
   * your typing in(preset texts not individual texts from User).
   */
  @Override
  public void selectionOfText() {
    logger.debug("Select a text");
  }
}
