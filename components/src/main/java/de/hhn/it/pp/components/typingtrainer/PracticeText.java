package de.hhn.it.pp.components.typingtrainer;
/***
 * @author Tobias Maraci, Robert Pistea
 * @version 1.0
 * @since 1.1
 */
public class PracticeText {
  private String[] text; //actual practice text
  private String typedWords; //words that user typed
  private int currentWordIndex;

  public PracticeText(String[] text) {
    this.text = text;
    this.currentWordIndex = 0;
  }

  public String getTypedWords() {
    return typedWords;
  }

  public void setTypedWords(String typedWords) {
    this.typedWords = typedWords;
  }

  public String[] getText() {
    return text;
  }

  public void setText(String[] text) {
    this.text = text;
  }

  public void printPracticeText() {
    for (int i = 0; i < text.length; i++) {
      System.out.print(text[i] + " ");
    }
    System.out.println();
  }

  /**
   * Changes color of a word
   * @param word which should be colored
   */
  public void markWord(String word) {
    System.out.println(word + " marked");
  }

  public int getCurrentWordIndex() {
    return currentWordIndex;
  }

  public void setCurrentWordIndex(int currentWordIndex) {
    this.currentWordIndex = currentWordIndex;
  }

  /**
   * Checks if a word is correctly wirtten
   */
  public void checkSpelling() {
    System.out.println("checken ob etwas falsch geschrieben wurde");
  }
}
