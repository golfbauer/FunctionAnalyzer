package de.hhn.it.pp.components.vocabletrainer.junit;

import static org.junit.jupiter.api.Assertions.assertThrows;


import de.hhn.it.pp.components.vocabletrainer.LearningState;
import de.hhn.it.pp.components.vocabletrainer.Vocable;
import de.hhn.it.pp.components.vocabletrainer.exceptions.TranslationIsEmptyException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryAlreadyExistException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryNotFoundException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocableNotFoundException;
import de.hhn.it.pp.components.vocabletrainer.provider.JbVocableTrainerService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test for VocableTrainerService bad cases")
public class TestVocableTrainerServiceBadCases {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(TestVocableTrainerServiceBadCases.class);

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
  @DisplayName("Create an already existent VocCategory")
  public void testExceptionWhenCreatingExistentVocCategory() {
    VocCategoryAlreadyExistException vocCategoryAlreadyExistException =
        assertThrows(VocCategoryAlreadyExistException.class, () ->
            jbVocableTrainerService.addVocCategory("Auto", new ArrayList<>()));
  }

  @Test
  @DisplayName("Remove a non existent VocCategory")
  public void testExceptionWhenRemovingNonExistentVocCategory() {
    VocCategoryNotFoundException vocCategoryNotFoundException =
        assertThrows(VocCategoryNotFoundException.class, () ->
            jbVocableTrainerService.removeVocCategory("NotExistentVocCategoryName"));
  }

  @Test
  @DisplayName("Get a non existent vocable in an existent vocCategory")
  public void testExceptionWhenGettingNonExistentVocable() {
    VocableNotFoundException vocableNotFoundException = assertThrows(VocableNotFoundException.class,
        () -> jbVocableTrainerService.getVocable(12345, "Auto"));
  }

  @Test
  @DisplayName("Get a vocable in an not existent vocCategory")
  public void testExceptionWhenGettingVocableFromNotExistentVocCategory() {
    VocCategoryNotFoundException vocCategoryNotFoundException =
        assertThrows(VocCategoryNotFoundException.class,
            () -> jbVocableTrainerService.getVocable(0, "NotExistentVocCategoryName"));
  }

  @Test
  @DisplayName("Get vocabulary list from a not existent vocCategory")
  public void testExceptionWhenGettingVocableListFromNotExistentVocCategory() {
    VocCategoryNotFoundException vocCategoryNotFoundException =
        assertThrows(VocCategoryNotFoundException.class,
            () -> jbVocableTrainerService.getVocabulary("NotExistentVocCategoryName"));
  }

  @Test
  @DisplayName("Add vocable to not existent vocCategory")
  public void testExceptionWhenAddingVocableToNotExistentVocCategory() {
    VocCategoryNotFoundException vocCategoryNotFoundException =
        assertThrows(VocCategoryNotFoundException.class, () -> jbVocableTrainerService
            .addVocable("test", new String[] {"test"}, "NotExistentVocCategoryName"));
  }

  @Test
  @DisplayName("Add vocable with empty translations")
  public void testExceptionWhenAddingVocableWithoutTranslation() {
    TranslationIsEmptyException translationIsEmptyException =
        assertThrows(TranslationIsEmptyException.class,
            () -> jbVocableTrainerService.addVocable("test", new String[] {}, "Auto"));
  }
}
