package de.hhn.it.pp.components.spellingtrainer.Provider;

import java.util.ArrayList;

/**
 * Class LearningSet creates new learning sets containing learning entries.
 * It provides methods to add and remove learning entries to learning sets.
 */
public class LearningSet {

  private ArrayList<LearningEntry> learningEntries;
  private String learningSetName;

  /**
   * Constructor for an learning set, which contains setting learning set name and
   * creating a new arraylist containing learning entries.
   *
   * @param learningSetName name of the learning set
   */
  public LearningSet(String learningSetName) {
    this.learningEntries = new ArrayList<>();
    this.learningSetName = learningSetName;
  }

  /**
   * Method to add a learning entry to the learning set.
   *
   * @param learningEntry learning entry to be added
   */
  public void addLearningEntry(LearningEntry learningEntry) {
    this.learningEntries.add(learningEntry);
  }

  /**
   * Method to remove an existing learning entry from the learning set.
   *
   * @param learningEntry learning entry to be removed
   */
  public void removeLearningEntry(LearningEntry learningEntry) {
    this.learningEntries.remove(learningEntry);
  }

  /**
   * Method to get next learning entry from set.
   *
   * @param index position of the learning entry
   * @return learning entry
   */
  public LearningEntry getLearningEntry(int index) {
    return this.learningEntries.get(index);
  }

  public ArrayList<LearningEntry> getLearningEntries() {
    return this.learningEntries;
  }

  public String getLearningSetName() {
    return this.learningSetName;
  }
}