package de.hhn.it.pp.components.vocabletrainer;

public class DemoVocableTrainerUsage {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(DemoVocableTrainerUsage.class);

  public static void main(String[] args) {
      // - Benutzer, Score, Benutzerliste anzeigen
    // Benutzer hinzufügen
    // - Benutzerbearbeitung
    // Bestätigen
    // - Benutzer, Score, Benutzerliste
    // Benutzer ändern
    // - Benutzerbearbeitung
    // Benutzer auswählen
    // Betätigen
    // - Benutzer, Score, Kategorieliste
    // Kategorie hinzufügen
    // - Kategoriebearbeitung
    // SaveAndNew
    // 2. Kategorie hinzufügen
    // - Kategoriebearbeitung
    // Save
    // 2. Kategorie ändern
    // - Kategoriebearbeitung
    // Save
    // - Benutzer, Score, Kategorieliste
    // 1. Kategorie auswählen
    // - Benutzer, Score, Kategorie, Vokabelliste
    // 1. Vokabel hinzufügen
    // - Vokabelbearbeitung
    // Save and New
    // 2. Vokabel hinzufügen
    // - Vokabelbearbeitung
    // Save and New
    // 3. Vokabel hinzufügen
    // - Vokabelbearbeitung
    // Save
    // - Benutzer, Score, Kategorie, Vokabelliste
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
