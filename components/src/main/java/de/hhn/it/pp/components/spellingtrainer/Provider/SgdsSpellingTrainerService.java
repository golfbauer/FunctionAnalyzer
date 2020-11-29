package de.hhn.it.pp.components.spellingtrainer.Provider;

import de.hhn.it.pp.components.spellingtrainer.SpellingTrainerDescriptor;
import de.hhn.it.pp.components.spellingtrainer.SpellingTrainerService;
import de.hhn.it.pp.components.spellingtrainer.exceptions.LearningSetCouldNotBeFoundException;
import de.hhn.it.pp.components.spellingtrainer.exceptions.LearningSetNameAlreadyAssignedException;
import de.hhn.it.pp.components.spellingtrainer.exceptions.WordAlreadyAddedException;
import de.hhn.it.pp.components.spellingtrainer.exceptions.WordNotFoundException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.ListIterator;

public class SgdsSpellingTrainerService implements SpellingTrainerService {
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
      throws WordAlreadyAddedException, FileNotFoundException, LearningSetCouldNotBeFoundException {
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
  public void deleteWord(String word, String learningSetName)
      throws WordNotFoundException, LearningSetCouldNotBeFoundException {
    LearningSet ls= getLearningSet(learningSetName);
    ListIterator it= ls.getLearningEntries().listIterator();
    while(it.hasNext()){
      LearningEntry le= (LearningEntry) it.next();
      if(le.getWordEntry().equals(word)){
        ls.removeLearningEntry(le);
        logger.info("Learning entry successfully removed from {}.",learningSetName);
        return;
      }

    }
    throw new WordNotFoundException("Word couldn't be found in the learning set "+ learningSetName);


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
    ArrayList<LearningSet> learningSets = getLearningSets();
    ListIterator it= learningSets.listIterator();

    while(it.hasNext()){
      LearningSet ls = (LearningSet) it.next();
      if(ls.getLearningSetName().equals(learningSetName)){
        SpellingTrainerDescriptor.removeLearningSet(ls);
      }
      logger.info("Learning set successfully removed.");


  }

  /**
   * Method to get all learning sets.
   *
   * @return list of learning sets
   */
  @Override
  public ArrayList<LearningSet> getLearningSets() {
    return SpellingTrainerDescriptor.getLearningSets();
  }

  /**
   * Method get a learning set by it's name.
   *
   * @param learningSetName name of the learning set
   * @return learning set with associated name
   */
  @Override
  public LearningSet getLearningSet(String learningSetName)
      throws LearningSetCouldNotBeFoundException {
    ArrayList<LearningSet> learningSets = getLearningSets();

    for (int i = 0; i < learningSets.size(); i++) {
      if (learningSets.get(i).getLearningSetName().equals(learningSetName)) {
        return learningSets.get(i);
      }
    }
    logger.warn("LearningSet with given name " + learningSetName + " could not be found");
    throw new LearningSetCouldNotBeFoundException("LearningSet with name " + learningSetName + " could not be found");
  }


  /**
   * Method to start an learning session with an learning set.
   *
   * @param learningSetName name of the set to be learned
   * @return true if the learning session started successfully, false if it doesn't
   */
  @Override
  public boolean startLearning(String learningSetName) throws LearningSetCouldNotBeFoundException {
    SpellingTrainerDescriptor.resetInts();
    SpellingTrainerDescriptor.setActiveLearningSet(getLearningSet(learningSetName));
    return true;
  }

  /**
   * Method to stop a learning session.
   *
   * @return true if it was stopped successfully, false when not
   */
  @Override
  public boolean endLearning() {
    SpellingTrainerDescriptor.setActiveLearningSet(null);
    return true;
  }

  /**
   * Method to play the next word.
   */
  @Override
  public void nextWord() {
    MediaPresentationListener mpl = MediaPresentationListener.getMediaPresentationListener(0);
    MediaReference mr = SpellingTrainerDescriptor.getActiveLearningSet().getLearningEntry(SpellingTrainerDescriptor.getCurrentWordIndex()).getMediaReference();
    mpl.present(mr);
  }

  /**
   * Method to register an media presentation listener.
   *
   * @param mediaPresentationListener media presentation listener to be registered
   */
  @Override
  public void registerMediaPresentationListener(
      MediaPresentationListener mediaPresentationListener) {
      MediaPresentationListener mpl = new MediaPresentationListener();
      MediaPresentationListener.addMediaPresentationListener(mpl);
  }

  /**
   * Method to deregister and media presentation listener.
   *
   * @param mediaPresentationListener media presentation listener to be deregistered.
   */
  @Override
  public void deregisterMediaPresentationListener(
      MediaPresentationListener mediaPresentationListener) {
      MediaPresentationListener.removeMediaPresentationListener(mediaPresentationListener);
  }

}
