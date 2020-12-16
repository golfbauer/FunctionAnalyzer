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
import org.junit.jupiter.api.Test;

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
  void initialize() throws IOException, WordNotFoundException, LineUnavailableException, UnsupportedAudioFileException, InterruptedException{
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

  static void foo() throws IOException {
    throw new IOException("This is an IOException");
  }

  @Test
  @DisplayName("Checks how checkWord handles wrong Spelling")
  void wrongCheckWord(){
    assertFalse(typingTrainerService.checkWord("Dee", 0));
  }

//  @Test
//  @DisplayName("Checks how Feedback handles wrong Input")
//  void falseFeedback(){
//    assertThrows(IOException.class, () -> foo());
//  }
//
//  @Test
//  @DisplayName("Checks how SaveScore handles exceptions")
//  void falseSaveScore(){
//    assertThrows(IOException.class, () -> foo());
//  }



}
