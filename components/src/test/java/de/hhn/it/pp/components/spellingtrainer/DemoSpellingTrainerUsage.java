package de.hhn.it.pp.components.spellingtrainer;

import de.hhn.it.pp.components.spellingtrainer.exceptions.CounterNotFoundException;
import de.hhn.it.pp.components.spellingtrainer.exceptions.WordAlreadyAddedException;
import de.hhn.it.pp.components.spellingtrainer.exceptions.WordNotFoundException;

import java.io.File;
import java.io.FileNotFoundException;

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
      public void addWord(String word, File audio, LearningSet learningSet)
          throws WordAlreadyAddedException, FileNotFoundException {

      }

      @Override
      public void deleteWord(String word, LearningSet learningSet) throws WordNotFoundException {

      }
    };
    SpellingTrainerDescriptor descriptor = new SpellingTrainerDescriptor();

    //Not executed during runtime
    String word = "test";
    File audioFile = new File("test.mp3");
    LearningSet learningSet = new LearningSet("Test Set ");
    service.addWord(word, audioFile,learningSet);
    service.deleteWord(word,learningSet);

    //Selecting the active learning set
    descriptor.setActiveLearningSet(learningSet);

    //Repeat execution for each word
   LearningEntry learningEntry = descriptor.getActiveLearningSet().getLearningEntry(0);
   MediaPresentationListener mpl = new MediaPresentationListener();
   mpl.present(learningEntry.getMediaReference());
    //User Input via JavaFX
    //Check spelling of the entered word
  }
}

