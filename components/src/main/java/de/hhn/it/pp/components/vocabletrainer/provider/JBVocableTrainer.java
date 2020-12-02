package de.hhn.it.pp.components.vocabletrainer.provider;

import de.hhn.it.pp.components.vocabletrainer.Vocable;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryAlreadyExistException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryNotFoundException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocableNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class JBVocableTrainer {
  private HashMap<String, List<Vocable>> trainerData = new HashMap<>();
  private int score;

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public void addCategory(String categoryName, List<Vocable> voc)
      throws VocCategoryAlreadyExistException {
    if (trainerData.get(categoryName) != null) {
      throw new VocCategoryAlreadyExistException("The VocCategory could not be added");
    }
    trainerData.put(categoryName, voc);
  }

  public void deleteCategory(String categoryName) throws VocCategoryNotFoundException {
    if (trainerData.get(categoryName) == null) {
      throw new VocCategoryNotFoundException("The VocCategory could not be deleted");
    }
    trainerData.remove(categoryName);
  }

  public void addVocable(String categoryName, Vocable voc) throws VocCategoryNotFoundException {
    if (trainerData.get(categoryName) == null) {
      throw new VocCategoryNotFoundException();
    }
    trainerData.get(categoryName).add(voc);
  }

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

  public List<Vocable> getVocableList(String categoryName) {
    if (trainerData.get(categoryName) == null) {
      throw new VocCategoryNotFoundException();
    }
    return trainerData.get(categoryName);
  }

  public Set<String> getVocCategories() {
    return trainerData.keySet();
  }
}
