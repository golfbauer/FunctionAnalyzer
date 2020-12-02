package de.hhn.it.pp.components.vocabletrainer.provider;


import de.hhn.it.pp.components.vocabletrainer.Vocable;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryAlreadyExistException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class JBVocableTrainer {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(JBVocableTrainer.class);

  private HashMap<String, List<Vocable>> trainerData = new HashMap<>();
  private int score;

  public void setScore(int score) {
    this.score = score;
  }

  public int getScore() {
    return score;
  }

  public void addCategory(String categoryName, List<Vocable> voc) throws VocCategoryAlreadyExistException {
    trainerData.put(categoryName, voc);
  }

  public void addCategory(String categoryName) {
    List<Vocable> voc = new ArrayList<>();
    trainerData.put(categoryName, voc);
  }

  public void deleteCategory(String categoryName) {
    trainerData.remove(categoryName);
  }

  public void addVocable(String categoryName, Vocable voc) {
    trainerData.get(categoryName).add(voc);
  }

  public void deleteVocable(String categoryName, Vocable voc) {
    trainerData.get(categoryName).remove(voc);
  }

  public List<Vocable> getVocableList(String categoryName) {
    return trainerData.get(categoryName);
  }

  public Set<String> getVocCategories(){
    return trainerData.keySet();
  }
}
