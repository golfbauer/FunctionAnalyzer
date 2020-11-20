package de.hhn.it.pp.components.vocabletrainer;

import java.util.HashMap;
import java.util.List;

public class LearningState {
  private int score;
  private HashMap<String, List<Vocable>> vocabularyList;

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public HashMap<String, List<Vocable>> getVocabularyList() {
    return vocabularyList;
  }

  public void setVocabularyList(
      HashMap<String, List<Vocable>> vocabularyList) {
    this.vocabularyList = vocabularyList;
  }
}
