package de.hhn.it.pp.components.typingtrainer;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/***
 * @author Tobias Maraci, Robert Pistea
 * @version 1.1
 * @since 1.0
 */

public class DemoTypingTrainerUsage {

  public static void main(String[] args) throws FileNotFoundException, InterruptedException {
    TypingTrainerService service = new TypingTrainerService() {
      @Override
      public boolean checkWord(String word) {
        return false;
      }

      @Override
      public void audioOutput(File soundFile) {
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
      public void userInput(PracticeText practiceText) {
        Scanner scan = new Scanner(System.in);
        practiceText.setTypedWords(scan.nextLine());
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
    };

    //Initial setup
    service.loadScore();
    File audioWrondWord = new File("sound_wrongWord.mp3");
    Feedback feedback = new Feedback(0,0);

    FileReader fileReader = new FileReader();
    String[] selectedText = fileReader.GetPracticeText(); //for later: depends on what button was clicked (use other constructor)
    PracticeText practiceText = new PracticeText(selectedText);

    TypingTrainerDescriptor descriptor = new TypingTrainerDescriptor(audioWrondWord, feedback,practiceText);

    //In session
    service.countdown(5);
    descriptor.getPracticeText().printPracticeText();
    descriptor.getFeedback().setStartTime(LocalTime.now().toNanoOfDay());
    service.userInput(practiceText); //Stops if user presses enter
    descriptor.getFeedback().setEndTime(LocalTime.now().toNanoOfDay());

    //Feedback
    descriptor.getFeedback().calculateTime();
    descriptor.getFeedback().calculateWordsPerMinute(descriptor.getPracticeText().getTypedWords());
    service.showFeedback(descriptor.getFeedback());

    //End
    service.saveScore(descriptor.getFeedback());
    service.quitSession();
  }

}
