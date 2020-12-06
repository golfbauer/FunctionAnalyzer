package de.hhn.it.pp.components.typingtrainer.junit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.hhn.it.pp.components.typingtrainer.PracticeText;
import de.hhn.it.pp.components.typingtrainer.TypingTrainerDescriptor;
import de.hhn.it.pp.components.typingtrainer.WordNotFoundException;
import de.hhn.it.pp.components.typingtrainer.provider.ProviderTypingTrainer;
import de.hhn.it.pp.components.typingtrainer.Feedback;
import de.hhn.it.pp.components.typingtrainer.FileReader;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

@DisplayName("Test the TypingTrainerService with bad cases")
public class TestTypingTrainerBadCases {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(TestTypingTrainerBadCases.class);

  ProviderTypingTrainer TypingTrainerService;
  FileReader testFileReader;
  File testAudioFile;
  String[] testPractice;
  String[] testTyped;
  Feedback testFeedback;
  String testSelectedText;
  PracticeText testPracticeText;
  TypingTrainerDescriptor testDescriptor;

  @BeforeEach
  void initialize() throws IOException, WordNotFoundException, LineUnavailableException, UnsupportedAudioFileException, InterruptedException{
  TypingTrainerService = new ProviderTypingTrainer();

  testAudioFile = new File("javafx/src/main/resources/typingTrainerFiles/8BIT RETRO Beep.mp3");

  testFeedback = new Feedback(0,0);

  testSelectedText ="practiceText-3.txt";
  testFileReader = new FileReader(testSelectedText);
  testPractice = testFileReader.getPracticeText();
  testPracticeText = new PracticeText(testPractice);

  testDescriptor = new TypingTrainerDescriptor(testAudioFile, testFeedback, testPracticeText);

  }

}
