package de.hhn.it.pp.components.vocabletrainer.junit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.hhn.it.pp.components.example.coffeemakerservice.junit.TestCoffeeMakerServiceGoodCases;
import de.hhn.it.pp.components.vocabletrainer.LearningState;
import de.hhn.it.pp.components.vocabletrainer.Vocable;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryAlreadyExistException;
import de.hhn.it.pp.components.vocabletrainer.provider.JbVocableTrainer;
import de.hhn.it.pp.components.vocabletrainer.provider.JbVocableTrainerService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("Test the VocableTrainer with good cases.")
public class TestVocableTrainerServiceGoodCases {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(TestCoffeeMakerServiceGoodCases.class);


  LearningState learningState;
  JbVocableTrainerService jbVocableTrainerService = new JbVocableTrainerService();

  @BeforeEach
  void setUp() throws VocCategoryAlreadyExistException {


    List<Vocable> testList = new ArrayList<>();
    testList.add(
        new Vocable("Auto", new String[] {"car", "vehicle", "motorcar", "automobile", "auto"}));
    testList.add(new Vocable("Spiegel", new String[] {"mirror", "glass", "reflector"}));
    testList.add(new Vocable("Reifen", new String[] {"tire", "hoop", "maturation"}));
    testList.add(new Vocable("Lenkrad", new String[] {"wheel", "steering wheel"}));
    testList.add(new Vocable("Autositz", new String[] {"car seat", "seat"}));

    HashMap<String, List<Vocable>> testMap = new HashMap<>();
    testMap.put("Auto", testList);
    learningState = new LearningState();
    learningState.setScore(10);
    learningState.setVocabularyList(testMap);
    jbVocableTrainerService.addVocCategory("Auto", testList);
    jbVocableTrainerService.loadData(learningState);


  }

  @Test
  void TesterGetterScore(){
    assertEquals(10,learningState.getScore());
  }

}
