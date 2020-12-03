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
  void setup() throws TranslationIsEmptyException {
    testList = new ArrayList<>();

    testList.add(
        new Vocable("Auto", new String[] {"car", "vehicle", "motorcar", "automobile", "auto"}));
    testList.add(new Vocable("Spiegel", new String[] {"mirror", "glass", "reflector"}));
    testList.add(new Vocable("Reifen", new String[] {"tire", "hoop", "maturation"}));
    testList.add(new Vocable("Lenkrad", new String[] {"wheel", "steering wheel"}));
    testList.add(new Vocable("Autositz", new String[] {"car seat", "seat"}));

    testMap = new HashMap<>();
    testMap.put("Auto", testList);
    testMap.put("PC", new ArrayList<>());
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
  @DisplayName("Add vocable with no translations")
  public void testExceptionWhenAddingVocableWithoutTranslation() {
    TranslationIsEmptyException translationIsEmptyException =
        assertThrows(TranslationIsEmptyException.class,
            () -> jbVocableTrainerService.addVocable("test", new String[] {}, "Auto"));
  }

  @Test
  @DisplayName("Add vocable with empty translations")
  public void testExceptionWhenAddingVocableWithEmptyTranslation() {
    TranslationIsEmptyException translationIsEmptyException =
        assertThrows(TranslationIsEmptyException.class,
            () -> jbVocableTrainerService.addVocable("test", new String[] {""}, "Auto"));
  }

  @Test
  @DisplayName("Remove not existent vocable")
  public void testExceptionWhenRemovingNotExistentVocable() {
    VocableNotFoundException vocableNotFoundException = assertThrows(VocableNotFoundException.class,
        () -> jbVocableTrainerService.removeVocable(123456, "Auto"));
  }

  @Test
  @DisplayName("Remove vocable from not existent vocCategory")
  public void testExceptionWhenRemovingVocableFromNotExistentVocCategory() {
    VocCategoryNotFoundException vocCategoryNotFoundException =
        assertThrows(VocCategoryNotFoundException.class,
            () -> jbVocableTrainerService.removeVocable(0, "NotExistentVocCategoryName"));
  }

  @Test
  @DisplayName("Edit a non existent vocCategory")
  public void testExceptionWhenEditingNonExistentVocCategory() {
    VocCategoryNotFoundException vocCategoryNotFoundException =
        assertThrows(VocCategoryNotFoundException.class, () -> jbVocableTrainerService
            .editVocCategory("NotExistentVocCategoryName", "NewCategoryName"));
  }

  @Test
  @DisplayName("Edit a vocCategory to an already existend VocCategory")
  public void testExceptionWhenEditingVocCategoryToAlreadyExistentVocCategory() {
    VocCategoryAlreadyExistException vocCategoryAlreadyExistException =
        assertThrows(VocCategoryAlreadyExistException.class,
            () -> jbVocableTrainerService.editVocCategory("Auto", "PC"));
  }

  @Test
  @DisplayName("Check vocable from non existent vocCategory")
  public void testExceptionWhenCheckingVocableFromNotExistendVocCategory() {
    VocCategoryNotFoundException vocCategoryNotFoundException =
        assertThrows(VocCategoryNotFoundException.class,
            () -> jbVocableTrainerService.checkVocable("car", 0, "NotExistentVocCategoryName", 0));
  }

  @Test
  @DisplayName("Check non existend vocable")
  public void testExceptionWhenCheckingNonExistentVocable() {
    VocableNotFoundException vocableNotFoundException =
        assertThrows(VocableNotFoundException.class,
            () -> jbVocableTrainerService.checkVocable("car", 123456, "Auto", 0));
  }

  @Test
  @DisplayName("Edit a not exitstent vocable")
  public void testExceptionWhenEditingNotExistentVocable() {
    VocableNotFoundException vocableNotFoundException = assertThrows(VocableNotFoundException.class,
        () -> jbVocableTrainerService.editVocable(123456, "test", new String[] {"test"}, "Auto"));
  }

  @Test
  @DisplayName("Edit vocable in not existent vocCategory")
  public void testExceptionWhenEditingVocableInNotExistentVocCategory() {
    VocCategoryNotFoundException vocCategoryNotFoundException =
        assertThrows(VocCategoryNotFoundException.class, () -> jbVocableTrainerService
            .editVocable(0, "test", new String[] {"test"}, "NotExistentVocCategoryName"));
  }

  @Test
  @DisplayName("Edit vocable with no translations")
  public void testExceptionWhenEditingVocableWithoutTranslationIsEmpty() {
    TranslationIsEmptyException translationIsEmptyException =
        assertThrows(TranslationIsEmptyException.class, () -> jbVocableTrainerService
            .editVocable(0, "test", new String[] {}, "NotExistentVocCategoryName"));
  }

  @Test
  @DisplayName("Edit vocable with empty translations")
  public void testExceptionWhenEditingVocableWithEmptyTranslationIsEmpty() {
    TranslationIsEmptyException translationIsEmptyException =
        assertThrows(TranslationIsEmptyException.class, () -> jbVocableTrainerService
            .editVocable(0, "test", new String[] {""}, "NotExistentVocCategoryName"));
  }
}
