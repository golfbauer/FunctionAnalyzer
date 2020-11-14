package de.hhn.it.pp.components.vocabletrainer;

public class Vocable {
  private String learningWord;
  private String[] translations;

  public Vocable(String learningWord, String[] translations) {
    this.learningWord = learningWord;
    this.translations = translations;
  }

  public String getLearningWord() {
    return learningWord;
  }

  public String[] getTranslations() {
    return translations;
  }

  public void setLearningWord(String learningWord) {
    this.learningWord = learningWord;
  }

  public void setTranslations(String[] translations) {
    this.translations = translations;
  }
}
