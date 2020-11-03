package de.hhn.it.pp.components.vocabletrainer;

import java.util.ArrayList;

public interface VocableTrainerService {

  /**
   * Returns the currently selected User.
   *
   * @return user object
   */
  User getUser() throws NullPointerException;

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
  boolean addUser(String name) throws UserNotAddableException;

  /**
   * Remove user from user list.
   *
   * @param userId User
   * @return user successfully removed
   */
  boolean removeUser(int userId) throws UserNotRemovableException, UserNotFoundException;

  /**
   * Select a user from the user list.
   *
   * @param userId User
   * @return user successfully selected
   */
  boolean selectUser(int userId) throws UserNotFoundException;

  /**
   * Returns the currently selected VocCategory.
   *
   * @return VocCategory array
   */
  VocCategory getVocCategory() throws NullPointerException;

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
  boolean addVocCategory(String name) throws VocCategoryNotAddableException;

  /**
   * Remove VocCategory from VocCategory array.
   *
   * @param CategoryId VocCategory
   * @return VocCategory successfully removed
   */
  boolean removeVocCategory(int CategoryId)
      throws VocCategoryNotRemovableException, VocCategoryNotFoundException;

  /**
   * Select a VocCategory from the VocCategory array.
   *
   * @param CategoryId VocCategory
   * @return VocCategory successfully selected
   */
  boolean selectVocCategory(int CategoryId) throws VocCategoryNotFoundException;

  /**
   * Returns the currently selected Vocable.
   *
   * @return Vocable
   */
  Vocable getVocable() throws NullPointerException;

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
   */
  boolean addVocable(String originWord, String foreignWord, VocCategory CategoryId)
      throws VocableNotAddableException;

  /**
   * Remove Vocable from Vocabulary list.
   *
   * @param vocableId Vocabulary
   * @return Vocable successfully removed
   */
  boolean removeVocable(int vocableId) throws VocableNotRemovableExcepion, VocableNotFoundException;

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
   */
  boolean editUser(int userId, String name) throws UserNotFoundException;

  /**
   * edit a Category
   *
   * @param name       Name of the Category,
   * @param categoryId CategoryId from the Category
   * @return Category was successfully edited
   */
  boolean editVocCategory(int categoryId, String name) throws VocCategoryNotFoundException;

  /**
   * checks if the vocable is correct.
   *
   * @param word input from the user
   * @return word is correct
   */
  boolean isVocableCorrect(String word) throws IllegalStateException;

  /**
   * edit a vocable.
   *
   * @param categoryId  Id of the category
   * @param foreignWord string of the foreign word
   * @param originWord  string of the origin word
   * @param vocableId   Id from the Vocab
   * @return
   */
  boolean editVocable(int vocableId, String originWord, String foreignWord, int categoryId)
      throws VocableNotFoundException;

  /**
   * start learning.
   *
   * @param state state of learning
   */
  boolean learn(learningSelection state) throws IllegalStateException;

}