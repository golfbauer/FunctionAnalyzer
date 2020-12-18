package de.hhn.it.pp.components.typingtrainer.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.hhn.it.pp.components.typingtrainer.FeedbackNotFoundException;
import de.hhn.it.pp.components.typingtrainer.PracticeText;
import de.hhn.it.pp.components.typingtrainer.SaveLoad;
import de.hhn.it.pp.components.typingtrainer.TypingTrainerDescriptor;
import de.hhn.it.pp.components.typingtrainer.provider.ProviderTypingTrainer;
import de.hhn.it.pp.components.typingtrainer.Feedback;
import de.hhn.it.pp.components.typingtrainer.FileReader;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.junit.jupiter.api.Test;

/**
 * Badcase Tests.
 *
 * @author Tobias Maraci, Robert Pistea
 * @version 1.1
 * @since 1.0
 */

@DisplayName("Test the TypingTrainerService with bad cases")
public class TestTypingTrainerBadCases {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(TestTypingTrainerBadCases.class);

  TypingTrainerDescriptor descriptor;
  File audioWrongWord;
  Feedback feedback;
  FileReader fileReader;
  String[] selectedText;
  PracticeText practiceText;
  ProviderTypingTrainer typingTrainerService;


  @BeforeEach
  void initialize() throws IOException, LineUnavailableException, UnsupportedAudioFileException, InterruptedException{
    audioWrongWord = new File("javafx/src/main/resources/typingTrainerFiles/8BIT RETRO Beep.mp3");

    feedback = new Feedback(0,0);

    ClassLoader classLoader;
    classLoader = getClass().getClassLoader();
    File file = new File(classLoader.getResource("practiceText-3.txt").getFile());

    fileReader = new FileReader(file);
    selectedText = fileReader.getPracticeText();
    practiceText = new PracticeText(selectedText);

    descriptor = new TypingTrainerDescriptor(audioWrongWord, feedback, practiceText);

    typingTrainerService = new ProviderTypingTrainer();
    typingTrainerService.descriptor = descriptor;

    typingTrainerService.countdown(11);
    typingTrainerService.countdown(66);

  }

  @Test
  @DisplayName("Checks how checkWord handles wrong Spelling")
  void wrongCheckWord(){
    assertFalse(typingTrainerService.checkWord("Dee", 0));
  }

  @Test
  @DisplayName("Checks how markWord handles faulty checkWord")
  void wrongMarkWord(){
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> typingTrainerService.markWord(1000, Color.red));
  }

  @Test
  @DisplayName("Checks how markWord handles faulty checkWord")
  void wrongGreenWord(){
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> typingTrainerService.markWord(1000, Color.green));
  }

//  @Test
//  @DisplayName("Checks if saveScores throws the given exception")
//  void wrongSaveScores(){
//    assertThrows(NullPointerException.class, () -> typingTrainerService.saveScore(feedback));
//  }

//  @Test
//  @DisplayName("Checks how countdown handles faulty input")
//  void wrongCountdown() throws InterruptedException {
//    assertFalse(Objects.equals(feedback.getEndTime(), feedback.getStartTime()));
//  }

  @Test
  @DisplayName("Test SaveLoad if it saves and load correctly")
  void wrongSaveLoad() {
    SaveLoad saveLoad = new SaveLoad();
    assertThrows(NullPointerException.class, () -> saveLoad.save(null));
  }

  @Test
  @DisplayName("Test SaveLoad if it saves and load correctly")
  void wrongLoadLoad() {
    SaveLoad saveLoad = new SaveLoad();
    assertThrows(NullPointerException.class, () -> saveLoad.load(null));
  }

  @Test
  @DisplayName("Checks how Feedback handles wrong Input")
  void falseFeedback(){
    assertThrows(FeedbackNotFoundException.class, () -> typingTrainerService.showFeedback(null));
  }

  @Test
  @DisplayName("Checks how Feedback handles wrong Input")
  void falseFeedbackSecond(){
    feedback.setStartTime(-1);
    feedback.setEndTime(-500);
    assertThrows(FeedbackNotFoundException.class, () -> typingTrainerService.showFeedback(feedback));
  }

  @Test
  @DisplayName("Checks how SaveScore handles exceptions")
  void falseSaveScore(){
    typingTrainerService.path = "failtest.txt";
    assertThrows(NullPointerException.class, () -> typingTrainerService.saveScore(feedback));
  }

//  @Test
//  @DisplayName("Checks first save score") //aus SaveLoad
//  void falseSave() {
//    SaveLoad saveLoad = new SaveLoad();
//    saveLoad.path = "weizenclown654";
//    assertThrows(FileNotFoundException.class, () -> saveLoad.save("asd", "13.32", "12"));
//  }

  @Test
  @DisplayName("Checks how LoadScore handles exceptions")
  void falseLoadScore(){
    typingTrainerService.path = "failtest.txt";
    assertThrows(NullPointerException.class, () -> typingTrainerService.loadScore());
  }
}
