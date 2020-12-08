package de.hhn.it.pp.components.typingtrainer;

/***
 * @author Tobias Maraci, Robert Pistea
 * @version 1.3
 * @since 1.1
 */

public class Feedback {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(Feedback.class);

  private double time, startTime, endTime;
  private int wordsPerMinute;
  private int counterRightWords;
  private int avgWordLength = 5; //durchschnittliche Wortlänge englischer Wörter

  public Feedback(double time, int wordsPerMinute) {
    this.time = time;
    this.wordsPerMinute = wordsPerMinute;
  }

  public int getAvgWordLength() {
    return avgWordLength;
  }

  public void setAvgWordLength(int avgWordLength) {
    this.avgWordLength = avgWordLength;
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

  /**
   * Increases counterRightWords by one.
   */
  public void increaseCounterRightWords()
  {
    this.counterRightWords++;
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
   * Checks how much words are written correctly and
   * calculates a value for wordsPerMinute with:
   *
   * WordsPerMinute = ((writtenWords / averageWordLength) / time to complete text) * 60
   *
   * @param typedWords typed words
   */
  public void calculateWordsPerMinute(String[] typedWords, String[] selectedText) {

    int numChars = 0;

    for (int i = 0; i < selectedText.length; i++) {
      try {
        if (selectedText[i].equals(typedWords[i])) {
          numChars++;
        }
      } catch (ArrayIndexOutOfBoundsException e) {
        System.out.println("nicht alle wörter geschrieben");
        break;
      }
    }

    wordsPerMinute = (int) ((((double) numChars / avgWordLength) / time) * 60);
  }

  /**
   * Calculates time that was needed to write the text
   */
  public void calculateTime() {
    double elapsedTime = endTime - startTime;
    time = elapsedTime / 1000000000.0;
  }
}
