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
import de.hhn.it.pp.components.typingtrainer.TypingTrainerService;
import de.hhn.it.pp.components.typingtrainer.WordNotFoundException;
import de.hhn.it.pp.components.typingtrainer.provider.ProviderTypingTrainer;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
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

  ProviderTypingTrainer typingTrainerService;
/*  FileReader testFileReader;
  File testAudioFile;
  String[] testPractice;
  String[] testTyped;
  Feedback testFeedback;
  String testSelectedText;
  PracticeText testPracticeText;
  TypingTrainerDescriptor testDescriptor;*/
  TypingTrainerDescriptor descriptor;
  File audioWrongWord;
  Feedback feedback;
  FileReader fileReader;
  String[] selectedText;
  PracticeText practiceText;

  @BeforeEach
  void initialize() throws IOException, WordNotFoundException, LineUnavailableException,
      UnsupportedAudioFileException, InterruptedException{
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

  }

  @Test
  @DisplayName("Checks if written word matches with word from practice text.")
  void testCheckWord() {
    String wordToCheck = "Die";
    int wordFromPracticeText = 1;

    assertTrue(typingTrainerService.checkWord(wordToCheck, wordFromPracticeText));
  }
}
