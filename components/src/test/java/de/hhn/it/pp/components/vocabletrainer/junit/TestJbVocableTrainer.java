package de.hhn.it.pp.components.vocabletrainer.junit;

import static org.junit.jupiter.api.Assertions.assertThrows;


import de.hhn.it.pp.components.vocabletrainer.Vocable;
import de.hhn.it.pp.components.vocabletrainer.exceptions.TranslationIsEmptyException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryAlreadyExistException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryNotFoundException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocableNotFoundException;
import de.hhn.it.pp.components.vocabletrainer.provider.JbVocableTrainer;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test for JbVocableTrainer")
public class TestJbVocableTrainer {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(TestVocableTrainerServiceBadCases.class);

  JbVocableTrainer jbVocableTrainer;
  List<Vocable> testList;
  Vocable testVocable;

  @BeforeEach
  void setup() throws TranslationIsEmptyException, VocCategoryAlreadyExistException {
    testList = new ArrayList<>();
    testVocable =
        new Vocable("Auto", new String[] {"car", "vehicle", "motorcar", "automobile", "auto"});
    testList.add(testVocable);
    testList.add(new Vocable("Spiegel", new String[] {"mirror", "glass", "reflector"}));
    testList.add(new Vocable("Reifen", new String[] {"tire", "hoop", "maturation"}));
    testList.add(new Vocable("Lenkrad", new String[] {"wheel", "steering wheel"}));
    testList.add(new Vocable("Autositz", new String[] {"car seat", "seat"}));

    jbVocableTrainer = new JbVocableTrainer();
    jbVocableTrainer.addVocCategory("Auto", testList);
  }

  @Test
  @DisplayName("Remove vocable from not existent vocCategory")
  public void testExceptionWhenRemovingVocableFromNotExistentVocCategory() {
    VocCategoryNotFoundException vocCategoryNotFoundException =
        assertThrows(VocCategoryNotFoundException.class, () ->
            jbVocableTrainer.removeVocable("NotExistentVocCategoryName", testVocable));
  }

  @Test
  @DisplayName("Create an already existent VocCategory")
  public void testExceptionWhenVocableNotFound() {
    VocableNotFoundException vocableNotFoundException =
        assertThrows(VocableNotFoundException.class, () ->
            jbVocableTrainer.removeVocable("Auto", new Vocable("test", new String[] {"test"})));
  }
}
