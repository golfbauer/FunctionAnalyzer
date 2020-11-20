package de.hhn.it.pp.components.vocabletrainer;

import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryAlreadyExistException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryNotFoundException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocableNotFoundException;
import java.util.HashMap;
import java.util.List;

public interface VocableTrainerService {

  /**
   * Returns the current score.
   *
   * @return Score
   */
  int getScore();

  /**
   * Returns all Categories saved in the Hashmap.
   *
   * @return List of registered Categories
   */
  List<String> getVocCategories();

  /**
   * Add a new Category with a new vocabularyList to the hashmap.
   *
   * @param category       name of the category
   * @param vocabularyList list of vocabulary
   * @throws VocCategoryAlreadyExistException when category name already exist
   */
  void addVocCategory(String category, List<Vocable> vocabularyList)
      throws VocCategoryAlreadyExistException;

  /**
   * Remove VocCategory from the hashmap.
   *
   * @param category name of the category
   * @throws VocCategoryNotFoundException when category name doesn't exist
   */
  void removeVocCategory(String category) throws VocCategoryNotFoundException;

  /**
   * Returns the Vocable.
   *
   * @param id       position of the vocab in the list
   * @param category name of the category
   * @return Vocable
   * @throws VocableNotFoundException     when the vocable doesn't exist
   * @throws VocCategoryNotFoundException when category name doesn't exist
   */
  Vocable getVocable(int id, String category)
      throws VocableNotFoundException, VocCategoryNotFoundException;

  /**
   * Returns all Vocabulary in the category.
   *
   * @param category name of the category whose list is to be output
   * @return List of registered vocabulary
   * @throws VocCategoryNotFoundException when category name doesn't exist
   */
  List<Vocable> getVocabulary(String category) throws VocCategoryNotFoundException;

  /**
   * Add a new Vocable to the Vocabulary list from the given category.
   *
   * @param learningWord the vocabulary to be learned
   * @param translations translations of the vocabulary
   * @param category     name of the category
   * @throws VocCategoryNotFoundException when CategoryId doesn't exist
   */
  void addVocable(String learningWord, String[] translations, String category)
      throws VocCategoryNotFoundException;

  /**
   * Remove Vocable from Vocabulary list from the category.
   *
   * @param id       position of the word in the list
   * @param category name of the category where the vocable is in
   * @throws VocableNotFoundException     when Id doesn't exist
   * @throws VocCategoryNotFoundException when Category doesn't exist
   */
  void removeVocable(int id, String category)
      throws VocableNotFoundException, VocCategoryNotFoundException;

  /**
   * Edit the name of the category.
   *
   * @param oldCategoryName name from the category
   * @param newCategoryName name of the edited category,
   * @throws VocCategoryNotFoundException if category doesn't exist
   */
  void editVocCategory(String oldCategoryName, String newCategoryName)
      throws VocCategoryNotFoundException;

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
  boolean checkVocable(String word, int id, String category, int levenshteinDistance)
      throws VocCategoryNotFoundException;

  /**
   * Edit a vocab within the list from the category.
   *
   * @param id           position of the vocab in the list
   * @param learningWord string of the origin word
   * @param translations list of translated words
   * @param category     name of the category
   * @throws VocableNotFoundException     when vocableId doesn't exist
   * @throws VocCategoryNotFoundException when categoryId doesn't exist
   */
  void editVocable(int id, String learningWord, String[] translations, String category)
      throws VocableNotFoundException, VocCategoryNotFoundException;

  /**
   * Load data into the vocableTrainer component.
   *
   * @param vocabularyList HashMap of data that should be loaded into the component
   * @return the success of the process
   */
  boolean loadData(HashMap<String, List<Vocable>> vocabularyList);
}