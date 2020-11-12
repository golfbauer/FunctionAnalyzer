package de.hhn.it.pp.components.vocabletrainer.provider;

import de.hhn.it.pp.components.vocabletrainer.VocCategory;
import de.hhn.it.pp.components.vocabletrainer.Vocable;
import de.hhn.it.pp.components.vocabletrainer.VocableTrainerService;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryNotFoundException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocableNotFoundException;
import de.hhn.it.pp.components.vocabletrainer.learningSelection;
import java.util.ArrayList;

public class JBVocableTrainerService implements VocableTrainerService {


  /**
   * Returns the currently selected VocCategory.
   *
   * @return VocCategory array
   */
  @Override
  public VocCategory getVocCategory() {
    return null;
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
   * @param name of the category
   * @return VocCategory successfully added
   */
  @Override
  public boolean addVocCategory(String name) {
    return false;
  }

  /**
   * Remove VocCategory from VocCategory array.
   *
   * @param CategoryId VocCategory
   * @return VocCategory successfully removed
   * @throws VocCategoryNotFoundException when CategoryId doesn't exist
   */
  @Override
  public boolean removeVocCategory(int CategoryId) throws VocCategoryNotFoundException {
    return false;
  }

  /**
   * Select a VocCategory from the VocCategory array.
   *
   * @param CategoryId VocCategory
   * @return VocCategory successfully selected
   * @throws VocCategoryNotFoundException when CategoryId doesn't exist
   */
  @Override
  public boolean selectVocCategory(int CategoryId) throws VocCategoryNotFoundException {
    return false;
  }

  /**
   * Returns the currently selected Vocable.
   *
   * @return Vocable
   */
  @Override
  public Vocable getVocable() {
    return null;
  }

  /**
   * Returns all Vocabulary.
   *
   * @return List of registered vocabulary
   */
  @Override
  public ArrayList<Vocable> getVocabulary() {
    return null;
  }

  /**
   * Add a new Vocable to the Vocabulary list.
   *
   * @param originWord  , foreignWord, CategoryId of the VocCategory
   * @param foreignWord
   * @param CategoryId
   * @return Vocable successfully added
   * @throws VocCategoryNotFoundException when CategoryId doesn't exist
   */
  @Override
  public boolean addVocable(String originWord, String foreignWord, VocCategory CategoryId)
      throws VocCategoryNotFoundException {
    return false;
  }

  /**
   * Remove Vocable from Vocabulary list.
   *
   * @param vocableId Vocabulary
   * @return Vocable successfully removed
   * @throws VocableNotFoundException when vocableId doesn't exist
   */
  @Override
  public boolean removeVocable(int vocableId) throws VocableNotFoundException {
    return false;
  }

  /**
   * Skips the current vocable.
   */
  @Override
  public void skip() {

  }

  /**
   * edit a Category
   *
   * @param categoryId CategoryId from the Category
   * @param name       Name of the Category,
   * @return Category was successfully edited
   * @throws VocCategoryNotFoundException if categoryID doesn't exist
   */
  @Override
  public boolean editVocCategory(int categoryId, String name) throws VocCategoryNotFoundException {
    return false;
  }

  /**
   * checks if the vocable is correct.
   *
   * @param word input from the user
   * @return word is correct
   * @throws IllegalStateException if either user, voccategory or learn state wasn't selected first
   */
  @Override
  public boolean isVocableCorrect(String word) throws IllegalStateException {
    return false;
  }

  /**
   * edit a vocable.
   *
   * @param vocableId   Id from the Vocab
   * @param originWord  string of the origin word
   * @param foreignWord string of the foreign word
   * @param categoryId  Id of the category
   * @return true if the Vocable was successfully added
   * @throws VocableNotFoundException     when vocableId doesn't exist
   * @throws VocCategoryNotFoundException when categoryId doesn't exist
   */
  @Override
  public boolean editVocable(int vocableId, String originWord, String foreignWord, int categoryId)
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
  public boolean learn(learningSelection state) throws IllegalStateException {
    return false;
  }
}