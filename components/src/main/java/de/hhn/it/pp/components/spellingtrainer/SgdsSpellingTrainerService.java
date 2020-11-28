package de.hhn.it.pp.components.spellingtrainer;

import de.hhn.it.pp.components.spellingtrainer.exceptions.LearningSetNameAlreadyAssignedException;
import de.hhn.it.pp.components.spellingtrainer.exceptions.WordAlreadyAddedException;
import de.hhn.it.pp.components.spellingtrainer.exceptions.WordNotFoundException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.ListIterator;

public class SgdsSpellingTrainerService implements SpellingTrainerService{
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SgdsSpellingTrainerService.class);

  /**
   * Checks the spelling of the entered word.
   *
   * @param enteredWord   word, that the user entered
   * @param learningEntry learning entry to be spelled
   * @return Returns true if the spelling of the word was correct, false if the spelling was wrong
   */
  @Override
  public boolean checkSpelling(String enteredWord, LearningEntry learningEntry){
    logger.info("Checked spelling successfully.");
    return enteredWord.equals(learningEntry.getWordEntry());

  }

  /**
   * Adds the given word to the learning set and maps the audio file to the word.
   *
   * @param word            word that will be added
   * @param audio           audio that will be mapped to the word
   * @param learningSetName name of the set, that the word should be added to
   */
  @Override
  public void addWord(String word, File audio, String learningSetName)
      throws WordAlreadyAddedException, FileNotFoundException {
    getLearningSet(learningSetName).addLearningEntry(new LearningEntry(new MediaReference(audio),word));
    logger.info("Word successfully added to learning set {}",learningSetName);

  }

  /**
   * Removes the given word from the Learning List and removes the mapping.
   *
   * @param word            word that is to be removed
   * @param learningSetName name of the set, that the word is deleted from
   */
  @Override
  public void deleteWord(String word, String learningSetName) throws WordNotFoundException {
    LearningSet ls= getLearningSet(learningSetName);
    ListIterator it= ls.getLearningEntries().listIterator();
    while(it.hasNext()){
      LearningEntry le= (LearningEntry) it.next();
      if(le.getWordEntry().equals(word)){
        ls.removeLearningEntry(le);
      }
    }
logger.info("Learning entry successfully removed from {}.",learningSetName);


  }


  /**
   * Method to create a new learning set.
   *
   * @param learningSetName name of the learning set
   * @return new learning set
   */
  @Override
  public void createLearningSet(String learningSetName)
      throws LearningSetNameAlreadyAssignedException {

     SpellingTrainerDescriptor.addLearningSet(new LearningSet(learningSetName));
     logger.info("Learning set successfully created.");
  }

  /**
   * Removes an learning set from the list of learning sets.
   *
   * @param learningSetName name of the learning set to be removed from the list of learning sets
   */
  @Override
  public void removeLearningSet(String learningSetName) {

  }

  /**
   * Method to get all learning sets.
   *
   * @return list of learning sets
   */
  @Override
  public ArrayList<LearningSet> getLearningSets() {
    return null;
  }

  /**
   * Method get a learning set by it's name.
   *
   * @param learningSetName name of the learning set
   * @return learning set with associated name
   */
  @Override
  public LearningSet getLearningSet(String learningSetName) {
    return null;
  }

  /**
   * Method to start an learning session with an learning set.
   *
   * @param learningSetName name of the set to be learned
   * @return true if the learning session started successfully, false if it doesn't
   */
  @Override
  public boolean startLearning(String learningSetName) {
    return false;
  }

  /**
   * Method to stop a learning session.
   *
   * @return true if it was stopped successfully, false when not
   */
  @Override
  public boolean endLearning() {
    return false;
  }

  /**
   * Method to play the next word.
   */
  @Override
  public void nextWord() {

  }

  /**
   * Method to register an media presentation listener.
   *
   * @param mediaPresentationListener media presentation listener to be registered
   */
  @Override
  public void registerMediaPresentationListener(
      MediaPresentationListener mediaPresentationListener) {

  }

  /**
   * Method to deregister and media presentation listener.
   *
   * @param mediaPresentationListener media presentation listener to be deregistered.
   */
  @Override
  public void deregisterMediaPresentationListener(
      MediaPresentationListener mediaPresentationListener) {

  }

}
