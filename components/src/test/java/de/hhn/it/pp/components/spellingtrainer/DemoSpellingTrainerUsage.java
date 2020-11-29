package de.hhn.it.pp.components.spellingtrainer;

import de.hhn.it.pp.components.spellingtrainer.Provider.SgdsSpellingTrainerService;

import java.io.File;

public class DemoSpellingTrainerUsage {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(DemoSpellingTrainerUsage.class);

  public static void main(String[] args)
      throws Exception {
    //Nur für den Test
    SgdsSpellingTrainerService service = new SgdsSpellingTrainerService();

    //Not executed during runtime
    String word = "test";
    File audioFile = new File("C:\\Users\\simon\\Downloads\\Computer (1).mp3");
    service.createLearningSet("Test Set");
    service.addWord(word, audioFile, "Test Set");

    service.registerMediaPresentationListener();

    //Starting learning session
    service.startLearning("Test Set");


    //Repeat execution for each word
    System.out.println("" + service.currentWord());
    service.playWord();
    //User Input via JavaFX
    String input = "test";
    //Check spelling of the entered word
    System.out.println("" + service.checkSpelling(input));
    service.nextWord();
  }
}

