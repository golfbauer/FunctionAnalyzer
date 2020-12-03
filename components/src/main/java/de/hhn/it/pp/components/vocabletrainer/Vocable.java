package de.hhn.it.pp.components.vocabletrainer;

import de.hhn.it.pp.components.vocabletrainer.exceptions.TranslationIsEmptyException;

public class Vocable {
  private String learningWord;
  private String[] translations;

  public Vocable(String learningWord, String[] translations) throws TranslationIsEmptyException {
    this.learningWord = learningWord;
    checkTranslation(translations);
    this.translations = translations.clone();
  }

  public String getLearningWord() {
    return learningWord;
  }

  public void setLearningWord(String learningWord) {
    this.learningWord = learningWord;
  }

  public String[] getTranslations() {
    return translations.clone();
  }

  public void setTranslations(String[] translations) throws TranslationIsEmptyException {
    checkTranslation(translations);
    this.translations = translations.clone();
  }

  private void checkTranslation(String[] translations) throws TranslationIsEmptyException {
    if (translations == null || translations.length == 0 || translations[0].trim().length() == 0) {
      throw new TranslationIsEmptyException("Translation is Empty!");
    }
  }
}
