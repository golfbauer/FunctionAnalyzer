package de.hhn.it.pp.components.typingtrainer;
/***
 * @author Tobias Maraci, Robert Pistea
 * @version 1.0
 * @since 1.1
 */

public class Feedback {
  private double time, startTime, endTime;
  private int wordsPerMinute;
  private int counterRightWords;

  public Feedback(double time, int wordsPerMinute) {
    this.time = time;
    this.wordsPerMinute = wordsPerMinute;
  }

  public double getTime() {
    return time;
  }

  public void setTime(float time) {
    this.time = time;
  }

  public double getWordsPerMinute() {
    return wordsPerMinute;
  }

  public void setWordsPerMinute(int wordsPerMinute) {
    this.wordsPerMinute = wordsPerMinute;
  }

  public int getCounterRightWords() {
    return counterRightWords;
  }

  public void setCounterRightWords(int counterRightWords) {
    this.counterRightWords = counterRightWords;
  }

  public double getEndTime() {
    return endTime;
  }

  public void setEndTime(double endTime) {
    this.endTime = endTime;
  }

  public double getStartTime() {
    return startTime;
  }

  public void setStartTime(double startTime) {
    this.startTime = startTime;
  }

  /**
   * Calculates value for wordsPerMinute
   * @param typedWords typed words
   */
  public void calculateWordsPerMinute(String typedWords) {
    int numChars = typedWords.length();
    wordsPerMinute =
        (int) ((((double) numChars / 5) / time) * 60); //(typedWords / avarage length of a word) / time) * 60
  }

  /**
   * Calculates time that was needed to wirte the text
   */
  public void calculateTime() {
    double elapsedTime = endTime - startTime;
    time = elapsedTime / 1000000000.0;
  }
}
