package de.hhn.it.pp.components.typingtrainer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/***
 * @author Tobias Maraci, Robert Pistea
 * @version 1.0
 */

public class DemoTypingTrainerUsage {

  public static void main(String[] args) throws WordNotFoundException, FileNotFoundException {
    TypingTrainerService start = new TypingTrainerService() {


      @Override
      public boolean checkWord(String word) throws WordNotFoundException {
        return false;
      }

      @Override
      public void audioOutput(File soundFile) throws FileNotFoundException {

      }

      @Override
      public void markWord(String word) throws WordNotFoundException {

      }

      @Override
      public void selectionOfText(String selectedText) {

      }

      @Override
      public void quitSession() {

      }

      @Override
      public void showFeedback(long time, float wordsPerMinute) {
        System.out.println("Time: " + time + "\n WPM: " + wordsPerMinute);
      }

      @Override
      public void saveScore(File saveFile) throws FileNotFoundException {

      }

      @Override
      public void loadScore(File saveFile) throws FileNotFoundException {

      }
    };

    TypingTrainerDescriptor descriptor = new TypingTrainerDescriptor();

    //Testrun
    String userInput = "";
    int indexText = 0;
    File saveFile = new File("saveFile.txt");
    boolean isRunning = true;

    //Setup training session
    File audioWrongWord = new File("sound.mp3"); // Sound for wrong words
    ArrayList<String> selectedText = new ArrayList<>(); // Text which is selected by user

    descriptor.setSelectedText(selectedText);
    descriptor.setTime(System.currentTimeMillis());
    descriptor.setCounterRightWords(0);

    //During training session
    while (isRunning) {
      if (start.checkWord(selectedText.get(indexText))) {
        descriptor.addCounterRightWords();
      } else {
        start.markWord(selectedText.get(indexText));
        start.audioOutput(audioWrongWord);
      }
    }

    long endTime = System.currentTimeMillis();

    //End training session
    start.saveScore(saveFile);
    descriptor.setTime(descriptor.calculateTime(descriptor.getTime(), endTime));

    start.showFeedback(descriptor.getTime(), descriptor.getWordsPerMinute());
    start.quitSession();
  }
}
