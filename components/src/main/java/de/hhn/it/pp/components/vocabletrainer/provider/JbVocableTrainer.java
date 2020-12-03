package de.hhn.it.pp.components.vocabletrainer.provider;

import de.hhn.it.pp.components.vocabletrainer.Vocable;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryAlreadyExistException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryNotFoundException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocableNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class JbVocableTrainer {
  private HashMap<String, List<Vocable>> trainerData = new HashMap<>();
  private int score;

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  /**
   * Adds a new Category to the trainer.
   *
   * @param categoryName name of the new Category
   * @param voc a vocab list for the new Category
   * @throws VocCategoryAlreadyExistException when the Category already exist
   */
  public void addCategory(String categoryName, List<Vocable> voc)
      throws VocCategoryAlreadyExistException {
    if (trainerData.containsKey(categoryName)) {
      throw new VocCategoryAlreadyExistException("The VocCategory could not be added");
    }
    trainerData.put(categoryName, voc);
  }

  /**
   * Deletes the Category with the given name.
   * @param categoryName name of the Category which should be delete
   * @throws VocCategoryNotFoundException when Category doesn't exist
   */
  public void deleteCategory(String categoryName) throws VocCategoryNotFoundException {
    if (trainerData.get(categoryName) == null) {
      throw new VocCategoryNotFoundException("The VocCategory could not be deleted");
    }
    trainerData.remove(categoryName);
  }

  /**
   * Adds the Vocable to the Category.
   * @param categoryName name of the category
   * @param voc Vocable which should be added
   * @throws VocCategoryNotFoundException when Category doesn't exist
   */
  public void addVocable(String categoryName, Vocable voc) throws VocCategoryNotFoundException {
    if (trainerData.get(categoryName) == null) {
      throw new VocCategoryNotFoundException();
    }
    trainerData.get(categoryName).add(voc);
  }

  /**
   * Deletes a Vocab from the Category.
   * @param categoryName name of the category
   * @param voc Vocable which should be deleted
   * @throws VocCategoryNotFoundException when Category doesn't exist
   * @throws VocableNotFoundException when Vocable doesn't exist
   */
  public void deleteVocable(String categoryName, Vocable voc)
      throws VocCategoryNotFoundException, VocableNotFoundException {
    if (trainerData.get(categoryName) == null) {
      throw new VocCategoryNotFoundException();
    }
    if (!trainerData.get(categoryName).contains(voc)) {
      throw new VocableNotFoundException(
          "The Vocable was not found in the category " + categoryName);
    }
    trainerData.get(categoryName).remove(voc);
  }

  /**
   * Return every Vocab.
   * @param categoryName name of the Category
   * @return vocabList
   * @throws VocCategoryNotFoundException when Category doesn't exist
   */
  public List<Vocable> getVocableList(String categoryName) throws VocCategoryNotFoundException {
    if (trainerData.get(categoryName) == null) {
      throw new VocCategoryNotFoundException();
    }
    return trainerData.get(categoryName);
  }

  public Set<String> getVocCategories() {
    return trainerData.keySet();
  }
}
