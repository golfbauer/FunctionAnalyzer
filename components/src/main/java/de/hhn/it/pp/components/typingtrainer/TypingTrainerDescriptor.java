package de.hhn.it.pp.components.typingtrainer;

import java.io.File;
import java.util.ArrayList;

/***
 * @author Tobias Maraci, Robert Pistea
 * @version 1.3
 * @since 1.0
 */

public class TypingTrainerDescriptor {
  private File audioWrongWord;
  private Feedback feedback;
  private PracticeText practiceText;
  private String[] typedWords; //words that user typed

  public TypingTrainerDescriptor(File audioWrongWord, Feedback feedback, PracticeText selectedText)
  {
    this.audioWrongWord = new File("components/src/main/resources/8BIT RETRO Beep.mp3");
    this.feedback = feedback;
    this.practiceText = selectedText;
    this.typedWords = new String[selectedText.getText().length];
  }

  public String[] getTypedWords() {
    return typedWords;
  }

  public String getTypedWordsAtIndex(int index)
  {
    return typedWords[index];
  }

  public void setTypedWords(String[] typedWords) {
    this.typedWords = typedWords;
  }

  /**
   * Adds a word to typedWords at index
   * @param word word to write in typedWords Array
   * @param index index where the word should be added
   */
  public void addTypedWords(String word, int index)
  {
    //if(!word.equals(" ")) {word.strip();} //löscht das leerzeichen falls eins im wort ist}
    typedWords[index] = word;
    System.out.println("Hinzugefügt: "+word);
  }

  public void setAudioWrongWord(File audioWrongWord) {
    this.audioWrongWord = audioWrongWord;
  }

  public File getAudioWrongWord() {
    return audioWrongWord;
  }

  public void setFeedback(Feedback feedback) {
    this.feedback = feedback;
  }

  public Feedback getFeedback() {
    return feedback;
  }

  public void setPracticeText(PracticeText practiceText) {
    this.practiceText = practiceText;
  }

  public PracticeText getPracticeText() {
    return practiceText;
  }

}
