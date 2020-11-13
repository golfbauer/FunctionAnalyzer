package de.hhn.it.pp.components.vocabletrainer;

import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryAlreadyExistException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryNotFoundException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocableNotFoundException;
import de.hhn.it.pp.components.vocabletrainer.provider.JBVocableTrainerService;
import java.util.ArrayList;
import java.util.HashMap;

public class DemoVocableTrainerUsage {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(DemoVocableTrainerUsage.class);

  public static void main(String[] args) {
    JBVocableTrainerService jbVocableTrainerService = new JBVocableTrainerService();
    ArrayList<Vocable> vocables = new ArrayList<>();
    HashMap<String, ArrayList<Vocable>> data = new HashMap<>();
    vocables.add(new Vocable("Auto", "car"));
    vocables.add(new Vocable("Spiegel", "mirror"));
    vocables.add(new Vocable("Reifen", "tire"));
    vocables.add(new Vocable("fahren", "drive"));
    vocables.add(new Vocable("Nummernschild", "license plate"));
    data.put("car", vocables);

    //load component
    jbVocableTrainerService.loadData(data);

    // show  score, vocCategories
    int score = jbVocableTrainerService.getScore();
    ArrayList<String> vocCategories = jbVocableTrainerService.getVocCategories();
    logger.debug(score + "\n" + vocCategories);

    // Add vocCategory 1
    boolean sucess = false;
    try {
      sucess = jbVocableTrainerService.addVocCategory("Computer", new ArrayList<>());
    } catch (VocCategoryAlreadyExistException e) {
      e.printStackTrace();
    }
    if (sucess) {
      logger.debug("successfully added");
    } else {
      logger.debug("adding failed");
    }

    // Edit vocCategory 2
    try {
      sucess = jbVocableTrainerService.editVocCategory("Computer", "new VocCategoryName");
    } catch (VocCategoryNotFoundException e) {
      e.printStackTrace();
    }
    if (sucess) {
      logger.debug("successfully added");
    } else {
      logger.debug("adding failed");
    }

    // show score, vocCategories
    score = jbVocableTrainerService.getScore();
    vocCategories = jbVocableTrainerService.getVocCategories();
    logger.debug(score + "\n" + vocCategories);

    // Add vocable 1
    try {
      sucess = jbVocableTrainerService
          .addVocable("hello1", "hallo1", "new VocCategoryName");
    } catch (VocCategoryNotFoundException e) {
      e.printStackTrace();
    }
    if (sucess) {
      logger.debug("Vocable successfully added");
    } else {
      logger.debug("Adding vocabulary failed");
    }

    // Add vocable 2
    try {
      sucess = jbVocableTrainerService
          .addVocable("hello2", "hallo2", "new VocCategoryName");
    } catch (VocCategoryNotFoundException e) {
      e.printStackTrace();
    }
    if (sucess) {
      logger.debug("Vocable successfully added");
    } else {
      logger.debug("Adding vocabulary failed");
    }

    // show score, vocCategory, vocabularies
    score = jbVocableTrainerService.getScore();
    ArrayList<Vocable> vocabulary = null;
    try {
      vocabulary = jbVocableTrainerService.getVocabulary("new VocCategoryName");
    } catch (VocCategoryNotFoundException e) {
      e.printStackTrace();
    }
    logger.debug(score + "\n" + "new VocCategoryName: " + vocabulary);

    // Edit vocable 2
    try {
      sucess = jbVocableTrainerService
          .editVocable(0, "hello 2", new String[] {"hallo 2"}, "new VocCategoryName");
    } catch (VocableNotFoundException | VocCategoryNotFoundException e) {
      e.printStackTrace();
    }
    if (sucess) {
      logger.debug("Vocable successful edit");
    } else {
      logger.debug("Edit vocabulary failed");
    }

    // show score, vocCategory, vocabularies
    score = jbVocableTrainerService.getScore();
    try {
      vocabulary = jbVocableTrainerService.getVocabulary("new VocCategoryName");
    } catch (VocCategoryNotFoundException e) {
      e.printStackTrace();
    }
    logger.debug(score + "\n" + "new VocCategoryName: " + vocabulary);

    // Delete vocable 1
    try {
      sucess = jbVocableTrainerService.removeVocable(0, "new VocCategoryName");
    } catch (VocableNotFoundException | VocCategoryNotFoundException e) {
      e.printStackTrace();
    }
    if (sucess) {
      logger.debug("successfully removed the vocable");
    } else {
      logger.debug("removing the vocable failed");
    }

    // show score, vocCategory, vocabularies
    score = jbVocableTrainerService.getScore();
    try {
      vocabulary = jbVocableTrainerService.getVocabulary("new VocCategoryName");
    } catch (VocCategoryNotFoundException e) {
      e.printStackTrace();
    }
    logger.debug(score + "\n" + "new VocCategoryName: " + vocabulary);

    // learn vocabulary + check if is correct
    int sizeList = 0;
    try {
      sizeList = jbVocableTrainerService.getVocabulary("new VocCategoryName").size();
    } catch (VocCategoryNotFoundException e) {
      e.printStackTrace();
    }
    for (int i = 0; i < sizeList; i++) {
      try {
        jbVocableTrainerService.getVocable(i, "new VocCategoryName");
        boolean isCorrect =
            jbVocableTrainerService.checkVocable("userInput", i, "new VocCategoryName", 2);

        if (isCorrect) {
          logger.debug("Vocable is correct");
        } else {
          logger.debug("Vocable is false");
        }

      } catch (VocableNotFoundException | VocCategoryNotFoundException e) {
        e.printStackTrace();
      }
    }

    // evaluation
    score = jbVocableTrainerService.getScore();
    logger.debug("\nnew Score: " + score);

    // show score, vocCategory, vocabularies
    score = jbVocableTrainerService.getScore();
    try {
      vocabulary = jbVocableTrainerService.getVocabulary("new VocCategoryName");
    } catch (VocCategoryNotFoundException e) {
      e.printStackTrace();
    }
    logger.debug(": " + score + "\n" + "new VocCategoryName: " + vocabulary);


    //remove Category
    boolean isRemoved = false;
    try {
      isRemoved = jbVocableTrainerService.removeVocCategory("new VocCategoryName");
    } catch (VocCategoryNotFoundException e) {
      e.printStackTrace();
    }
    if (isRemoved) {
      logger.debug("Category successfully removed");
    } else {
      logger.debug("Failed to remove the Category");
    }

  }
}
