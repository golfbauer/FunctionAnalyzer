package de.hhn.it.pp.components.vocabletrainer.provider;

import de.hhn.it.pp.components.vocabletrainer.Vocable;
import de.hhn.it.pp.components.vocabletrainer.VocableTrainerService;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryAlreadyExistException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryNotFoundException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocableNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JBVocableTrainerService implements VocableTrainerService {


  /**
   * Returns the current score.
   *
   * @return Score
   */
  @Override
  public int getScore() {
    return 0;
  }

  /**
   * Returns all Categories saved in the Hashmap.
   *
   * @return List of registered Categories
   */
  @Override
  public List<String> getVocCategories() {
    return null;
  }

  /**
   * Add a new Category with a new vocabularyList to the hashmap.
   *
   * @param category       name of the category
   * @param vocabularyList list of vocabulary
   * @return VocCategory successfully added
   * @throws VocCategoryAlreadyExistException when category name already exist
   */
  @Override
  public boolean addVocCategory(String category, List<Vocable> vocabularyList)
      throws VocCategoryAlreadyExistException {
    return false;
  }

  /**
   * Remove VocCategory from the hashmap.
   *
   * @param category name of the category
   * @return VocCategory successfully removed
   * @throws VocCategoryNotFoundException when category name doesn't exist
   */
  @Override
  public boolean removeVocCategory(String category) throws VocCategoryNotFoundException {
    return false;
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
    return null;
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
    return null;
  }

  /**
   * Add a new Vocable to the Vocabulary list from the given category.
   *
   * @param learningWord the vocabulary to be learned
   * @param translations translations of the vocabulary
   * @param category     name of the category
   * @return Vocable successfully added
   * @throws VocCategoryNotFoundException when CategoryId doesn't exist
   */
  @Override
  public boolean addVocable(String learningWord, String[] translations, String category)
      throws VocCategoryNotFoundException {
    return false;
  }

  /**
   * Remove Vocable from Vocabulary list from the category.
   *
   * @param id       position of the word in the list
   * @param category name of the category where the vocable is in
   * @return Vocable successfully removed
   * @throws VocableNotFoundException     when Id doesn't exist
   * @throws VocCategoryNotFoundException when Category doesn't exist
   */
  @Override
  public boolean removeVocable(int id, String category)
      throws VocableNotFoundException, VocCategoryNotFoundException {
    return false;
  }

  /**
   * Edit the name of the category.
   *
   * @param oldCategoryName name from the category
   * @param newCategoryName name of the edited category,
   * @return Category was successfully edited
   * @throws VocCategoryNotFoundException if category doesn't exist
   */
  @Override
  public boolean editVocCategory(String oldCategoryName, String newCategoryName)
      throws VocCategoryNotFoundException {
    return false;
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
    return false;
  }

  /**
   * Edit a vocab within the list from the category.
   *
   * @param id           position of the vocab in the list
   * @param learningWord string of the origin word
   * @param translations list of translated words
   * @param category     name of the category
   * @return true if the Vocable was successfully added
   * @throws VocableNotFoundException     when vocableId doesn't exist
   * @throws VocCategoryNotFoundException when categoryId doesn't exist
   */
  @Override
  public boolean editVocable(int id, String learningWord, String[] translations, String category)
      throws VocableNotFoundException, VocCategoryNotFoundException {
    return false;
  }

  /**
   * Load data into the vocableTrainer component.
   *
   * @param vocabularyList HashMap of data that should be loaded into the component
   * @return the success of the process
   */
  @Override
  public boolean loadData(HashMap<String, List<Vocable>> vocabularyList) {
    return false;
  }
}