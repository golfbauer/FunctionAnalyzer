package de.hhn.it.pp.components.vocabletrainer.provider;

import de.hhn.it.pp.components.vocabletrainer.VocCategory;
import de.hhn.it.pp.components.vocabletrainer.Vocable;
import de.hhn.it.pp.components.vocabletrainer.VocableTrainerService;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryAlreadyExistException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryNotFoundException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocableNotFoundException;
import de.hhn.it.pp.components.vocabletrainer.learningSelection;
import java.util.ArrayList;
import java.util.HashMap;

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
   * Returns all VocCategories.
   *
   * @return List of registered VocCategories
   */
  @Override
  public ArrayList<VocCategory> getVocCategories() {
    return null;
  }

  /**
   * Add a new VocCategory to the VocCategory array.
   *
   * @param category       name of the category
   * @param vocabularyList list of vocabulary
   * @return VocCategory successfully added
   * @throws VocCategoryAlreadyExistException when category name already exist
   */
  @Override
  public boolean addVocCategory(String category, ArrayList<Vocable> vocabularyList)
      throws VocCategoryAlreadyExistException {
    return false;
  }

  /**
   * Remove VocCategory from VocCategory array.
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
   * Returns the Vocable with the id.
   *
   * @param id       number of the vocable
   * @param category name of the category
   * @return Vocable
   * @throws VocableNotFoundException
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
  public ArrayList<Vocable> getVocabulary(String category) throws VocCategoryNotFoundException {
    return null;
  }

  /**
   * Add a new Vocable to the Vocabulary list.
   *
   * @param originWord  the vocabulary to be learned
   * @param foreignWord translation of the vocabulary
   * @param category    name of the category
   * @return Vocable successfully added
   * @throws VocCategoryNotFoundException when CategoryId doesn't exist
   */
  @Override
  public boolean addVocable(String originWord, String foreignWord, String category)
      throws VocCategoryNotFoundException {
    return false;
  }

  /**
   * Remove Vocable from Vocabulary list.
   *
   * @param id       number of the word to be deleted
   * @param category name of the category where the vocable is in
   * @return Vocable successfully removed
   * @throws VocableNotFoundException     when vocableId doesn't exist
   * @throws VocCategoryNotFoundException when CategoryId doesn't exist
   */
  @Override
  public boolean removeVocable(int id, String category)
      throws VocableNotFoundException, VocCategoryNotFoundException {
    return false;
  }

  /**
   * edit a Category
   *
   * @param oldCategoryName name from the category
   * @param newCategoryName Name of the category,
   * @return Category was successfully edited
   * @throws VocCategoryNotFoundException if categoryID doesn't exist
   */
  @Override
  public boolean editVocCategory(String oldCategoryName, String newCategoryName)
      throws VocCategoryNotFoundException {
    return false;
  }

  /**
   * checks if the vocable is correct with the levenshtein distance.
   *
   * @param word                input from the user
   * @param id                  of the vocable
   * @param category            name of the category
   * @param levenshteinDistance deviation from the right word
   * @return word is correct
   * @throws VocCategoryNotFoundException if categoryID doesn't exist
   */
  @Override
  public boolean checkVocable(String word, int id, String category, int levenshteinDistance)
      throws VocCategoryNotFoundException {
    return false;
  }

  /**
   * edit a vocable.
   *
   * @param id           number of the word to be changed
   * @param originWord   string of the origin word
   * @param foreignWords list of translated words
   * @param category     name of the category
   * @return true if the Vocable was successfully added
   * @throws VocableNotFoundException     when vocableId doesn't exist
   * @throws VocCategoryNotFoundException when categoryId doesn't exist
   */
  @Override
  public boolean editVocable(int id, String originWord, String[] foreignWords, String category)
      throws VocableNotFoundException, VocCategoryNotFoundException {
    return false;
  }

  /**
   * start learning the selected vocables
   *
   * @param state state of learning
   * @return true if learning started
   * @throws IllegalStateException voccategory wasn't selected first
   */
  @Override
  public boolean learn(learningSelection[] state) throws IllegalStateException {
    return false;
  }

  /**
   * load data into the vocableTrainer component
   *
   * @param vocabularyList HashMap of data that should be loaded into the component
   * @return the success of the process
   */
  @Override
  public boolean loadData(HashMap<String, ArrayList<Vocable>> vocabularyList) {
    return false;
  }
}