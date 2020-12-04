package de.hhn.it.pp.components.typingtrainer;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/***
 * @author Tobias Maraci, Robert Pistea
 * @version 1.3
 * @since 1.0
 */

public class DemoTypingTrainerUsage {

  private static TypingTrainerDescriptor descriptor;

  public static void main(String[] args) throws IOException, InterruptedException {
    TypingTrainerService service = new TypingTrainerService() {
      @Override
      public boolean checkWord(String word, int index) {
        String ptWord = descriptor.getPracticeText().getWordAtIndex(index); //Word from practiceText
        boolean isCorrect = word.equals(ptWord) ? true : false;

        if(isCorrect) {descriptor.getFeedback().increaseCounterRightWords();}

        return isCorrect;
      }

      @Override
      public void audioOutput() {
        System.out.println("NI: play sound");
      }

      @Override
      public void quitSession() {
        System.out.println("NI: return to startscreen");
      }

      @Override
      public void showFeedback(Feedback feedback) {
        System.out.println("~~~ Score ~~~");
        System.out.println("Time: "+feedback.getTime());
        System.out.println("WPM:  "+feedback.getWordsPerMinute());
      }

      @Override
      public void saveScore(Feedback score) {
        System.out.println("NI: score saved");
      }

      @Override
      public void loadScore() {
        System.out.println("NI: score loaded");
      }

      @Override
      public void userInput() {
        Scanner scan = new Scanner(System.in);
        descriptor.addTypedWords(scan.nextLine(), 0);

      }

      @Override
      public void countdown(int seconds) throws InterruptedException {
        for (int i = seconds; i > -1; i--) {
          if (i != 0) {
            System.out.println("__" + i + "__");
            TimeUnit.SECONDS.sleep(1);
          } else {
            System.out.println("__START__");
            TimeUnit.MILLISECONDS.sleep(500L);
          }
        }
      }

      @Override
      public void markWord(int index, Color color) {
        //Marks word at index with color in GUI displayed text.
      }

      @Override
      public void selectionOfText() {

      }
    };

    //Initial setup
    service.loadScore();
    File audioWrongWord = new File("sound_wrongWord.mp3");
    Feedback feedback = new Feedback(0,0);

    FileReader fileReader = new FileReader();
    String[] selectedText = fileReader.getPracticeText(); //for later: depends on what button was clicked (use other constructor)
    PracticeText practiceText = new PracticeText(selectedText);

    TypingTrainerDescriptor descriptor = new TypingTrainerDescriptor(audioWrongWord, feedback,practiceText);

    //In session
    service.countdown(5);
    descriptor.getPracticeText().printPracticeText();
    descriptor.getFeedback().setStartTime(LocalTime.now().toNanoOfDay());
    service.userInput(); //Stops if user presses enter
    descriptor.getFeedback().setEndTime(LocalTime.now().toNanoOfDay());

    //Feedback
    for (int i = 0; i < descriptor.getPracticeText().getText().length; i++) {
      try{
        boolean isCorrect = service.checkWord(descriptor.getTypedWordsAtIndex(i), i);

        if (isCorrect == true)
          service.markWord(i, Color.green);
        else
          service.markWord(i, Color.red);

      }
      catch (ArrayIndexOutOfBoundsException | IOException e)
      {
        break;
      }
    }

    descriptor.getFeedback().calculateTime();
    descriptor.getFeedback().calculateWordsPerMinute(descriptor.getTypedWords(), descriptor.getPracticeText().getText());
    service.showFeedback(descriptor.getFeedback());

    //End
    service.saveScore(descriptor.getFeedback());
    service.quitSession();
  }

}
