package de.hhn.it.pp.components.vocabletrainer;


import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryNotFoundException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocableNotFoundException;
import java.util.ArrayList;

public interface VocableTrainerService {

  /**
   * Returns the current score.
   *
   * @return Score
   */
  int getScore();

  /**
   * Returns the currently selected VocCategory.
   *
   * @return VocCategory array
   */
  VocCategory getVocCategory();

  /**
   * Returns all VocCategories.
   *
   * @return List of registered VocCategories
   */
  ArrayList<VocCategory> getVocCategories();

  /**
   * Add a new VocCategory to the VocCategory array.
   *
   * @param name of the category
   * @return VocCategory successfully added
   */
  boolean addVocCategory(String name);

  /**
   * Remove VocCategory from VocCategory array.
   *
   * @param CategoryId VocCategory
   * @return VocCategory successfully removed
   * @throws VocCategoryNotFoundException when CategoryId doesn't exist
   */
  boolean removeVocCategory(int CategoryId) throws VocCategoryNotFoundException;

  /**
   * Select a VocCategory from the VocCategory array.
   *
   * @param CategoryId VocCategory
   * @return VocCategory successfully selected
   * @throws VocCategoryNotFoundException when CategoryId doesn't exist
   */
  boolean selectVocCategory(int CategoryId) throws VocCategoryNotFoundException;

  /**
   * Returns the currently selected Vocable.
   *
   * @return Vocable
   */
  Vocable getVocable();

  /**
   * Returns all Vocabulary.
   *
   * @return List of registered vocabulary
   */
  ArrayList<Vocable> getVocabulary();

  /**
   * Add a new Vocable to the Vocabulary list.
   *
   * @param originWord , foreignWord, CategoryId of the VocCategory
   * @return Vocable successfully added
   * @throws VocCategoryNotFoundException when CategoryId doesn't exist
   */
  boolean addVocable(String originWord, String foreignWord, VocCategory CategoryId)
      throws VocCategoryNotFoundException;

  /**
   * Remove Vocable from Vocabulary list.
   *
   * @param vocableId Vocabulary
   * @return Vocable successfully removed
   * @throws VocableNotFoundException when vocableId doesn't exist
   */
  boolean removeVocable(int vocableId) throws VocableNotFoundException;

  /**
   * Skips the current vocable.
   */
  void skip();

  /**
   * edit a Category
   *
   * @param name       Name of the Category,
   * @param categoryId CategoryId from the Category
   * @return Category was successfully edited
   * @throws VocCategoryNotFoundException if categoryID doesn't exist
   */
  boolean editVocCategory(int categoryId, String name) throws VocCategoryNotFoundException;

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
   * @param categoryId  Id of the category
   * @param foreignWord string of the foreign word
   * @param originWord  string of the origin word
   * @param vocableId   Id from the Vocab
   * @return true if the Vocable was successfully added
   * @throws VocableNotFoundException     when vocableId doesn't exist
   * @throws VocCategoryNotFoundException when categoryId doesn't exist
   */
  boolean editVocable(int vocableId, String originWord, String foreignWord, int categoryId)
      throws VocableNotFoundException, VocCategoryNotFoundException;

  /**
   * start learning the selected vocables
   *
   * @param state state of learning
   * @return true if learning started
   * @throws IllegalStateException voccategory wasn't selected first
   */
  boolean learn(learningSelection[] state) throws IllegalStateException;

}