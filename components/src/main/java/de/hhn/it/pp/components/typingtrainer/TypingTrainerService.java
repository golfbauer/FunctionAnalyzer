package de.hhn.it.pp.components.typingtrainer;

import java.io.File;
import java.io.FileNotFoundException;

/***
 * @author Tobias Maraci, Robert Pistea
 */

public interface TypingTrainerService {
    /***
     * Überprüft das geschriebene Wort auf Richtigkeit
     * @param word: Wort das überprüft werden soll
     * @return : true, wenn Wort richtig ist
     * @throws WordNotFoundException
     */
    boolean checkWord(String word) throws WordNotFoundException;

    /***
     * Spielt einen Ton ab, wenn Wort falsch ist.
     * @param soundFile: Ton der abgespielt wird
     * @throws FileNotFoundException
     */
    void audioOutput(File soundFile) throws FileNotFoundException;

    /***
     * Markiert ein Wort, welches richtig geschrieben ist
     * @param word: Wort das markiert wird
     * @throws WordNotFoundException
     */
    void markWord(String word) throws WordNotFoundException;

    /***
     * Wählt Text aus der geübt werden soll
     * @param selectedText: Ausgewählter Text
     */
    void selectionOfText(String selectedText);

    /***
     * Beendet eine Übungsphase.
     */
    void quitSession();

    /***
     * Zeigt Feedback an
     * @param time Gesamtzeit zum absolvieren
     * @param wordsPerMinute Wörter die in der Minute richtig geschrieben wurden
     */
    void showFeedback(float time, float wordsPerMinute);

    /***
     * Speichert den Fortschritt des Users in eine txt File
     * @param saveFile: File in der es gespeichert wird
     * @throws FileNotFoundException
     */
     void saveScore(File saveFile) throws FileNotFoundException;

    /***
     * Lädt die txt File falls sie existiert
     * @param saveFile: File die geladen werden soll
     * @throws FileNotFoundException
     */
     void loadScore(File saveFile) throws FileNotFoundException;

}