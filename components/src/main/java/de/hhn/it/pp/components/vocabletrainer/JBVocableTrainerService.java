package de.hhn.it.pp.components.vocabletrainer;

import de.hhn.it.pp.components.exceptions.IllegalParameterException;
import java.util.ArrayList;

public class JBVocableTrainerService implements VocableTrainerService{
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
  public VocCategory[] getVocCategories() {
    return new VocCategory[0];
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
  public boolean selectVocable(int vocableId) throws IllegalParameterException {
    return false;
  }

  @Override
  public String setInput() {
    return null;
  }

  @Override
  public void back() {

  }

  @Override
  public void cancel() {

  }

  @Override
  public void skip() {

  }

  @Override
  public void ok() {

  }

  @Override
  public void learn(String choice) {

  }

  @Override
  public void saveAndNew() throws IllegalStateException {

  }

  @Override
  public void save() throws IllegalStateException {

  }
}
