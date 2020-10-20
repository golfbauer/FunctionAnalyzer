package de.hhn.it.pp.components.typingtrainer;

import java.io.File;
import java.io.FileNotFoundException;

/***
 * @author Tobias Maraci, Robert Pistea
 */

public interface I_Interaction {
    /***
     * Überprüft das geschriebene Wort auf Richtigkeit
     * @param word: Wort das überprüft werden soll
     * @return : true, wenn Wort richtig ist
     * @throws WordNotFoundException
     */
    boolean checkWord(String word) throws WordNotFoundException;

    /***
     * Spielt einen Ton ab.
     * @param soundFile: Ton der abgespielt wird
     * @throws FileNotFoundException
     */
    void audioOutput(File soundFile) throws FileNotFoundException;

    /***
     * Markiert ein Wort
     * @param word: Wort das markiert wird
     * @throws WordNotFoundException
     */
    void markWork(String word) throws WordNotFoundException;

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
     * Zeigt dem User seinen Score (Zeit wie lang er für den Text gebraucht hat, WPM) an
     * @param feedback
     */
    void showFeedback(Feedback feedback);

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