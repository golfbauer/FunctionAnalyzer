package de.hhn.it.pp.components.vocabletrainer.provider;

import de.hhn.it.pp.components.vocabletrainer.LearningState;
import de.hhn.it.pp.components.vocabletrainer.Vocable;
import de.hhn.it.pp.components.vocabletrainer.VocableTrainerService;
import de.hhn.it.pp.components.vocabletrainer.exceptions.TranslationIsEmptyException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryAlreadyExistException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryNotFoundException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocableNotFoundException;
import java.util.HashMap;
import java.util.List;

public class JBVocableTrainerService implements VocableTrainerService {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(JBVocableTrainerService.class);

  JBVocableTrainer trainer = new JBVocableTrainer();

  /**
   * Returns the current score.
   *
   * @return Score
   */
  @Override
  public int getScore() {
    logger.info("getScore: no params");
    return trainer.getScore();
  }

  /**
   * Returns all Categories saved in the Hashmap.
   *
   * @return List of registered Categories
   */
  @Override
  public List<String> getVocCategories() {
    logger.info("getVocCategories: no params");
    return trainer.getVocCategories();
  }

  /**
   * Add a new Category with a new vocabularyList to the hashmap.
   *
   * @param category       name of the category
   * @param vocabularyList list of vocabulary
   * @throws VocCategoryAlreadyExistException when category name already exist
   */
  @Override
  public void addVocCategory(String category, List<Vocable> vocabularyList)
      throws VocCategoryAlreadyExistException {
    logger.info("addVocCategory: category = {}, vocabularyList = {}", category, vocabularyList);
    trainer.addCategory(category, vocabularyList);
  }

  /**
   * Remove VocCategory from the hashmap.
   *
   * @param category name of the category
   * @throws VocCategoryNotFoundException when category name doesn't exist
   */
  @Override
  public void removeVocCategory(String category) throws VocCategoryNotFoundException {
    logger.info("removeVocCategory: category = {}", category);
    trainer.deleteCategory(category);
  }

  /**
   * Returns the Vocable.
   *
   * @param id       position of the vocab in the list
   * @param category name of the category
   * @return Vocable
   * @throws VocableNotFoundException     when the vocable doesn't exist
   * @throws VocCategoryNotFoundException when category name doesn't exist
   */
  @Override
  public Vocable getVocable(int id, String category)
      throws VocableNotFoundException, VocCategoryNotFoundException {
    logger.info("getVocable: id = {}, category = {}", id, category);
    return trainer.getVocableList(category).get(id);
  }

  /**
   * Returns all Vocabulary in the category.
   *
   * @param category name of the category whose list is to be output
   * @return List of registered vocabulary
   * @throws VocCategoryNotFoundException when category name doesn't exist
   */
  @Override
  public List<Vocable> getVocabulary(String category) throws VocCategoryNotFoundException {
    logger.info("getVocabulary: category = {}", category);
    return trainer.getVocableList(category);
  }

  /**
   * Add a new Vocable to the Vocabulary list from the given category.
   *
   * @param learningWord the vocabulary to be learned
   * @param translations translations of the vocabulary
   * @param category     name of the category
   * @throws VocCategoryNotFoundException when CategoryId doesn't exist
   * @throws TranslationIsEmptyException  when the translation array is empty
   */
  @Override
  public void addVocable(String learningWord, String[] translations, String category)
      throws VocCategoryNotFoundException, TranslationIsEmptyException {
    logger.info("addVocable: learningWord = {}, translations = {}, category = {}", learningWord,
        translations, category);
    trainer.getVocableList(category).add(new Vocable(learningWord, translations));
  }

  /**
   * Remove Vocable from Vocabulary list from the category.
   *
   * @param id       position of the word in the list
   * @param category name of the category where the vocable is in
   * @throws VocableNotFoundException     when Id doesn't exist
   * @throws VocCategoryNotFoundException when Category doesn't exist
   */
  @Override
  public void removeVocable(int id, String category)
      throws VocableNotFoundException, VocCategoryNotFoundException {
    logger.info("removeVocable: id = {}, category = {}", id, category);
    trainer.getVocableList(category).remove(id);
  }

  /**
   * Edit the name of the category.
   *
   * @param oldCategoryName name from the category
   * @param newCategoryName name of the edited category,
   * @throws VocCategoryNotFoundException if category doesn't exist
   */
  @Override
  public void editVocCategory(String oldCategoryName, String newCategoryName)
      throws VocCategoryNotFoundException {
    logger.info("editVocCategory: oldCategoryName = {}, newCategoryName = {}", oldCategoryName,
        newCategoryName);
    try {
      trainer.addCategory(newCategoryName, trainer.getVocableList(oldCategoryName));
      trainer.deleteCategory(oldCategoryName);
    } catch (VocCategoryAlreadyExistException e) {
      e.printStackTrace();
    }
  }

  /**
   * Checks if the vocable is correct with the levenshtein distance.
   *
   * @param word                input from the user
   * @param id                  position of the vocab in the list
   * @param category            name of the category
   * @param levenshteinDistance deviation from the right word
   * @return word is correct
   * @throws VocCategoryNotFoundException if category doesn't exist
   */
  @Override
  public boolean checkVocable(String word, int id, String category, int levenshteinDistance)
      throws VocCategoryNotFoundException {
    logger.info("checkVocable: word = {}, category = {}, levenshteinDistance = {}", id, category,
        levenshteinDistance);
    Vocable vocable = trainer.getVocableList(category).get(id);
    int lev = Integer.MAX_VALUE;
    for (String translation : vocable.getTranslations()) {
      lev = Math.min(lev, levenshteinDistance(word, translation));
    }
    if (lev > levenshteinDistance) {
      return false;
    } else if (lev == 0) {
      trainer.setScore(trainer.getScore() + 10);
      return true;
    } else if (lev <= 2) {
      trainer.setScore(trainer.getScore() + 5);
      return true;
    } else {
      trainer.setScore(trainer.getScore() + 2);
      return true;
    }
  }

  /**
   * Edit a vocab within the list from the category.
   *
   * @param id           position of the vocab in the list
   * @param learningWord string of the origin word
   * @param translations list of translated words
   * @param category     name of the category
   * @throws VocableNotFoundException     when vocableId doesn't exist
   * @throws VocCategoryNotFoundException when categoryId doesn't exist
   * @throws TranslationIsEmptyException  when the translation array is empty
   */
  @Override
  public void editVocable(int id, String learningWord, String[] translations, String category)
      throws VocableNotFoundException, VocCategoryNotFoundException, TranslationIsEmptyException {
    logger.info("editVocable: id = {}, learningWord = {}, translations = {}, category = {}", id,
        learningWord, translations, category);
    Vocable vocEdit = new Vocable(learningWord, translations);
    trainer.deleteVocable(category, vocEdit);
    trainer.getVocableList(category).get(id).setLearningWord(learningWord);
    trainer.getVocableList(category).get(id).setTranslations(translations);
  }

  /**
   * Load data into the vocableTrainer component.
   *
   * @param learningState state of the learning status
   * @return the success of the process
   */
  @Override
  public boolean loadData(LearningState learningState) {
    logger.info("loadData: learningState = {}", learningState);
    trainer.setScore(learningState.getScore());

    for (HashMap.Entry<String, List<Vocable>> entry : learningState.getVocabularyList()
        .entrySet()) {
      String key = entry.getKey();
      List<Vocable> value = entry.getValue();

      try {
        trainer.addCategory(key, value);
      } catch (VocCategoryAlreadyExistException e) {
        e.printStackTrace();
        return false;
      }
    }
    return true;
  }

  public int levenshteinDistance(String word1, String word2) {

    return -1;
  }
}