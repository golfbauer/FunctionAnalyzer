package de.hhn.it.pp.components.typingtrainer;

import java.io.File;
import java.util.ArrayList;

/***
 * @author Tobias Maraci, Robert Pistea
 * @version 1.2
 * @since 1.0
 */

public class TypingTrainerDescriptor {
  private File audioWrongWord;
  private Feedback feedback;
  private PracticeText practiceText;
  private String[] typedWords; //words that user typed
  private int crntIndex;

  public TypingTrainerDescriptor(File audioWrongWord, Feedback feedback, PracticeText selectedText)
  {
    this.audioWrongWord = audioWrongWord;
    this.feedback = feedback;
    this.practiceText = selectedText;
    this.crntIndex = 0;
  }

  public String[] getTypedWords() {
    return typedWords;
  }

  public void setTypedWords(String[] typedWords) {
    this.typedWords = typedWords;
  }

  public void addTypedWords(String word, int index)
  {
    //if(!word.equals(" ")) {word.strip();} //löscht das leerzeichen falls eins im wort ist}
    typedWords[index] = typedWords[index].concat(word);
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
