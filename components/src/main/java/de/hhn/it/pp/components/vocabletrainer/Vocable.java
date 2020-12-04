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

  /**
   * Returns a string representation of the object. In general, the
   * {@code toString} method returns a string that
   * "textually represents" this object. The result should
   * be a concise but informative representation that is easy for a
   * person to read.
   * It is recommended that all subclasses override this method.
   * <p>
   * The {@code toString} method for class {@code Object}
   * returns a string consisting of the name of the class of which the
   * object is an instance, the at-sign character `{@code @}', and
   * the unsigned hexadecimal representation of the hash code of the
   * object. In other words, this method returns a string equal to the
   * value of:
   * <blockquote>
   * <pre>
   * getClass().getName() + '@' + Integer.toHexString(hashCode())
   * </pre></blockquote>
   *
   * @return a string representation of the object.
   */
  @Override
  public String toString() {
    StringBuilder tr = new StringBuilder();
    for (String st : translations) {
      tr.append(st + " ");
    }
    return learningWord + "(" + tr.toString() + ")";
  }
}
