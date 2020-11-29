package de.hhn.it.pp.components.spellingtrainer;

import de.hhn.it.pp.components.spellingtrainer.Provider.LearningEntry;
import de.hhn.it.pp.components.spellingtrainer.Provider.LearningSet;
import de.hhn.it.pp.components.spellingtrainer.Provider.MediaPresentationListener;
import de.hhn.it.pp.components.spellingtrainer.exceptions.LearningSetNameAlreadyAssignedException;
import de.hhn.it.pp.components.spellingtrainer.exceptions.WordAlreadyAddedException;
import de.hhn.it.pp.components.spellingtrainer.exceptions.WordNotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class DemoSpellingTrainerUsage {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(DemoSpellingTrainerUsage.class);

  public static void main(String[] args)
      throws FileNotFoundException, WordAlreadyAddedException, WordNotFoundException {
    //Nur für den Test
    SpellingTrainerService service = new SpellingTrainerService() {
      @Override
      public boolean checkSpelling(String enteredWord, LearningEntry learningEntry) {
        return false;
      }

      @Override
      public void addWord(String word, File audio, String learningSetName)
          throws WordAlreadyAddedException, FileNotFoundException {

      }

      @Override
      public void deleteWord(String word, String learningSetName) throws WordNotFoundException {

      }

      @Override
      public void createLearningSet(String learningSetName)
          throws LearningSetNameAlreadyAssignedException {
      }

      @Override
      public void removeLearningSet(String learningSetName) {

      }

      @Override
      public ArrayList<LearningSet> getLearningSets() {
        return null;
      }

      @Override
      public LearningSet getLearningSet(String learningSetName) {
        return null;
      }

      @Override
      public boolean startLearning(String learningSetName) {
        return false;
      }

      @Override
      public boolean endLearning() {
        return false;
      }

      @Override
      public void nextWord() {

      }

      @Override
      public void registerMediaPresentationListener(
          MediaPresentationListener mediaPresentationListener) {

      }

      @Override
      public void deregisterMediaPresentationListener(
          MediaPresentationListener mediaPresentationListener) {

      }
    };
    SpellingTrainerDescriptor descriptor = new SpellingTrainerDescriptor();

    //Not executed during runtime
    String word = "test";
    File audioFile = new File("test.mp3");
    LearningSet learningSet = new LearningSet("Test Set ");
    service.addWord(word, audioFile, "Test Set");
    service.deleteWord(word, "Test Set");
    MediaPresentationListener mpl = new MediaPresentationListener();
    service.registerMediaPresentationListener(mpl);
    //Selecting the active learning set
    descriptor.setActiveLearningSet(learningSet);

    //Starting learning session
    service.startLearning("Test Set");


    //Repeat execution for each word
    LearningEntry learningEntry = descriptor.getActiveLearningSet().getLearningEntry(0);
    service.nextWord();
    mpl.present(learningEntry.getMediaReference());
    //User Input via JavaFX
    //Check spelling of the entered word
  }
}

