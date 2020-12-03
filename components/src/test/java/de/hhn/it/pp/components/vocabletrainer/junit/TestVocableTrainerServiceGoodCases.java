package de.hhn.it.pp.components.vocabletrainer.junit;


import de.hhn.it.pp.components.vocabletrainer.LearningState;
import de.hhn.it.pp.components.vocabletrainer.Vocable;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryAlreadyExistException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryNotFoundException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocableNotFoundException;
import de.hhn.it.pp.components.vocabletrainer.provider.JbVocableTrainer;
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

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("Test the VocableTrainer with good cases.")
public class TestVocableTrainerServiceGoodCases {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(TestVocableTrainerServiceGoodCases.class);

  JbVocableTrainerService jbVocableTrainerService;
  JbVocableTrainer jbVocableTrainer;
  LearningState learningState;
  List<Vocable> testList;
  HashMap<String, List<Vocable>> testMap;

  @BeforeEach
  void setup() throws VocCategoryAlreadyExistException {
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
}
