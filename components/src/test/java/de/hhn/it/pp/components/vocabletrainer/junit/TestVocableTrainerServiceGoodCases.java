package de.hhn.it.pp.components.vocabletrainer.junit;


import de.hhn.it.pp.components.vocabletrainer.LearningState;
import de.hhn.it.pp.components.vocabletrainer.Vocable;
import de.hhn.it.pp.components.vocabletrainer.exceptions.TranslationIsEmptyException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryAlreadyExistException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryNotFoundException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocableNotFoundException;
import de.hhn.it.pp.components.vocabletrainer.provider.JbVocableTrainerService;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("Test the VocableTrainer with good cases.")
public class TestVocableTrainerServiceGoodCases {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(TestVocableTrainerServiceGoodCases.class);

  JbVocableTrainerService jbVocableTrainerService;
  LearningState learningState;
  List<Vocable> testList;
  HashMap<String, List<Vocable>> testMap;

  @BeforeEach
  void setup() {
    testList = new ArrayList<>();

    testList.add(
        new Vocable("Auto", new String[] {"car", "vehicle", "motorcar", "automobile", "auto"}));
    testList.add(new Vocable("Spiegel", new String[] {"mirror", "glass", "reflector"}));
    testList.add(new Vocable("Reifen", new String[] {"tire", "hoop", "maturation"}));
    testList.add(new Vocable("Lenkrad", new String[] {"wheel", "steering wheel"}));
    testList.add(new Vocable("Autositz", new String[] {"car seat", "seat"}));

    testMap = new HashMap<>();
    testMap.put("Auto", testList);
    learningState = new LearningState();
    jbVocableTrainerService = new JbVocableTrainerService();
    learningState.setScore(10);
    learningState.setVocabularyList(testMap);
    jbVocableTrainerService.loadData(learningState);
  }

  @Test
  @DisplayName("Test for getScore")
  void TestGetScore() {
    assertEquals(10, learningState.getScore());
  }

  @Test
  @DisplayName("Test for getVocCategories")
  void TestGetVocCategories() {
    assertEquals(new ArrayList<>(testMap.keySet()), jbVocableTrainerService.getVocCategories());
  }

  @Test
  @DisplayName("Test for addVocCategory")
  void TestAddVocCategory() throws VocCategoryAlreadyExistException, VocCategoryNotFoundException,
      VocableNotFoundException {
    Vocable testVocable = new Vocable("tisch", new String[] {"table"});
    List<Vocable> vocableListTester = new ArrayList<>();
    vocableListTester.add(testVocable);
    jbVocableTrainerService.addVocCategory("schule", vocableListTester);
    assertEquals(testVocable, jbVocableTrainerService.getVocable(0, "schule"));
  }

  @Test
  @DisplayName("Test for removeVocCategory")
  void TestRemoveVocCategory() throws VocCategoryNotFoundException {
    jbVocableTrainerService.removeVocCategory("Auto");
    assertEquals(new ArrayList<>(), jbVocableTrainerService.getVocCategories());
  }

  @Test
  @DisplayName("Test for getVocable")
  void TestGetVocable() throws VocCategoryNotFoundException, VocableNotFoundException {
    assertEquals("Auto", jbVocableTrainerService.getVocable(0, "Auto").getLearningWord());
  }

  @Test
  @DisplayName("Test for getVocabulary")
  void TestGetVocabulary() throws VocCategoryNotFoundException {
    assertEquals(testList, jbVocableTrainerService.getVocabulary("Auto"));
  }

  @Test
  @DisplayName("Test for addVocable")
  void TestAddVocable()
      throws VocCategoryNotFoundException, TranslationIsEmptyException, VocableNotFoundException {
    jbVocableTrainerService.addVocable("Hupe", new String[] {"horn"}, "Auto");
    int ka = 0;
    for (int i = 0; i < jbVocableTrainerService.getVocabulary("Auto").size(); i++) {
      if ("Hupe".equals(jbVocableTrainerService.getVocable(i, "Auto").getLearningWord())) {
        assertTrue(true);
        ka = 1;
      }
    }
    if (ka != 1) {
      assertFalse(fail("Vocable is not added"));
    }
  }

  @Test
  @DisplayName("Test for removeVocable")
  void TestRemoveVocable() throws VocCategoryNotFoundException, VocableNotFoundException {
    jbVocableTrainerService.removeVocable(0, "Auto");
    try {
      jbVocableTrainerService.getVocable(0, "Auto").getLearningWord();
    } catch (VocableNotFoundException e) {
      assertTrue(true, "Vocable is removed");
    }
  }

  @Test
  @DisplayName("Test for editVocCategory")
  void TestEditVocCategory() throws VocCategoryNotFoundException {
    jbVocableTrainerService.editVocCategory("Auto", "Fahrzeug");
    int ka = 0;
    for (int i = 0; i < jbVocableTrainerService.getVocCategories().size(); i++) {
      if ("Auto".equals(jbVocableTrainerService.getVocCategories().get(i))) {
        assertFalse(fail("Old Category is still here"));
      }
      if ("Fahrzeug".equals(jbVocableTrainerService.getVocCategories().get(i))) {
        assertTrue(true);
        ka = 1;
      }
    }
    if (ka != 1) {
      assertFalse(fail("Category is not edited"));
    }
  }

  @Test
  @DisplayName("Test for CheckVocable")
  void TestCheckVocable() throws VocCategoryNotFoundException {
    assertTrue(jbVocableTrainerService.checkVocable("Auto", 0, "Auto", 3));
  }

  @Test
  @DisplayName("Test for EditVocab")
  void TestEditVocab()
      throws VocCategoryNotFoundException, VocableNotFoundException, TranslationIsEmptyException {
    int ka = 0;
    jbVocableTrainerService.editVocable(0,"Car",new String[] {"car"},"Auto");
    for (int i = 0; i < jbVocableTrainerService.getVocabulary("Auto").size(); i++) {
      if ("Car".equals(jbVocableTrainerService.getVocable(i, "Auto").getLearningWord())) {
        assertTrue(true);
        ka = 1;
      }
      if ("Auto".equals(jbVocableTrainerService.getVocable(i, "Auto").getLearningWord())){
        assertFalse(fail("Vocable is not edited"));
      }
    }
    if (ka != 1) {
      assertFalse(fail("Vocable is not edited"));
    }
  }
}
