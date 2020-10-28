package de.hhn.it.pp.components.vocabletrainer;

import de.hhn.it.pp.components.exceptions.IllegalParameterException;

public class DemoVocableTrainerUsage {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(DemoVocableTrainerUsage.class);

  public static void main(String[] args) throws IllegalParameterException {
    JBVocableTrainerService jbVocableTrainerService = new JBVocableTrainerService();
    // - Benutzer, Score, Benutzerliste anzeigen
    // Benutzer hinzufügen
    jbVocableTrainerService.addUser("");
    // - Benutzerbearbeitung
    // Bestätigen
    jbVocableTrainerService.ok();
    // - Benutzer, Score, Benutzerliste
    // Benutzer ändern
    jbVocableTrainerService.selectUser(0);
    // - Benutzerbearbeitung
    // Benutzer auswählen
    // Betätigen
    jbVocableTrainerService.selectUser(0);
    jbVocableTrainerService.ok();
    // - Benutzer, Score, Kategorieliste
    // Kategorie hinzufügen
    jbVocableTrainerService.addVocCategory("");
    // - Kategoriebearbeitung
    // SaveAndNew
    jbVocableTrainerService.ok();
    // 2. Kategorie hinzufügen
    // - Kategoriebearbeitung
    // Save
    jbVocableTrainerService.addVocCategory("");
    jbVocableTrainerService.ok();

    // 2. Kategorie ändern
    // - Kategoriebearbeitung
    // Save
    jbVocableTrainerService.removeVocCategory(jbVocableTrainerService.selectVocCategory());
    jbVocableTrainerService.addVocCategory("");
    jbVocableTrainerService.ok();

    // - Benutzer, Score, Kategorieliste
    // 1. Kategorie auswählen
    jbVocableTrainerService.selectVocCategory(0);
    // - Benutzer, Score, Kategorie, Vokabelliste
    // 1. Vokabel hinzufügen
    jbVocableTrainerService.addVocable("","", jbVocableTrainerService.getVocCategory());

    // - Vokabelbearbeitung
    // Save and New
    jbVocableTrainerService.selectVocable(0);
    jbVocableTrainerService.removeVocable(0);
    jbVocableTrainerService.addVocable("","", jbVocableTrainerService.getVocCategory());
    jbVocableTrainerService.ok();

    // 2. Vokabel hinzufügen
    // - Vokabelbearbeitung
    // Save and New

    // 3. Vokabel hinzufügen
    // - Vokabelbearbeitung
    // Save

    // - Benutzer, Score, Kategorie, Vokabelliste


    ------------------------------------------------------------
    // 2. Vokabel ändern
    // - Vokabelbearbeitung
    // Bestätigen
    // - Benutzer, Score, Kategorie, Vokabelliste
    // 3. Vokabel löschen
    // - Benutzer, Score, Kategorie, Vokabelliste
    // Vokabeln lernen
    // 1. Vokabel prüfen
    // 2. Vokabel skip
    // - Score, Vokabeln, Kategorie
    // Auswertung bestätigen
    // - Benutzer, Score, Kategorie, Vokabelliste
    // Vokabeln lernen
    // lernen abbrechen
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
