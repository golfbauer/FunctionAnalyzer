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

    //Repeat execution for each word
   LearningEntry learningEntry = learningSet.getLearningEntry(0);
    audioFile = learningEntry.getMediaReference().getMediaFile();

    //User Eingabe über JavaFX
    //service.checkSpelling(word);
  }
}

