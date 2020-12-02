package de.hhn.it.pp.components.typingtrainer;
/***
 * @author Tobias Maraci, Robert Pistea
 * @version 1.3
 * @since 1.1
 */
public class PracticeText {
  private String[] text; //actual practice text
  private int currentWordIndex;

  public PracticeText(String[] text) {
    this.text = text;
    this.currentWordIndex = 0;
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

  public int getCurrentWordIndex() {
    return currentWordIndex;
  }

  public void setCurrentWordIndex(int currentWordIndex) {
    this.currentWordIndex = currentWordIndex;
  }

  public void increaseCurrentWordIndex()
  {
    ++currentWordIndex;
  }

  public String getWordAtIndex(int index)
  {
    return text[index];
  }
}
