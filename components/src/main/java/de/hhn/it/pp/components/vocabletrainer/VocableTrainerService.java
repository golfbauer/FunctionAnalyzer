package de.hhn.it.pp.components.vocabletrainer;


import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryNotFoundException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocableNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public interface VocableTrainerService {

  /**
   * Returns the current score.
   *
   * @return Score
   */
  int getScore();

  /**
   * Returns all VocCategories.
   *
   * @return List of registered VocCategories
   */
  ArrayList<VocCategory> getVocCategories();

  /**
   * Add a new VocCategory to the VocCategory array.
   *
   * @param category of the category
   * @param vocabularyList list of vocabulary
   * @return VocCategory successfully added
   */
  boolean addVocCategory(String category, ArrayList<Vocable> vocabularyList);

  /**
   * Remove VocCategory from VocCategory array.
   *
   * @param category name of the category
   * @return VocCategory successfully removed
   * @throws VocCategoryNotFoundException when CategoryId doesn't exist
   */
  boolean removeVocCategory(String category) throws VocCategoryNotFoundException;

  /**
   * Returns the Vocable with the id.
   *
   * @param id number of the vocable
   * @param category name of the category
   * @return Vocable
   */
  Vocable getVocable(int id, String category);

  /**
   * Returns all Vocabulary in the category.
   *
   * @param category name of the category whose list is to be output
   * @return List of registered vocabulary
   */
  ArrayList<Vocable> getVocabulary(String category);

  /**
   * Add a new Vocable to the Vocabulary list.
   *
   * @param originWord the vocabulary to be learned
   * @param foreignWord translation of the vocabulary
   * @param category name of the category
   * @return Vocable successfully added
   * @throws VocCategoryNotFoundException when CategoryId doesn't exist
   */
  boolean addVocable(String originWord, String foreignWord, String category)
      throws VocCategoryNotFoundException;

  /**
   * Remove Vocable from Vocabulary list.
   *
   * @param id number of the word to be deleted
   * @param category name of the category where the vocable is in
   * @return Vocable successfully removed
   * @throws VocableNotFoundException when vocableId doesn't exist
   */
  boolean removeVocable(int id, String category) throws VocableNotFoundException;

  /**
   * Skips the current vocable.
   */
  void skip();

  /**
   * edit a Category
   *
   * @param oldCategoryName name from the category
   * @param newCategoryName Name of the category,
   * @return Category was successfully edited
   * @throws VocCategoryNotFoundException if categoryID doesn't exist
   */
  boolean editVocCategory(String oldCategoryName, String newCategoryName) throws VocCategoryNotFoundException;

  /**
   * checks if the vocable is correct.
   *
   * @param word input from the user
   * @return word is correct
   * @throws IllegalStateException if either user, voccategory or learn state wasn't selected first
   */
  boolean isVocableCorrect(String word) throws IllegalStateException;

  /**
   * edit a vocable.
   *
   * @param id number of the word to be changed
   * @param originWord  string of the origin word
   * @param foreignWords list of translated words
   * @param category  name of the category
   * @return true if the Vocable was successfully added
   * @throws VocableNotFoundException     when vocableId doesn't exist
   * @throws VocCategoryNotFoundException when categoryId doesn't exist
   */
  boolean editVocable(int id, String originWord, String[] foreignWords, String category)
      throws VocableNotFoundException, VocCategoryNotFoundException;

  /**
   * start learning the selected vocables
   *
   * @param state state of learning
   * @return true if learning started
   * @throws IllegalStateException voccategory wasn't selected first
   */
  boolean learn(learningSelection[] state) throws IllegalStateException;

  /**
   * load data into the vocabletrainer component
   *
   * @param vocabularyList HashMap of data that should be loaded into the component
   * @return the success of the process
   */
  boolean loadData(HashMap<String, ArrayList<Vocable>> vocabularyList);
}