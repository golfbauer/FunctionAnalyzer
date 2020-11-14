package de.hhn.it.pp.components.vocabletrainer;

import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryAlreadyExistException;
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
  ArrayList<String> getVocCategories();

  /**
   * Add a new VocCategory to the VocCategory array.
   *
   * @param category       name of the category
   * @param vocabularyList list of vocabulary
   * @return VocCategory successfully added
   * @throws VocCategoryAlreadyExistException when category name already exist
   */
  boolean addVocCategory(String category, ArrayList<Vocable> vocabularyList)
      throws VocCategoryAlreadyExistException;

  /**
   * Remove VocCategory from VocCategory array.
   *
   * @param category name of the category
   * @return VocCategory successfully removed
   * @throws VocCategoryNotFoundException when category name doesn't exist
   */
  boolean removeVocCategory(String category) throws VocCategoryNotFoundException;

  /**
   * Returns the Vocable with the id.
   *
   * @param id       number of the vocable
   * @param category name of the category
   * @return Vocable
   * @throws VocableNotFoundException
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
  ArrayList<Vocable> getVocabulary(String category) throws VocCategoryNotFoundException;

  /**
   * Add a new Vocable to the Vocabulary list.
   *
   * @param originWord  the vocabulary to be learned
   * @param foreignWord translation of the vocabulary
   * @param category    name of the category
   * @return Vocable successfully added
   * @throws VocCategoryNotFoundException when CategoryId doesn't exist
   */
  boolean addVocable(String originWord, String foreignWord, String category)
      throws VocCategoryNotFoundException;

  /**
   * Remove Vocable from Vocabulary list.
   *
   * @param id       number of the word to be deleted
   * @param category name of the category where the vocable is in
   * @return Vocable successfully removed
   * @throws VocableNotFoundException     when vocableId doesn't exist
   * @throws VocCategoryNotFoundException when CategoryId doesn't exist
   */
  boolean removeVocable(int id, String category)
      throws VocableNotFoundException, VocCategoryNotFoundException;

  /**
   * edit a Category
   *
   * @param oldCategoryName name from the category
   * @param newCategoryName Name of the category,
   * @return Category was successfully edited
   * @throws VocCategoryNotFoundException if categoryID doesn't exist
   */
  boolean editVocCategory(String oldCategoryName, String newCategoryName)
      throws VocCategoryNotFoundException;

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
  boolean checkVocable(String word, int id, String category, int levenshteinDistance)
      throws VocCategoryNotFoundException;

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
  boolean editVocable(int id, String originWord, String[] foreignWords, String category)
      throws VocableNotFoundException, VocCategoryNotFoundException;

  /**
   * load data into the vocableTrainer component
   *
   * @param vocabularyList HashMap of data that should be loaded into the component
   * @return the success of the process
   */
  boolean loadData(HashMap<String, ArrayList<Vocable>> vocabularyList);
}