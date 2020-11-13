package de.hhn.it.pp.components.vocabletrainer;

import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryNotFoundException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocableNotFoundException;
import de.hhn.it.pp.components.vocabletrainer.provider.JBVocableTrainerService;
import java.util.ArrayList;

public class DemoVocableTrainerUsage {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(DemoVocableTrainerUsage.class);

  public static void main(String[] args) {
    JBVocableTrainerService jbVocableTrainerService = new JBVocableTrainerService();
    Vocable

    // show  score, vocCategories
    int score = jbVocableTrainerService.getScore();
    ArrayList<VocCategory> vocCategories = jbVocableTrainerService.getVocCategories();
    logger.debug(score + "\n" + vocCategories);

    // Add vocCategory 1
    boolean sucess = jbVocableTrainerService.addVocCategory("Category 1");
    if (sucess) {
      logger.debug("successfully added");
    } else {
      logger.debug("adding failed");
    }

    // Add vocCategory 2
    sucess = jbVocableTrainerService.addVocCategory("Category 2");
    if (sucess) {
      logger.debug("successfully added");
    } else {
      logger.debug("adding failed");
    }

    // Edit vocCategory 2
    try {
      sucess = jbVocableTrainerService.editVocCategory(0, "new VocCategoryName");
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

    // Select vocCategory 1
    boolean isSelected = false;
    try {
      isSelected = jbVocableTrainerService.selectVocCategory(0);
    } catch (VocCategoryNotFoundException e) {
      e.printStackTrace();
    }
    if (isSelected) {
      logger.debug("VocCategory successfully selected");
    } else {
      logger.debug("Selecting failed");
    }

    // show  score, vocCategory, vocabularies
    score = jbVocableTrainerService.getScore();
    VocCategory vocCategory = jbVocableTrainerService.getVocCategory();
    ArrayList<Vocable> vocabulary = jbVocableTrainerService.getVocabulary();
    logger.debug(score + "\n" + vocCategory + ": " + vocabulary);

    // Add vocable 1
    try {
      sucess = jbVocableTrainerService
          .addVocable("hello1", "hallo1", jbVocableTrainerService.getVocCategory());
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
          .addVocable("hello2", "hallo2", jbVocableTrainerService.getVocCategory());
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
    vocCategory = jbVocableTrainerService.getVocCategory();
    vocabulary = jbVocableTrainerService.getVocabulary();
    logger.debug(score + "\n" + vocCategory + ": " + vocabulary);

    // Edit vocable 2
    try {
      sucess = jbVocableTrainerService
          .editVocable(1, "hello 2", "hallo 2", jbVocableTrainerService.getVocCategory().getId());
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
    vocCategory = jbVocableTrainerService.getVocCategory();
    vocabulary = jbVocableTrainerService.getVocabulary();
    logger.debug(score + "\n" + vocCategory + ": " + vocabulary);

    // Delete vocable 1
    try {
      sucess = jbVocableTrainerService.removeVocable(0);
    } catch (VocableNotFoundException e) {
      e.printStackTrace();
    }
    if (sucess) {
      logger.debug("successfully removed the vocable");
    } else {
      logger.debug("removing the vocable failed");
    }

    // show score, vocCategory, vocabularies
    score = jbVocableTrainerService.getScore();
    vocCategory = jbVocableTrainerService.getVocCategory();
    vocabulary = jbVocableTrainerService.getVocabulary();
    logger.debug(score + "\n" + vocCategory + ": " + vocabulary);

    // learn vocabulary
    learningSelection[] learnSel = new learningSelection[]{learningSelection.ALL};

    boolean ready = jbVocableTrainerService.learn(learnSel);
    if (ready) {
      logger.debug("You can learn all");
    } else {
      logger.debug("failed to load Vocable");
    }
    String voc = jbVocableTrainerService.getVocable().getOriginWord();
    logger.debug("" + voc);

    // test vocable 1
    boolean isCorrect = jbVocableTrainerService.isVocableCorrect("helloo");
    if (isCorrect) {
      logger.debug("Vocable is correct");
    } else {
      logger.debug("Vocable is false");
    }

    // skip vocable 2
    String origin = jbVocableTrainerService.getVocable().getOriginWord();
    logger.debug(origin);
    jbVocableTrainerService.skip();
    logger.debug("Vocable skipped");

    // evaluation

    score = jbVocableTrainerService.getScore();
    logger.debug("\nnew Score: " + score);

    // show score, vocCategory, vocabularies
    score = jbVocableTrainerService.getScore();
    vocCategory = jbVocableTrainerService.getVocCategory();
    vocabulary = jbVocableTrainerService.getVocabulary();
    logger.debug(": " + score + "\n" + vocCategory + ": " + vocabulary);

    // learn vocabulary
    learningSelection[] learnSelWrong = new learningSelection[]{learningSelection.WRONG};
    jbVocableTrainerService.learn(learnSelWrong);
    // cancel learn
    jbVocableTrainerService.learn(learningSelection.CANCEL);

    //remove Category
    boolean isRemoved = false;
    try {
      isRemoved = jbVocableTrainerService.removeVocCategory(0);
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
