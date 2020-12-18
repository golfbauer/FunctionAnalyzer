package de.hhn.it.pp.components.typingtrainer;

import java.io.File;

/**
 * Descriptor class with constructor to be used for
 * getting all the needed feedback, typedWords and practiceText.
 *
 * @author Tobias Maraci, Robert Pistea
 * @version 1.3
 * @since 1.0
 */

public class TypingTrainerDescriptor {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(TypingTrainerDescriptor.class);

  private File audioWrongWord;
  private Feedback feedback;
  private PracticeText practiceText;
  private String[] typedWords; //words that user typed

  /**
   * Constructor for a basic typingTrainer Model.
   * @param audioWrongWord audio used for output when a word is wrong
   * @param feedback feedback from a session
   * @param selectedText the text used for practice
   */
  public TypingTrainerDescriptor(File audioWrongWord, Feedback feedback,
                                 PracticeText selectedText) {
    this.audioWrongWord = new File("components/src/main/resources/8BIT RETRO Beep.mp3");
    this.feedback = feedback;
    this.practiceText = selectedText;
    this.typedWords = new String[selectedText.getText().length];
  }

  public String[] getTypedWords() {
    return typedWords;
  }

  public void setTypedWords(String[] typedWords) {
    this.typedWords = typedWords;
  }

  public String getTypedWordsAtIndex(int index) {
    return typedWords[index];
  }

  /**
   * Adds a word to typedWords at index.
   *
   * @param word  word to write in typedWords Array
   * @param index index where the word should be added
   */
  public void addTypedWords(String word, int index) {
    typedWords[index] = word;
    logger.debug("word added: " + word);
  }

  public File getAudioWrongWord() {
    return audioWrongWord;
  }

  public void setAudioWrongWord(File audioWrongWord) {
    this.audioWrongWord = audioWrongWord;
  }

  public Feedback getFeedback() {
    return feedback;
  }

  public void setFeedback(Feedback feedback) {
    this.feedback = feedback;
  }

  public PracticeText getPracticeText() {
    return practiceText;
  }

  public void setPracticeText(PracticeText practiceText) {
    this.practiceText = practiceText;
  }

}
