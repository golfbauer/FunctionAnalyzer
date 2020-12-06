package de.hhn.it.pp.components.spellingtrainer.junit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.hhn.it.pp.components.spellingtrainer.Provider.MediaPresentationListener;
import de.hhn.it.pp.components.spellingtrainer.Provider.MediaReference;
import de.hhn.it.pp.components.spellingtrainer.Provider.SgdsSpellingTrainerService;
import de.hhn.it.pp.components.spellingtrainer.exceptions.LearningSetCouldNotBeFoundException;
import de.hhn.it.pp.components.spellingtrainer.exceptions.LearningSetNameAlreadyAssignedException;
import de.hhn.it.pp.components.spellingtrainer.exceptions.NoWordException;
import de.hhn.it.pp.components.spellingtrainer.exceptions.WordAlreadyAddedException;
import de.hhn.it.pp.components.spellingtrainer.exceptions.WordNotFoundException;
import java.io.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test the SgdsSpellingTrainerService with bad cases.")
public class TestSgdsSpellingTrainerServiceBadCases {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(TestSgdsSpellingTrainerServiceBadCases.class);
  SgdsSpellingTrainerService service;
  String audioFile;

  @BeforeEach
  void initialize()
      throws LearningSetNameAlreadyAssignedException, LearningSetCouldNotBeFoundException,
      WordAlreadyAddedException, NoWordException {
    service = new SgdsSpellingTrainerService();
    audioFile =
       "src/main/java/de/hhn/it/pp/components/spellingtrainer/audiofiles/Book.wav";
    service.createLearningSet("TestSet");
    service.addWord("test", audioFile, "TestSet");
    service.startLearning("TestSet");
  }

  @Test
  @DisplayName("Checks how checkSpelling handels wrong spelling")
  void wrongCheckSpelling() {
    assertFalse(service.checkSpelling("tes"));
  }

  @Test
  @DisplayName("Checks how addWord handels adding an existing word")
  void addAlreadyAddedWord() throws LearningSetCouldNotBeFoundException, WordAlreadyAddedException {
    assertThrows(WordAlreadyAddedException.class,
        () -> service.addWord("test", audioFile, "TestSet"));
  }

  @Test
  @DisplayName("Checks how deleteWord handels removing a non existing word")
  void removeNonExistingWord() {
    assertThrows(WordNotFoundException.class,
        () -> service.deleteWord("tes", "TestSet"));
  }

  @Test
  @DisplayName("Checks how createLearningSet handels adding a LearningSet with the same name")
  void addAlreadyExistingLearningSet() {
    assertThrows(LearningSetNameAlreadyAssignedException.class,
        () -> service.createLearningSet("TestSet"));
  }

  @Test
  @DisplayName("Checks how removeLearningSet handels removing a LearningSet which does not exist")
  void removeNonExistingLearningSet() {
    assertThrows(LearningSetCouldNotBeFoundException.class,
        () -> service.removeLearningSet("TestSe"));
  }

  @Test
  @DisplayName("Checks how getLearningSet handels getting a not existing learningset")
  void getNonExistingLearningSet() {
    assertThrows(LearningSetCouldNotBeFoundException.class,
        () -> service.getLearningSet("TestSe"));
  }

  @Test
  @DisplayName("Checks how startLearning handels getting a not existing learningset")
  void startLearningWithNonExistingLearningSet() {
    service.getDescriptor().setIsLearning(false);
    assertThrows(LearningSetCouldNotBeFoundException.class,
        () -> service.startLearning("TestSe"));
  }

  @Test
  @DisplayName("Checks how nextWord handels not having a next word")
  void noNextWord() {
    assertFalse(service.hasNextWord());
  }

  @Test
  @DisplayName("Checks how currentWord handels getting invalid index")
  void currentWordFromInvalidIndex() {
    service.getDescriptor().setCurrentWordIndex(5);
    assertThrows(IndexOutOfBoundsException.class,
        () -> service.currentWord());
  }
}