package de.hhn.it.pp.components.spellingtrainer.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.hhn.it.pp.components.spellingtrainer.Provider.SgdsSpellingTrainerService;
import de.hhn.it.pp.components.spellingtrainer.SpellingTrainerDescriptor;
import de.hhn.it.pp.components.spellingtrainer.exceptions.LearningSetCouldNotBeFoundException;
import de.hhn.it.pp.components.spellingtrainer.exceptions.LearningSetNameAlreadyAssignedException;
import de.hhn.it.pp.components.spellingtrainer.exceptions.WordAlreadyAddedException;
import java.io.File;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test the SgdsSpellingTrainerService with good cases.")
public class TestSgdsSpellingTrainerServiceGoodCases {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(TestSgdsSpellingTrainerServiceGoodCases.class);
  SgdsSpellingTrainerService service;
  File audioFile;

  @BeforeEach
  void setup() throws LearningSetNameAlreadyAssignedException, LearningSetCouldNotBeFoundException,
      WordAlreadyAddedException {
    service = new SgdsSpellingTrainerService();

    String word = "test";
    audioFile = new File("C:\\Users\\simon\\Downloads\\Book.wav");

    service.createLearningSet("TestSet");
    service.addWord(word, audioFile, "TestSet");

    service.registerMediaPresentationListener();
    service.startLearning("TestSet");
  }

  @Test
  @DisplayName("Checks if the spelling is correct.")
  void checkingSpellingIsCorrect(){
    assertTrue(service.checkSpelling("test"));
  }

  @Test
  @DisplayName("Adds a word to an existing learningset")
  void addWordToList() throws LearningSetCouldNotBeFoundException, WordAlreadyAddedException {
    service.addWord("test2", audioFile, "TestSet");
    assertEquals("test2", SpellingTrainerDescriptor.getActiveLearningSet().getLearningEntries().get(1).getWordEntry());
  }

}
