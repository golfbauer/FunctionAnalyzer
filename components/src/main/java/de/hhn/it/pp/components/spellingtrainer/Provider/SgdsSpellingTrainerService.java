package de.hhn.it.pp.components.spellingtrainer.Provider;

import de.hhn.it.pp.components.spellingtrainer.SpellingTrainerDescriptor;
import de.hhn.it.pp.components.spellingtrainer.SpellingTrainerService;
import de.hhn.it.pp.components.spellingtrainer.exceptions.LearningSetCouldNotBeFoundException;
import de.hhn.it.pp.components.spellingtrainer.exceptions.LearningSetNameAlreadyAssignedException;
import de.hhn.it.pp.components.spellingtrainer.exceptions.WordAlreadyAddedException;
import de.hhn.it.pp.components.spellingtrainer.exceptions.WordNotFoundException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SgdsSpellingTrainerService implements SpellingTrainerService {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SgdsSpellingTrainerService.class);

  SpellingTrainerDescriptor descriptor;

  public SgdsSpellingTrainerService() {
    descriptor = new SpellingTrainerDescriptor();
    logger.info("Constructor from class SgdsSpellingTrainerService successfully run.");

  }

  /**
   * Checks the spelling of the entered word.
   *
   * @param enteredWord word, that the user entered
   * @return Returns true if the spelling of the word was correct, false if the spelling was wrong
   */
  @Override
  public boolean checkSpelling(String enteredWord) {
    logger.info("Checked spelling successfully.");
    return enteredWord.equals(currentWord());
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
      throws WordAlreadyAddedException, LearningSetCouldNotBeFoundException {
    LearningSet ls = getLearningSet(learningSetName);
    if (ls.getLearningEntries().size() > 0) {
      for (LearningEntry entry : ls.getLearningEntries()) {
        if (entry.getWordEntry().equals(word)) {
          logger.warn("Word entry is already existing!");
          throw new WordAlreadyAddedException("Word is already added.");
        }
      }
    }
    getLearningSet(learningSetName)
        .addLearningEntry(new LearningEntry(new MediaReference(audio), word));
    logger.info("Word successfully added to learning set {}", learningSetName);
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
    LearningSet ls = getLearningSet(learningSetName);
    for (LearningEntry le : ls.getLearningEntries()) {
      if (le.getWordEntry().equals(word)) {
        ls.removeLearningEntry(le);
        logger.info("Learning entry successfully removed from {}.", learningSetName);
        return;
      }

    }
    logger.warn("Could not delete the referenced word.");
    throw new WordNotFoundException(
        "Word couldn't be found in the learning set " + learningSetName);
  }


  /**
   * Method to create a new learning set.
   *
   * @param learningSetName name of the learning set
   * @return new learning set
   */
  @Override
  public void createLearningSet(String learningSetName)
      throws LearningSetNameAlreadyAssignedException, LearningSetCouldNotBeFoundException {
    ArrayList<LearningSet> ls = getLearningSets();
    if (ls.size() > 0) {
      for (LearningSet learningSet : ls) {
        if (learningSet.getLearningSetName().equals(learningSetName)) {
          throw new LearningSetNameAlreadyAssignedException(
              "This learning set name is already taken!");
        }
      }
    }
    descriptor.addLearningSet(new LearningSet(learningSetName));
    logger.info("Learning set successfully created.");

  }

  /**
   * Removes an learning set from the list of learning sets.
   *
   * @param learningSetName name of the learning set to be removed from the list of learning sets
   */
  @Override
  public void removeLearningSet(String learningSetName) throws LearningSetCouldNotBeFoundException {
    descriptor.removeLearningSet(getLearningSet(learningSetName));

  }

  /**
   * Method to get all learning sets.
   *
   * @return list of learning sets
   */
  @Override

  public ArrayList<LearningSet> getLearningSets() {
    logger.info("Successfully returned all learning sets");
    return descriptor.getLearningSets();
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

    for (LearningSet learningSet : learningSets) {
      if (learningSet.getLearningSetName().equals(learningSetName)) {
        logger.info("Successfully returned the LearningSet with the name " + learningSetName);
        return learningSet;
      }
    }
    logger.warn("LearningSet with given name " + learningSetName + " could not be found");
    throw new LearningSetCouldNotBeFoundException(
        "LearningSet with name " + learningSetName + " could not be found");
  }


  /**
   * Method to start an learning session with an learning set.
   *
   * @param learningSetName name of the set to be learned
   * @return true if the learning session started successfully, false if it doesn't
   */
  @Override
  public boolean startLearning(String learningSetName) throws LearningSetCouldNotBeFoundException {
    if (!descriptor.getIsLearning()) {
      descriptor.resetInts();
      descriptor.setActiveLearningSet(getLearningSet(learningSetName));
      descriptor.setIsLearning(true);
      logger.info("Successfully set all the necessary start variables");
      return true;
    }
    logger.info("Learning already started!");
    return false;
  }

  /**
   * Method to stop a learning session.
   *
   * @return true if it was stopped successfully, false when not
   */
  @Override
  public boolean endLearning() {
    if (descriptor.getIsLearning()) {
      descriptor.setActiveLearningSet(null);
      descriptor.setIsLearning(false);
      logger.info("Successfully removed the ActiveLearningSet, stopping the Learningtask");
      return true;
    }
    logger.info("Learning could not be stopped because there is no active learning!");
    return false;
  }

  /**
   * Method that returns the next word in the active learningset.
   *
   * @return the next word
   */
  @Override
  public boolean hasNextWord() {
    descriptor
        .setCurrentWordIndex(descriptor.getCurrentWordIndex() + 1);

    if (descriptor.getCurrentWordIndex() <
        descriptor.getActiveLearningSet().getLearningEntries().size()) {
      return true;
    }
    logger.warn("No next word found");
    return false;
  }

  /**
   * Method that returns the current word in the active learningset.
   *
   * @return the current word
   */
  @Override
  public String currentWord() {
    return descriptor.getActiveLearningSet()
        .getLearningEntry(descriptor.getCurrentWordIndex()).getWordEntry();
  }


  /**
   * Method to play the current word.
   */
  @Override
  public void playWord()
      throws UnsupportedAudioFileException, IOException, LineUnavailableException {
    MediaPresentationListener mpl = MediaPresentationListener.getMediaPresentationListener(0);
    MediaReference mr = descriptor.getActiveLearningSet()
        .getLearningEntry(descriptor.getCurrentWordIndex()).getMediaReference();
    mpl.present(mr);
    logger.info("Successfully presented the audio from the current Word in the learningSet");
  }

  /**
   * Method to register an media presentation listener.
   *
   * @param mediaPresentationListener media presentation listener to be registered
   */
  @Override
  public void registerMediaPresentationListener(
      MediaPresentationListener mediaPresentationListener) {
    MediaPresentationListener.addMediaPresentationListener(mediaPresentationListener);
    logger.info("Successfully registered a new MediaPresentationListener");
  }

  /**
   * Method to register an media presentation listener.
   */
  public void registerMediaPresentationListener() {
    MediaPresentationListener mpl = new MediaPresentationListener();
    MediaPresentationListener.addMediaPresentationListener(mpl);
    logger.info("Successfully registered a new MediaPresentationListener");
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
    logger.info("Successfully deregistered the MediaPresentationListener");
  }

  @Override
  public SpellingTrainerDescriptor getDescriptor() {
    return this.descriptor;
  }

}

