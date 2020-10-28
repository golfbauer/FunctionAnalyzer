package de.hhn.it.pp.components.vocabletrainer;

import de.hhn.it.pp.components.exceptions.IllegalParameterException;

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
   * Add a new User to the user list
   *
   * @param name of the new user
   * @return user successfully added
   */
  boolean addUser(String name) throws IllegalStateException;

  /**
   * Remove user from user list
   *
   * @param userId User
   * @return user successfully removed
   */
  boolean removeUser(int userId) throws IllegalParameterException;

  /**
   * Select a user from the user list
   *
   * @param userId User
   * @return user successfully selected
   */
  boolean selectUser(int userId) throws IllegalParameterException;

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
   * Add a new VocCategory to the VocCategory array
   *
   * @param name of the category
   * @return VocCategory successfully added
   */
  boolean addVocCategory(String name) throws IllegalStateException;

  /**
   * Remove VocCategory from VocCategory array
   *
   * @param CategoryId VocCategory
   * @return VocCategory successfully removed
   */
  boolean removeVocCategory(int CategoryId) throws IllegalParameterException;

  /**
   * Select a VocCategory from the VocCategory array
   *
   * @param CategoryId VocCategory
   * @return VocCategory successfully selected
   */
  boolean selectVocCategory(int CategoryId) throws IllegalParameterException;

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
   * Add a new Vocable to the Vocabulary list
   *
   * @param originWord , foreignWord, CategoryId of the VocCategory
   * @return Vocable successfully added
   */
  boolean addVocable(String originWord, String foreignWord, VocCategory CategoryId)
      throws IllegalParameterException, IllegalStateException;

  /**
   * Remove Vocable from Vocabulary list
   *
   * @param vocableId Vocabulary
   * @return Vocable successfully removed
   */
  boolean removeVocable(int vocableId) throws IllegalParameterException;

  /**
   * Select a VocCategory from the VocCategory array
   *
   * @param vocableId Vocabulary
   * @return Vocable successfully selected
   */
  boolean selectVocable(int vocableId) throws IllegalParameterException;

  /**
   * Skips the current vocable
   */
  void skip();
}