package de.hhn.it.pp.components.vocabletrainer;

public class DemoVocableTrainerUsage {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(DemoVocableTrainerUsage.class);
  
  public static void main(String[] args) {
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
