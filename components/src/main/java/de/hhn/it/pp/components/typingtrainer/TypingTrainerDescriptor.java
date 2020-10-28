package de.hhn.it.pp.components.typingtrainer;

import java.io.File;
import java.util.ArrayList;

/***
 * @author Tobias Maraci, Robert Pistea
 * @version 1.0
 */

public class TypingTrainerDescriptor {
  private File audioWrongWord; // Audio für falsche Wörter
  private int counterRightWords; // Zähler für richtige Wörter (WPM)
  private ArrayList<String> selectedText = new ArrayList<>(); // Text der ausgewählt wird
  private long time;
  private float wordsPerMinute;

  public File getAudioWrongWord() {
    return audioWrongWord;
  }

  public void setAudioWrongWord(File media) {
    audioWrongWord = media;
  }

  public int getCounterRightWords() {
    return counterRightWords;
  }

  public void setCounterRightWords(int newValue) {
    counterRightWords += newValue;
  }

  /**
   * Add 1 to counterRightWords
   */
  public void addCounterRightWords() {
    counterRightWords += 1;
  }

  public ArrayList<String> getSelectedText() {
    return selectedText;
  }

  public void setSelectedText(ArrayList<String> text) {
    selectedText = text;
  }

  public long getTime() {
    return time;
  }

  public void setTime(long newTime) {
    time = newTime;
  }

  public float getWordsPerMinute() {
    float time = this.time / 1000 / 60;
    float counter = getCounterRightWords();

    wordsPerMinute = time / counter;

    return wordsPerMinute;
  }

  /**
   * Calculates the time how long it took to complete the text
   *
   * @return time needed to complete session
   */
  public long calculateTime(long startTime, long endTime) {
    startTime = startTime / 1000;
    endTime = endTime / 1000;

    return endTime - startTime;
  }

  @Override
  public String toString() {
    return "TypingTrainerDescriptor{" +
        "audioWrongWord=" + audioWrongWord +
        ", counterRightWords=" + counterRightWords +
        ", selectedText=" + selectedText +
        ", time=" + time +
        ", wordsPerMinute=" + wordsPerMinute +
        '}';
  }
}
