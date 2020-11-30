package de.hhn.it.pp.components.spellingtrainer.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.hhn.it.pp.components.spellingtrainer.Provider.MediaPresentationListener;
import de.hhn.it.pp.components.spellingtrainer.Provider.SgdsSpellingTrainerService;
import de.hhn.it.pp.components.spellingtrainer.SpellingTrainerDescriptor;
import de.hhn.it.pp.components.spellingtrainer.exceptions.LearningSetCouldNotBeFoundException;
import de.hhn.it.pp.components.spellingtrainer.exceptions.LearningSetNameAlreadyAssignedException;
import de.hhn.it.pp.components.spellingtrainer.exceptions.WordAlreadyAddedException;
import de.hhn.it.pp.components.spellingtrainer.exceptions.WordNotFoundException;
import java.io.File;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test the SgdsSpellingTrainerService with good cases.")
public class TestSgdsSpellingTrainerServiceGoodCases {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(TestSgdsSpellingTrainerServiceGoodCases.class);





  @Test
  @DisplayName("Checks if the spelling is correct.")
  void checkingSpellingIsCorrect()
      throws LearningSetNameAlreadyAssignedException, LearningSetCouldNotBeFoundException,
      WordAlreadyAddedException {
    SgdsSpellingTrainerService service = new SgdsSpellingTrainerService();
    File audioFile = new File("C:\\Users\\denni\\IdeaProjects\\learning-devtools-20ws\\components\\src\\main\\java\\de\\hhn\\it\\pp\\components\\spellingtrainer\\audiofiles\\Book.wav");
    service.createLearningSet("TestSet");
    service.addWord("test", audioFile, "TestSet");
    MediaPresentationListener mpl= new MediaPresentationListener();
    service.startLearning("TestSet");
    assertTrue(service.checkSpelling("test"));
  }

  @Test
  @DisplayName("Adds a word to an existing learningset")
  void addWordToList() throws LearningSetCouldNotBeFoundException, WordAlreadyAddedException,
      LearningSetNameAlreadyAssignedException {
    SgdsSpellingTrainerService service = new SgdsSpellingTrainerService();

    //TODO: Files global initialisieren
    File audioFile = new File("C:\\Users\\denni\\IdeaProjects\\learning-devtools-20ws\\components\\src\\main\\java\\de\\hhn\\it\\pp\\components\\spellingtrainer\\audiofiles\\Book.wav");
    MediaPresentationListener mpl= new MediaPresentationListener();
    service.createLearningSet("TestSet");
    service.addWord("test2", audioFile, "TestSet");
    service.startLearning("TestSet");
    assertEquals("test2", SpellingTrainerDescriptor.getActiveLearningSet().getLearningEntries().get(0).getWordEntry());
  }

}
