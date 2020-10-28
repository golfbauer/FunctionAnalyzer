package de.hhn.it.pp.components.vocabletrainer;

import de.hhn.it.pp.components.exceptions.IllegalParameterException;
import java.util.ArrayList;

public class DemoVocableTrainerUsage {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(DemoVocableTrainerUsage.class);

  public static void main(String[] args) throws IllegalParameterException {
    JBVocableTrainerService jbVocableTrainerService = new JBVocableTrainerService();

    // Home page
    ArrayList<User> users = jbVocableTrainerService.getUsers();
    logger.debug("Users: " + users);

    // Add user
    boolean sucess = jbVocableTrainerService.addUser("test");
    if (sucess) {
      logger.debug("User successfully added");
    } else {
      logger.debug("Failed to add user");
    }

    // Home page
    users = jbVocableTrainerService.getUsers();
    logger.debug("Users: " + users);

    // Edit user
    sucess = jbVocableTrainerService.editUser(0, "Tom");
    if (sucess) {
      logger.debug("User successfully saved");
    } else {
      logger.debug("Failed to save the user");
    }

    // Home page
    users = jbVocableTrainerService.getUsers();
    logger.debug("Users: " + users);

    // Select user
    boolean isSelected = jbVocableTrainerService.selectUser(0);
    if (isSelected) {
      logger.debug("successfully selected");
    } else {
      logger.debug("selecting failed");
    }
    // show selected user, score, vocCategories
    String name = jbVocableTrainerService.getUser().getName();
    int score = jbVocableTrainerService.getUser().getScore();
    ArrayList<VocCategory> vocCategories = jbVocableTrainerService.getVocCategories();
    logger.debug(name + ": " + score + "\n" + vocCategories);

    // Add vocCategory 1
    sucess = jbVocableTrainerService.addVocCategory("Category 1");
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
    sucess = jbVocableTrainerService.editVocCategory(0, "new VocCategoryName");
    if (sucess) {
      logger.debug("successfully added");
    } else {
      logger.debug("adding failed");
    }

    // show selected user, score, vocCategories
    name = jbVocableTrainerService.getUser().getName();
    score = jbVocableTrainerService.getUser().getScore();
    vocCategories = jbVocableTrainerService.getVocCategories();
    logger.debug(name + ": " + score + "\n" + vocCategories);

    // Select vocCategory 1
    isSelected = jbVocableTrainerService.selectVocCategory(0);
    if (isSelected) {
      logger.debug("VocCategory successfully selected");
    } else {
      logger.debug("Selecting failed");
    }

    // show user, score, vocCategory, vocabularies
    name = jbVocableTrainerService.getUser().getName();
    score = jbVocableTrainerService.getUser().getScore();
    VocCategory vocCategory = jbVocableTrainerService.getVocCategory();
    ArrayList<Vocable> vocabulary = jbVocableTrainerService.getVocabulary();
    logger.debug(name + ": " + score + "\n" + vocCategory + ": " + vocabulary);

    // Add vocable 1
    sucess = jbVocableTrainerService
        .addVocable("hello1", "hallo1", jbVocableTrainerService.getVocCategory());
    if (sucess) {
      logger.debug("Vocable successfully added");
    } else {
      logger.debug("Adding vocabulary failed");
    }

    // Add vocable 2
    sucess = jbVocableTrainerService
        .addVocable("hello2", "hallo2", jbVocableTrainerService.getVocCategory());
    if (sucess) {
      logger.debug("Vocable successfully added");
    } else {
      logger.debug("Adding vocabulary failed");
    }

    // show user, score, vocCategory, vocabularies
    name = jbVocableTrainerService.getUser().getName();
    score = jbVocableTrainerService.getUser().getScore();
    vocCategory = jbVocableTrainerService.getVocCategory();
    vocabulary = jbVocableTrainerService.getVocabulary();
    logger.debug(name + ": " + score + "\n" + vocCategory + ": " + vocabulary);

    // Edit vocable 2
    sucess = jbVocableTrainerService
        .editVocable(1, "hello 2", "hallo 2", jbVocableTrainerService.getVocCategory().getId());
    if (sucess) {
      logger.debug("Vocable successful edit");
    } else {
      logger.debug("Edit vocabulary failed");
    }

    // show user, score, vocCategory, vocabularies
    name = jbVocableTrainerService.getUser().getName();
    score = jbVocableTrainerService.getUser().getScore();
    vocCategory = jbVocableTrainerService.getVocCategory();
    vocabulary = jbVocableTrainerService.getVocabulary();
    logger.debug(name + ": " + score + "\n" + vocCategory + ": " + vocabulary);

    // Delete vocable 1
    sucess = jbVocableTrainerService.removeVocable(0);
    if (sucess) {
      logger.debug("successfully removed the vocable");
    } else {
      logger.debug("removing the vocable failed");
    }

    // show user, score, vocCategory, vocabularies
    name = jbVocableTrainerService.getUser().getName();
    score = jbVocableTrainerService.getUser().getScore();
    vocCategory = jbVocableTrainerService.getVocCategory();
    vocabulary = jbVocableTrainerService.getVocabulary();
    logger.debug(name + ": " + score + "\n" + vocCategory + ": " + vocabulary);

    // learn vocabulary
    boolean ready = jbVocableTrainerService.learn(learningSelection.ALL); // Enum class?
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
    jbVocableTrainerService.getVocable().getOriginWord();
    jbVocableTrainerService.skip();
    logger.debug("Vocable skipped");

    // evaluation
    name = jbVocableTrainerService.getUser().getName();
    score = jbVocableTrainerService.getUser().getScore();
    logger.debug(name + "\nnew Score: " + score);

    // show user, score, vocCategory, vocabularies
    name = jbVocableTrainerService.getUser().getName();
    score = jbVocableTrainerService.getUser().getScore();
    vocCategory = jbVocableTrainerService.getVocCategory();
    vocabulary = jbVocableTrainerService.getVocabulary();
    logger.debug(name + ": " + score + "\n" + vocCategory + ": " + vocabulary);

    // learn vocabulary
    jbVocableTrainerService.learn(learningSelection.WRONG);
    // cancle learn
    jbVocableTrainerService.learn(learningSelection.CANCLE);

    // TODO: 28.10.2020 removeUser, removeVocCategory, selectVocable
    boolean isRemoved = jbVocableTrainerService.removeUser(0);
    isRemoved = jbVocableTrainerService.removeVocCategory(0);
    isSelected = jbVocableTrainerService.selectVocable(0); // used?
    // - Benutzer, Score, Kategorie, Vokabelliste
    // Zurück
    // - Benutzer, Score, Kategorie, Vokabelliste
    // Vokabel hinzufügen
    // - Vokabelbearbeitung
    // Abbrechen
    // - Benutzer, Score, Kategorie, Vokabelliste
    // Zurück
    // - Benutzer, Score, Kategorieliste
    // Kategorie hinzufügen
    // - Vokabelbearbeitung
    // Abbrechen
    // - Benutzer, Score, Kategorieliste
    // Zurück
    // - Benutzer, Score, Benutzerliste
    // Benutzer hinzufügen
    // - Benutzerbearbeitung
    // Abbrechen

    // Homepage
    // pick -> add, choose (edit, delete) user, OK;
    // show -> User list (score list), (selected) user, score

    // Category page
    // pick -> add, choose (edit, delete) category, OK, Back;
    // show -> Category list, user, score

    // Vocabulary page
    // pick -> add, choose (edit, delete) vocabulary; Category (Set) learn everything / wrong, back;
    // show -> Selected category, vocabulary list, indication of whether vocabulary has already been learned, answered
    // incorrectly or correctly, user, score

    // edit (add) User
    // pick -> Text field (name), OK, Cancel;
    // show -> User name

    // edit (add) Category
    // pick -> Text field (name), OK; Cancel;
    // show -> Category name

    // edit (add) Vocabulary
    // pick -> Text field (untranslated), text field (translation) , OK, Cancel;
    // show -> Vocabulary name, category

    // Learn page
    // pick -> Text field, OK, Skip, Cancel;
    // show -> Vocabulary untranslated, user, score, (at skip -> answer)

    // Done with learning
    // pick -> OK
    // show -> User, new Score, wrong/correct words

    // Delete page
    // pick -> OK, Cancel;
    // show -> "Are you sure?"

  }
}
