package de.hhn.it.pp.components.vocabletrainer;

import java.util.ArrayList;

public interface VocableTrainerService {

  /**
   * Returns the currently selected User.
   *
   * @return user object
   */
  User getUser();

  /**
   * Returns a list of users.
   *
   * @return List of registered users
   */
  ArrayList<User> getUsers();

  /**
   * Add a new User to the user list.
   *
   * @param name of the new user
   * @return user successfully added
   */
  boolean addUser(String name);

  /**
   * Remove user from user list.
   *
   * @param userId User
   * @return user successfully removed
   * @throws UserNotFoundExcepiton when CategoryId doesn't exist
   */
  boolean removeUser(int userId) throws UserNotFoundException;

  /**
   * Select a user from the user list.
   *
   * @param userId User
   * @return user successfully selected
   * @throws UserNotFoundExcepiton when CategoryId doesn't exist
   */
  boolean selectUser(int userId) throws UserNotFoundException;

  /**
   * Returns the currently selected VocCategory.
   *
   * @return VocCategory array
   * @throws VocCategoryNotFoundExcepiton when CategoryId doesn't exist
   */
  VocCategory getVocCategory() throws VocCategoryNotFoundException;

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
   * @throws VocCategoryNotFoundExcepiton when CategoryId doesn't exist
   */
  boolean removeVocCategory(int CategoryId) throws VocCategoryNotFoundException;

  /**
   * Select a VocCategory from the VocCategory array.
   *
   * @param CategoryId VocCategory
   * @return VocCategory successfully selected
   * @throws VocCategoryNotFoundExcepiton when CategoryId doesn't exist
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
      throws VocCategoryNotFountException;

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
   * edit a User.
   *
   * @param name   Name of the User,
   * @param userId userId Id from the User
   * @return User was successfully edited
   * @throws UserNotFoundException when userrId doesn't exist
   */
  boolean editUser(int userId, String name) throws UserNotFoundException;

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
   * @throws VocCategoryNotFoundExcepiton when categoryId doesn't exist
   */
  boolean editVocable(int vocableId, String originWord, String foreignWord, int categoryId)
      throws VocableNotFoundException, VocCategoryNotFoundException;

  /**
   * start learning the selected vocables
   *
   * @param state state of learning
   * @return true if learning started
   * @throws IllegalStateException if either user or voccategory wasn't selected first
   */
  boolean learn(learningSelection state) throws IllegalStateException;

}