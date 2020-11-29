package de.hhn.it.pp.components.spellingtrainer;

import de.hhn.it.pp.components.spellingtrainer.Provider.LearningEntry;
import de.hhn.it.pp.components.spellingtrainer.Provider.LearningSet;
import de.hhn.it.pp.components.spellingtrainer.Provider.MediaPresentationListener;
import de.hhn.it.pp.components.spellingtrainer.exceptions.LearningSetCouldNotBeFoundException;
import de.hhn.it.pp.components.spellingtrainer.exceptions.LearningSetNameAlreadyAssignedException;
import de.hhn.it.pp.components.spellingtrainer.exceptions.WordAlreadyAddedException;
import de.hhn.it.pp.components.spellingtrainer.exceptions.WordNotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * The SpellingTrainerInterface shows the basic functionalities of the Spellingtrainer.
 */
public interface SpellingTrainerService {

  /**
   * Checks the spelling of the entered word.
   *
   * @param learningEntry learning entry to be spelled
   * @param enteredWord   word, that the user entered
   * @return Returns true if the spelling of the word was correct, false if the spelling was wrong
   */
  boolean checkSpelling(String enteredWord, LearningEntry learningEntry);

  /**
   * Adds the given word to the learning set and maps the audio file to the word.
   *
   * @param word        word that will be added
   * @param audio       audio that will be mapped to the word
   * @param learningSetName name of the set, that the word should be added to
   */
  void addWord(String word, File audio, String learningSetName)
      throws WordAlreadyAddedException, FileNotFoundException, LearningSetCouldNotBeFoundException;

  /**
   * Removes the given word from the Learning List and removes the mapping.
   *
   * @param word        word that is to be removed
   * @param learningSetName name of the set, that the word is deleted from
   */
  void deleteWord(String word, String learningSetName)
      throws WordNotFoundException, LearningSetCouldNotBeFoundException;

  /**
   * Method to create a new learning set.
   *
   * @param learningSetName name of the learning set
   * @return new learning set
   */
  void createLearningSet(String learningSetName)throws LearningSetNameAlreadyAssignedException;

  /**
   * Removes an learning set from the list of learning sets.
   *
   * @param learningSetName name of the learning set to be removed from the list of learning sets
   */
  void removeLearningSet(String learningSetName);

  /**
   * Method to get all learning sets.
   *
   * @return list of learning sets
   */
  ArrayList<LearningSet> getLearningSets();

  /**
   * Method get a learning set by it's name.
   *
   * @param learningSetName name of the learning set
   * @return learning set with associated name
   */
  LearningSet getLearningSet(String learningSetName) throws LearningSetCouldNotBeFoundException;

  /**
   * Method to start an learning session with an learning set.
   *
   * @param learningSetName name of the set to be learned
   * @return true if the learning session started successfully, false if it doesn't
   */
  boolean startLearning(String learningSetName);


  /**
   * Method to stop a learning session.
   *
   * @return true if it was stopped successfully, false when not
   */
  boolean endLearning();

  /**
   * Method to play the next word.
   */
  void nextWord();

  /**
   * Method to register an media presentation listener.
   *
   * @param mediaPresentationListener media presentation listener to be registered
   */
  void registerMediaPresentationListener(MediaPresentationListener mediaPresentationListener);

  /**
   * Method to deregister and media presentation listener.
   *
   * @param mediaPresentationListener media presentation listener to be deregistered.
   */
  void deregisterMediaPresentationListener(MediaPresentationListener mediaPresentationListener);
}
