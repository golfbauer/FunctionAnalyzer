package de.hhn.it.pp.components.typingtrainer;

import java.io.File;
import java.io.FileNotFoundException;

/***
 * @author Tobias Maraci, Robert Pistea
 */

public class DemoTypingTrainerUsage implements I_Interaction{

    public static void main(String[] args) {

    }

    /***
     * Überprüft das geschriebene Wort auf Richtigkeit
     * @param word : Wort das überprüft werden soll
     * @return : true, wenn Wort richtig ist
     * @throws WordNotFoundException
     */
    @Override
    public boolean checkWord(String word) throws WordNotFoundException {
        return false;
    }

    /***
     * Spielt einen Ton ab.
     * @param soundFile : Ton der abgespielt wird
     * @throws FileNotFoundException
     */
    @Override
    public void audioOutput(File soundFile) throws FileNotFoundException {

    }

    /***
     * Markiert ein Wort
     * @param word : Wort das markiert wird
     * @throws WordNotFoundException
     */
    @Override
    public void markWork(String word) throws WordNotFoundException {

    }

    /***
     * Wählt Text aus der geübt werden soll
     * @param selectedText : Ausgewählter Text
     */
    @Override
    public void selectionOfText(String selectedText) {

    }

    /***
     * Beendet eine Übungsphase.
     */
    @Override
    public void quitSession() {

    }

    /***
     * Zeigt dem User seinen Score (Zeit wie lang er für den Text gebraucht hat, WPM) an
     * @param feedback
     */
    @Override
    public void showFeedback(Feedback feedback) {

    }

    /***
     * Speichert den Fortschritt des Users in eine txt File
     * @param saveFile : File in der es gespeichert wird
     * @throws FileNotFoundException
     */
    @Override
    public void saveScore(File saveFile) throws FileNotFoundException {

    }

    /***
     * Lädt die txt File falls sie existiert
     * @param saveFile : File die geladen werden soll
     * @throws FileNotFoundException
     */
    @Override
    public void loadScore(File saveFile) throws FileNotFoundException {

    }
}
