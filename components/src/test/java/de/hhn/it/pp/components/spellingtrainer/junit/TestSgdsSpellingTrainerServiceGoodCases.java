package de.hhn.it.pp.components.spellingtrainer.junit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.hhn.it.pp.components.spellingtrainer.Provider.LearningEntry;
import de.hhn.it.pp.components.spellingtrainer.Provider.LearningSet;
import de.hhn.it.pp.components.spellingtrainer.Provider.SgdsSpellingTrainerService;
import de.hhn.it.pp.components.spellingtrainer.exceptions.LearningSetCouldNotBeFoundException;
import de.hhn.it.pp.components.spellingtrainer.exceptions.LearningSetNameAlreadyAssignedException;
import de.hhn.it.pp.components.spellingtrainer.exceptions.NoWordException;
import de.hhn.it.pp.components.spellingtrainer.exceptions.WordAlreadyAddedException;
import de.hhn.it.pp.components.spellingtrainer.exceptions.WordNotFoundException;
import java.io.File;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test the SgdsSpellingTrainerService with good cases.")
public class TestSgdsSpellingTrainerServiceGoodCases {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(TestSgdsSpellingTrainerServiceGoodCases.class);
  SgdsSpellingTrainerService service;
  String audioFile;

  @BeforeEach
  void initialize()
      throws LearningSetNameAlreadyAssignedException, LearningSetCouldNotBeFoundException,
      WordAlreadyAddedException, NoWordException {
    service = new SgdsSpellingTrainerService();
    audioFile = "src/main/java/de/hhn/it/pp/components/spellingtrainer/audiofiles/Book.wav";
    service.createLearningSet("TestSet");
    service.addWord("test", audioFile, "TestSet");
    service.startLearning("TestSet");
  }

  @Test
  @DisplayName("Checks if the spelling is correct.")
  void checkingSpellingIsCorrect() {
    assertTrue(service.checkSpelling("test"));
  }

  @Test
  @DisplayName("Adds a word to an existing learningset")
  void addWordToList() throws LearningSetCouldNotBeFoundException, WordAlreadyAddedException {
    service.addWord("test2", audioFile, "TestSet");
    assertEquals("test2",
        service.getDescriptor().getActiveLearningSet().getLearningEntries().get(1).getWordEntry());
  }

  @Test
  @DisplayName("Deletes a word from an existing learning set.")
  void deleteWordFromList() throws LearningSetCouldNotBeFoundException, WordNotFoundException {
    service.deleteWord("test", "TestSet");
    boolean contains = false;
    ArrayList<LearningEntry> leList = service.getLearningSet("TestSet").getLearningEntries();
    for (LearningEntry le : leList) {
      contains = le.getWordEntry().equals("test");
    }
    logger.debug("There are no learning entries.");
    assertFalse(contains);
  }

  @Test
  @DisplayName("Creating a new learning set.")
  void createANewLearningSet()
      throws LearningSetNameAlreadyAssignedException, LearningSetCouldNotBeFoundException {
    service.createLearningSet("LearningSet1");
    boolean contains = false;
    ArrayList<LearningSet> lSets = service.getDescriptor().getLearningSets();
    for (LearningSet ls : lSets) {
      contains = ls.getLearningSetName().equals("LearningSet1");
    }
    assertTrue(contains);
  }

  @Test
  @DisplayName("Deletes an existing learning set.")
  void deleteAnExistingLearningSet() throws LearningSetCouldNotBeFoundException {
    service.removeLearningSet("TestSet");
    boolean contains = false;
    ArrayList<LearningSet> lSets = service.getDescriptor().getLearningSets();
    for (LearningSet ls : lSets) {
      contains = ls.getLearningSetName().equals("TestSet");
    }
    assertFalse(contains);
  }

  @Test
  @DisplayName("Get all learning sets.")
  void getAllLearningSets() {
    ArrayList<LearningSet> sets = service.getLearningSets();
    assertTrue(sets.size() >= 3);
  }

  @Test
  @DisplayName("Get existing learning set from name.")
  void getASpecificLearningSet() throws LearningSetCouldNotBeFoundException {
    LearningSet set = service.getLearningSet("TestSet");
    assertEquals("TestSet", set.getLearningSetName());
  }

  @Test
  @DisplayName("Checks if there is a next word")
  void checkNextWord() throws LearningSetCouldNotBeFoundException, WordAlreadyAddedException {
    service.addWord("test2", audioFile, "TestSet");
    assertTrue(service.hasNextWord());
  }

  @Test
  @DisplayName("Returns current word")
  void getCurrentWord() {
    assertEquals("test", service.currentWord());
  }

  @Test
  @DisplayName("Starting the learning service")
  void startLearning() throws LearningSetCouldNotBeFoundException, NoWordException {
    service.getDescriptor().setActiveLearningSet(null);
    service.getDescriptor().updateCounter("wrong", 1);
    service.getDescriptor().updateCounter("right", 5);
    service.getDescriptor().updateCounter("remaining", 3);
    service.getDescriptor().setIsLearning(false);
    service.startLearning("TestSet");
    assertAll(() -> assertEquals("TestSet",
        service.getDescriptor().getActiveLearningSet().getLearningSetName()),
        () -> assertTrue(service.getDescriptor().getCounter("wrong") == 0 &&
            service.getDescriptor().getCounter("right") == 0 &&
            service.getDescriptor().getCounter("remaining") == 1),
        () -> assertTrue(service.getDescriptor().getIsLearning()));
  }

  @Test
  @DisplayName("Ending learning service ")
  void endLearning() {
    service.endLearning();
    assertAll(() -> assertNull(service.getDescriptor().getActiveLearningSet()),
        () -> assertFalse(service.getDescriptor().getIsLearning()));
  }
}