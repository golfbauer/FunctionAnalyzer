package de.hhn.it.pp.components.spellingtrainer;

import de.hhn.it.pp.components.spellingtrainer.provider.SgdsSpellingTrainerService;

public class DemoSpellingTrainerUsage {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(DemoSpellingTrainerUsage.class);

  public static void main(String[] args)
      throws Exception {
    //Nur für den Test
    SgdsSpellingTrainerService service = new SgdsSpellingTrainerService();
    //Not executed during runtime
    String word = "test";
    String audioFile =
       "src/main/java/de/hhn/it/pp/components/spellingtrainer/audiofiles/Book.wav";
    service.createLearningSet("Test Set");
    service.addWord(word, audioFile, "Test Set");
    //Starting learning session
    service.startLearning("Test Set");
    //Repeat execution for each word
    System.out.println("" + service.currentWord());
    service.playWord();
    //User Input via JavaFX
    String input = "test";
    //Check spelling of the entered word
    System.out.println("" + service.checkSpelling(input));
    service.hasNextWord();
  }
}