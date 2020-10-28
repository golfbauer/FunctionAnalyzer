package de.hhn.it.pp.components.vocabletrainer;

import de.hhn.it.pp.components.exceptions.IllegalParameterException;
import java.util.ArrayList;

public class JBVocableTrainerService implements VocableTrainerService {

  @Override
  public User getUser() {
    return null;
  }

  @Override
  public ArrayList<User> getUsers() {
    return null;
  }

  @Override
  public boolean addUser(String name) throws IllegalStateException {
    return false;
  }

  @Override
  public boolean removeUser(int userId) throws IllegalParameterException {
    return false;
  }

  @Override
  public boolean selectUser(int userId) throws IllegalParameterException {
    return false;
  }

  @Override
  public VocCategory getVocCategory() {
    return null;
  }

  @Override
  public ArrayList<VocCategory> getVocCategories() {
    return null;
  }

  @Override
  public boolean addVocCategory(String name) throws IllegalStateException {
    return false;
  }

  @Override
  public boolean removeVocCategory(int CategoryId) throws IllegalParameterException {
    return false;
  }

  @Override
  public boolean selectVocCategory(int CategoryId) throws IllegalParameterException {
    return false;
  }

  @Override
  public Vocable getVocable() {
    return null;
  }

  @Override
  public ArrayList<Vocable> getVocabulary() {
    return null;
  }

  @Override
  public boolean addVocable(String originWord, String foreignWord, VocCategory CategoryId)
      throws IllegalParameterException, IllegalStateException {
    return false;
  }

  @Override
  public boolean removeVocable(int vocableId) throws IllegalParameterException {
    return false;
  }

  @Override
  public void skip() {

  }

  @Override
  public boolean editUser(int userId, String name) {
    return false;
  }

  @Override
  public boolean editVocCategory(int categoryId, String name) {
    return false;
  }

  @Override
  public boolean isVocableCorrect(String word) {
    return false;
  }

  @Override
  public boolean editVocable(int vocableId, String originWord, String foreignWord, int categoryId) {
    return false;
  }

  @Override
  public boolean learn(learningSelection state) {
    return false;
  }
}