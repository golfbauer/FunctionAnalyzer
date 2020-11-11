package de.hhn.it.pp.components.typingtrainer;

import java.io.File;
import java.util.ArrayList;

/***
 * @author Tobias Maraci, Robert Pistea
 * @version 1.1
 * @since 1.0
 */

public class TypingTrainerDescriptor {
  private File audioWrongWord;
  private Feedback feedback;
  private PracticeText practiceText;

  public TypingTrainerDescriptor(File audioWrongWord, Feedback feedback, PracticeText selectedText)
  {
    this.audioWrongWord = audioWrongWord;
    this.feedback = feedback;
    this.practiceText = selectedText;
  }

  public void setAudioWrongWord(File audioWrondWord) {
    this.audioWrongWord = audioWrondWord;
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
