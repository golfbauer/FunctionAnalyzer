package de.hhn.it.pp.components.vocabletrainer;

import de.hhn.it.pp.components.vocabletrainer.exceptions.TranslationIsEmptyException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryAlreadyExistException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryNotFoundException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocableNotFoundException;
import de.hhn.it.pp.components.vocabletrainer.provider.JBVocableTrainerService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DemoVocableTrainerUsage {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(DemoVocableTrainerUsage.class);

  public static void main(String[] args) {
    JBVocableTrainerService jbVocableTrainerService =
        new JBVocableTrainerService(); // new vocableTrainer instance

    List<Vocable> carVocabulary =
        new ArrayList<>(); // new vocabulary list for the car category
    carVocabulary.add(new Vocable("car", new String[] {"Auto", "Wagen", "Fahrzeug"}));
    carVocabulary.add(new Vocable("mirror", new String[] {"Spiegel", "Reflektor"}));
    carVocabulary.add(new Vocable("tire", new String[] {"Reifen", "Bereifung"}));
    carVocabulary.add(new Vocable("drive", new String[] {"fahren", "lenken"}));
    carVocabulary
        .add(new Vocable("license plate", new String[] {"Kennzeichenschild", "Nummernschild"}));

    HashMap<String, List<Vocable>> data =
        new HashMap<>(); // new HashMap for categories and their vocabulary lists
    data.put("car",
        carVocabulary); // add the car category to the HashMap using the carVocabulary vocabulary list
    jbVocableTrainerService.loadData(data); // load the example data into the component

    // show the score and the vocCategories in the GUI
    int score = jbVocableTrainerService.getScore();
    List<String> vocCategories = jbVocableTrainerService.getVocCategories();
    logger.debug("Score: " + score + "\nCategories: " + vocCategories);

    // add a new vocCategory to the component

    try {
      jbVocableTrainerService.addVocCategory("Computer", new ArrayList<>());
      logger.debug("Category successfully added");
    } catch (VocCategoryAlreadyExistException e) {
      logger.debug("Category failed to add");
      e.printStackTrace();
    }


    // Rename the vocCategory "Computer" into "VocCategory"
    try {
      jbVocableTrainerService.editVocCategory("Computer", "VocCategory");
      logger.debug("Category successfully edited");
    } catch (VocCategoryNotFoundException e) {
      logger.debug("Category failed to edit");
      e.printStackTrace();
    }

    // show the score and the vocCategories in the GUI
    score = jbVocableTrainerService.getScore();
    vocCategories = jbVocableTrainerService.getVocCategories();
    logger.debug("Score: " + score + "\nCategories: " + vocCategories);

    // add the word "hello" to the category "VocCategory"
    try {
      jbVocableTrainerService
          .addVocable("hello", new String[] {"hallo"}, "VocCategory");
      logger.debug("Vocable successfully added");
    } catch (VocCategoryNotFoundException | TranslationIsEmptyException e) {
      e.printStackTrace();
      logger.debug("Vocable failed to add");
    }

    // add the word "bye" to the category "VocCategory"
    try {
      jbVocableTrainerService
          .addVocable("bye", new String[] {"Tschüss"}, "VocCategory");
      logger.debug("Vocable successfully added");
    } catch (VocCategoryNotFoundException | TranslationIsEmptyException e) {
      e.printStackTrace();
      logger.debug("Vocable failed to add");
    }


    // show the score and the vocCategories in the GUI
    score = jbVocableTrainerService.getScore();
    vocCategories = jbVocableTrainerService.getVocCategories();
    logger.debug("Score: " + score + "\nCategories: " + vocCategories);

    List<Vocable> vocabulary =
        null; // print all vocabulary from category "VocCategory" on the screen
    try {
      vocabulary = jbVocableTrainerService.getVocabulary("VocCategory");
    } catch (VocCategoryNotFoundException e) {
      e.printStackTrace();
    }
    logger.debug("Score: " + score + "\nVocCategory: " + vocabulary);

    // Edit the word "hello" in the category "VocCategory"
    try {
      jbVocableTrainerService
          .editVocable(0, "hello", new String[] {"hallo", "moin"}, "VocCategory");
      logger.debug("Vocable successfully edit");
    } catch (VocableNotFoundException | VocCategoryNotFoundException | TranslationIsEmptyException e) {
      e.printStackTrace();
      logger.debug("Failed to edit the vocable");
    }


    // show the score and the vocCategories in the GUI
    score = jbVocableTrainerService.getScore();
    vocCategories = jbVocableTrainerService.getVocCategories();
    logger.debug("Score: " + score + "\nCategories: " + vocCategories);
    vocabulary =
        null; // print all vocabulary from category "VocCategory" on the screen
    try {
      vocabulary = jbVocableTrainerService.getVocabulary("VocCategory");
    } catch (VocCategoryNotFoundException e) {
      e.printStackTrace();
    }
    logger.debug("Score: " + score + "\nVocCategory: " + vocabulary);

    // Delete vocable "hello" in the category "VocCategory"
    try {
      jbVocableTrainerService.removeVocable(0, "VocCategory");
      logger.debug("Vocable successfully removed");
    } catch (VocableNotFoundException | VocCategoryNotFoundException e) {
      e.printStackTrace();
      logger.debug("Failed to remove the vocable");
    }


    // show the score and the vocCategories in the GUI
    score = jbVocableTrainerService.getScore();
    vocCategories = jbVocableTrainerService.getVocCategories();
    logger.debug("Score: " + score + "\nCategories: " + vocCategories);
    vocabulary =
        null; // print all vocabulary from category "VocCategory" on the screen
    try {
      vocabulary = jbVocableTrainerService.getVocabulary("VocCategory");
    } catch (VocCategoryNotFoundException e) {
      e.printStackTrace();
    }
    logger.debug("Score: " + score + "\nVocCategory: " + vocabulary);

    // Learn the words from the "VocCategory" category
    int sizeList = 0;
    try {
      sizeList = jbVocableTrainerService.getVocabulary("VocCategory").size();
    } catch (VocCategoryNotFoundException e) {
      e.printStackTrace();
    }
    List<Integer> falseVocabulary = new ArrayList<>(); // List of wrong vocabulary
    for (int i = 0; i < sizeList;
         i++) { // Iterate through the vocabulary list of the category "VocCategory"
      try {
        Vocable testVocable = jbVocableTrainerService
            .getVocable(i, "VocCategory"); // Get vocable that should be tested
        logger.debug("" + testVocable);
        boolean isCorrect =
            jbVocableTrainerService.checkVocable("userInput", i, "VocCategory",
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
      for (int i = 0; i < falseVocabulary.size();
           i++) { // Iterate through the vocabulary list of wrong vocabulary
        int id = falseVocabulary.get(i); // get id of the false vocable
        try {
          Vocable testVocable = jbVocableTrainerService
              .getVocable(id, "VocCategory"); // get vocable that should be tested
          logger.debug("" + testVocable);
          boolean isCorrect =
              jbVocableTrainerService.checkVocable("userInput", id, "VocCategory",
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

    // Remove the "VocCategory"

    try {
      jbVocableTrainerService.removeVocCategory("VocCategory");
      logger.debug("Category successfully removed");
    } catch (VocCategoryNotFoundException e) {
      e.printStackTrace();
      logger.debug("Failed to remove the Category");
    }
  }
}
