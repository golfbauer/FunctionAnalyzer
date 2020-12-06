package de.hhn.it.pp.components.typingtrainer.junit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.hhn.it.pp.components.typingtrainer.Feedback;
import de.hhn.it.pp.components.typingtrainer.FileReader;
import de.hhn.it.pp.components.typingtrainer.PracticeText;
import de.hhn.it.pp.components.typingtrainer.TypingTrainerDescriptor;
import de.hhn.it.pp.components.typingtrainer.WordNotFoundException;
import de.hhn.it.pp.components.typingtrainer.provider.ProviderTypingTrainer;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Typing Trainer Tests")
public class TestsTypingTrainerGoodCases {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(TestsTypingTrainerGoodCases.class);

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
  void initialize() throws IOException, WordNotFoundException, LineUnavailableException,
      UnsupportedAudioFileException, InterruptedException{
    TypingTrainerService = new ProviderTypingTrainer();

    testAudioFile = new File("javafx/src/main/resources/typingTrainerFiles/8BIT RETRO Beep.mp3");

    testFeedback = new Feedback(0,0);

    testSelectedText ="practiceText-3.txt";
    testFileReader = new FileReader(testSelectedText);
    testPractice = testFileReader.getPracticeText();
    testPracticeText = new PracticeText(testPractice);

    testDescriptor = new TypingTrainerDescriptor(testAudioFile, testFeedback, testPracticeText);

  }

  //Rausnehmen
  //region Tutorial
  @Test
  @DisplayName("Should demonstrate a simple assertion")
  void shouldShowGoodAssertion() {
    Assertions.assertEquals(1,1);
  }

  @Test
  void shouldShowWorstAssertion() {
    Assertions.assertEquals(1,2);
  }

  @Test
  @DisplayName("Should check all items in the list")
  void shouldCheckAllItemsInTheList() {
    List<Integer> numbers = List.of(2,3,5,7);

    Assertions.assertEquals(java.util.Optional.of(1), numbers.get(0));
    Assertions.assertEquals(java.util.Optional.of(3),numbers.get(1));
    Assertions.assertEquals(java.util.Optional.of(5),numbers.get(2));
    Assertions.assertEquals(java.util.Optional.of(7),numbers.get(3));
  }

  @Test
  void shouldOnlyRunTheTestIfSomeCriteriaAreMet() {
    Assumptions.assumeTrue(11 > 10);
    assertEquals(1,1);
  }
  //endregion
}
