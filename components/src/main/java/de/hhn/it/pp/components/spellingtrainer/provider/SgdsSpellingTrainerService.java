package de.hhn.it.pp.components.spellingtrainer.provider;

import de.hhn.it.pp.components.spellingtrainer.SpellingTrainerDescriptor;
import de.hhn.it.pp.components.spellingtrainer.SpellingTrainerService;
import de.hhn.it.pp.components.spellingtrainer.exceptions.LearningSetCouldNotBeFoundException;
import de.hhn.it.pp.components.spellingtrainer.exceptions.LearningSetNameAlreadyAssignedException;
import de.hhn.it.pp.components.spellingtrainer.exceptions.NoWordException;
import de.hhn.it.pp.components.spellingtrainer.exceptions.WordAlreadyAddedException;
import de.hhn.it.pp.components.spellingtrainer.exceptions.WordNotFoundException;
import java.util.ArrayList;


public class SgdsSpellingTrainerService implements SpellingTrainerService {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SgdsSpellingTrainerService.class);

  private SpellingTrainerDescriptor descriptor;
  private ArrayList<MediaPresentationListener> mplisteners;

  /**
   * Constructor that initializes the whole spellingtrainer service implementation.
   *
   * @throws LearningSetNameAlreadyAssignedException is thrown, when the learning set name ist already assigned
   * @throws LearningSetCouldNotBeFoundException     is thrown, when the learning set couldn't be found
   * @throws WordAlreadyAddedException               is thrown, when the referenced word is already added
   */
  public SgdsSpellingTrainerService()
      throws LearningSetNameAlreadyAssignedException, LearningSetCouldNotBeFoundException,
      WordAlreadyAddedException {
    logger.info("Constructor from class SgdsSpellingTrainerService successfully run.");
    mplisteners = new ArrayList<>();
    descriptor = new SpellingTrainerDescriptor();
    createLearningSet("GermanLearningSet");
    createLearningSet("EnglishLearningSet");
    addWord("book",
        "/audiofiles/englishwords/Book.wav",
        "EnglishLearningSet");
    addWord("brain",
        "/audiofiles/englishwords/Brain.wav",
        "EnglishLearningSet");
    addWord("computer",
        "/audiofiles/englishwords/Computer.wav",
        "EnglishLearningSet");
    addWord("engineering",
        "/audiofiles/englishwords/Engineering.wav",
        "EnglishLearningSet");
    addWord("software",
        "/audiofiles/englishwords/Software.wav",
        "EnglishLearningSet");
    addWord("Buch",
        "/audiofiles/germanwords/Buch.wav",
        "GermanLearningSet");
    addWord("Gehirn",
        "/audiofiles/germanwords/Gehirn.wav",
        "GermanLearningSet");
    addWord("Computer",
        "/audiofiles/germanwords/Computer.wav",
        "GermanLearningSet");
    addWord("Ingenieur",
        "/audiofiles/germanwords/Ingenieur.wav",
        "GermanLearningSet");
    addWord("Software",
        "/audiofiles/germanwords/Software.wav",
        "GermanLearningSet");

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
  public void addWord(String word, String audio, String learningSetName)
      throws WordAlreadyAddedException, LearningSetCouldNotBeFoundException {
    logger.info("Word successfully added to learning set {}", learningSetName);
    LearningSet ls = getLearningSet(learningSetName);
    if (ls.getLearningEntries().size() > 0) {
      for (LearningEntry entry : ls.getLearningEntries()) {
        if (entry.getWordEntry().equals(word)) {
          throw new WordAlreadyAddedException("Word is already added.");
        }
      }
    }
    getLearningSet(learningSetName)
        .addLearningEntry(new LearningEntry(new MediaReference(audio), word));
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
    logger.info("Learning entry successfully removed from {}.", learningSetName);
    LearningSet ls = getLearningSet(learningSetName);
    for (LearningEntry le : ls.getLearningEntries()) {
      if (le.getWordEntry().equals(word)) {
        ls.removeLearningEntry(le);
        return;
      }
    }
    throw new WordNotFoundException(
        "Word couldn't be found in the learning set " + learningSetName);
  }


  /**
   * Method to create a new learning set.
   *
   * @param learningSetName name of the learning set
   */
  @Override
  public void createLearningSet(String learningSetName)
      throws LearningSetNameAlreadyAssignedException, LearningSetCouldNotBeFoundException {
    logger.info("Learning set successfully created.");
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
  }

  /**
   * Removes an learning set from the list of learning sets.
   *
   * @param learningSetName name of the learning set to be removed from the list of learning sets
   */
  @Override
  public void removeLearningSet(String learningSetName) throws LearningSetCouldNotBeFoundException {
    logger.info("Learning set {} successfully removed.", learningSetName);
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
    logger.info("Successfully returned the LearningSet with the name " + learningSetName);
    ArrayList<LearningSet> learningSets = getLearningSets();
    for (LearningSet learningSet : learningSets) {
      if (learningSet.getLearningSetName().equals(learningSetName)) {
        return learningSet;
      }
    }
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
  public boolean startLearning(String learningSetName)
      throws LearningSetCouldNotBeFoundException, NoWordException {
    logger.info("Successfully set all the necessary start variables");
    if (!descriptor.getIsLearning()) {
      descriptor.setActiveLearningSet(getLearningSet(learningSetName));
      descriptor.resetInts();
      descriptor
          .updateCounter("remaining", getLearningSet(learningSetName).getLearningEntries().size());
      descriptor.setIsLearning(true);
      return true;
    }
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
    logger.info(
        "The activelearningset has a next word and the currentwordindex has been incremented");
    if (descriptor.getCurrentWordIndex() + 1
        < descriptor.getActiveLearningSet().getLearningEntries().size()) {
      descriptor
          .setCurrentWordIndex(descriptor.getCurrentWordIndex() + 1);

      return true;
    }
    return false;
  }

  /**
   * Method that returns the current word in the active learningset.
   *
   * @return the current word
   */
  @Override
  public String currentWord() {
    logger.info("Current word successfully returned");
    return descriptor.getActiveLearningSet()
        .getLearningEntry(descriptor.getCurrentWordIndex()).getWordEntry();
  }

  /**
   * Method to play the current word.
   */
  @Override
  public void playWord() {
    logger.info("Successfully presented the audio from the current Word in the learningSet");
    MediaPresentationListener mpl = this.getMediaPresentationListeners().get(0);
    MediaReference mr = descriptor.getActiveLearningSet()
        .getLearningEntry(descriptor.getCurrentWordIndex()).getMediaReference();
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
    logger.info("Successfully registered a new MediaPresentationListener");
    this.mplisteners.add(mediaPresentationListener);

  }

  /**
   * Method to deregister and media presentation listener.
   *
   * @param index position of the media presentation listener to be removed
   */
  @Override
  public void deregisterMediaPresentationListener(int index) {
    logger.info("Successfully deregistered the MediaPresentationListener");
    this.mplisteners.remove(index);

  }

  @Override
  public SpellingTrainerDescriptor getDescriptor() {
    logger.info("Successfully returned the descriptor.");
    return this.descriptor;
  }

  public ArrayList<MediaPresentationListener> getMediaPresentationListeners() {
    logger.info("Successfully returned all media presentation listeners.");
    return this.mplisteners;
  }
}