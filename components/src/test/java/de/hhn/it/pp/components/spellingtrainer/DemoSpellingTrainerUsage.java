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
      public void audioOutput(File audioFile) throws FileNotFoundException {
      }

      @Override
      public boolean checkSpelling(String writtenWord) {
        return false;
      }

      @Override
      public void addWord(String word, File audio)
          throws WordAlreadyAddedException, FileNotFoundException {

      }

      @Override
      public void deleteWord(String word) throws WordNotFoundException {

      }
    };
    SpellingTrainerDescriptor descriptor = new SpellingTrainerDescriptor();

    //Nicht während Projektablauf
    String word = "test";
    File audioFile = new File("test.mp3");
    service.addWord(word, audioFile);
    service.deleteWord(word);

    //Ablauf für jedes Wort wiederholen
    word = descriptor.getSpellingWord(0);
    audioFile = descriptor.getAudioFile(word);
    service.audioOutput(audioFile);
    //User Eingabe über JavaFX
    service.checkSpelling(word);
  }
}

