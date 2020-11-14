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
    JBVocableTrainerService jbVocableTrainerService =
        new JBVocableTrainerService(); // new VocableTrainerInstance

    ArrayList<Vocable> vocables = new ArrayList<>(); // Create list for sample vocabulary data
    vocables.add(new Vocable("Auto", "car"));
    vocables.add(new Vocable("Spiegel", "mirror"));
    vocables.add(new Vocable("Reifen", "tire"));
    vocables.add(new Vocable("fahren", "drive"));
    vocables.add(new Vocable("Nummernschild", "license plate"));

    HashMap<String, ArrayList<Vocable>> data = new HashMap<>(); // HashMap for category names with their vocabulary list
    data.put("car", vocables); // new category car with vocables from ArrayList vocables
    jbVocableTrainerService.loadData(data); // load data into the component

    // show score and vocCategories in the GUI
    int score = jbVocableTrainerService.getScore();
    ArrayList<String> vocCategories = jbVocableTrainerService.getVocCategories();
    logger.debug(score + "\n" + vocCategories);

    // Add new vocCategory to the component
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

    // Edit vocCategory Computer
    try {
      sucess = jbVocableTrainerService.editVocCategory("Computer", "VocCategory2");
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
          .addVocable("hello1", "hallo1", "VocCategory2");
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
          .addVocable("hello2", "hallo2", "VocCategory2");
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
      vocabulary = jbVocableTrainerService.getVocabulary("VocCategory2");
    } catch (VocCategoryNotFoundException e) {
      e.printStackTrace();
    }
    logger.debug(score + "\n" + "VocCategory2: " + vocabulary);

    // Edit vocable 2
    try {
      sucess = jbVocableTrainerService
          .editVocable(0, "hello 2", new String[] {"hallo 2"}, "VocCategory2");
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
      vocabulary = jbVocableTrainerService.getVocabulary("VocCategory2");
    } catch (VocCategoryNotFoundException e) {
      e.printStackTrace();
    }
    logger.debug(score + "\n" + "VocCategory2: " + vocabulary);

    // Delete vocable 1
    try {
      sucess = jbVocableTrainerService.removeVocable(0, "VocCategory2");
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
      vocabulary = jbVocableTrainerService.getVocabulary("VocCategory2");
    } catch (VocCategoryNotFoundException e) {
      e.printStackTrace();
    }
    logger.debug(score + "\n" + "VocCategory2: " + vocabulary);

    // learn vocabulary + check if is correct
    int sizeList = 0;
    try {
      sizeList = jbVocableTrainerService.getVocabulary("VocCategory2").size();
    } catch (VocCategoryNotFoundException e) {
      e.printStackTrace();
    }

    ArrayList<Integer> falseVocabulary = new ArrayList<>(); // ArrayList of false vocabulary

    for (int i = 0; i < sizeList; i++) {
      try {
        Vocable testVocable = jbVocableTrainerService
            .getVocable(i, "VocCategory2"); // Get vocable that should be tested
        logger.debug("" + testVocable);
        boolean isCorrect =
            jbVocableTrainerService.checkVocable("userInput", i, "VocCategory2",
                2); // Check the input of the user

        if (isCorrect) {
          logger.debug("Vocable is correct");
        } else {
          falseVocabulary.add(i); // Add the id of the false vocable to the falseVocabulary list
          logger.debug("Vocable is false");
        }
      } catch (VocableNotFoundException | VocCategoryNotFoundException e) {
        e.printStackTrace();
      }
    }
    while (falseVocabulary.size() != 0) { // repeat the false vocabulary
      for (int i = 0; i < falseVocabulary.size(); i++) {
        int id = falseVocabulary.get(i); // get id of the false vocable
        try {
          Vocable testVocable = jbVocableTrainerService
              .getVocable(id, "VocCategory2"); // get vocable that should be tested
          logger.debug("" + testVocable);
          boolean isCorrect =
              jbVocableTrainerService.checkVocable("userInput", id, "VocCategory2",
                  2); // check the input of the user

          if (isCorrect) {
            falseVocabulary.remove(i); // Remove right vocable from the falseVocabulary list
            logger.debug("Vocable is correct");
          } else {
            logger.debug("Vocable is false");
          }
        } catch (VocableNotFoundException | VocCategoryNotFoundException e) {
          e.printStackTrace();
        }
      }
    }

    // evaluation
    score = jbVocableTrainerService.getScore(); // show the score in the GUI
    logger.debug("\nnew Score: " + score);

    // show vocCategory vocabularies
    try {
      vocabulary = jbVocableTrainerService.getVocabulary("VocCategory2");
    } catch (VocCategoryNotFoundException e) {
      e.printStackTrace();
    }
    logger.debug("Score: " + score + "\n" + "VocCategory2: " + vocabulary);

    // remove category
    boolean isRemoved = false;
    try {
      isRemoved = jbVocableTrainerService.removeVocCategory("VocCategory2");
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
