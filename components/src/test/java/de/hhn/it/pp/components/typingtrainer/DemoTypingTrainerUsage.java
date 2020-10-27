package de.hhn.it.pp.components.typingtrainer;

import javax.print.attribute.standard.Media;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;

/***
 * @author Tobias Maraci, Robert Pistea
 */

public class DemoTypingTrainerUsage{

    private File audioWrongWord; // Audio für falsche Wörter
    private static int counterRightWords; // Zähler für richtige Wörter (WPM)
    private ArrayList<String> selectedText = new ArrayList<>(); // Text der ausgewählt wird
    private static float time;
    private static float wordsPerMinute;

    public static void main(String[] args) {
        TypingTrainerService start = new TypingTrainerService() {


            @Override
            public boolean checkWord(String word) throws WordNotFoundException {
                return false;
            }

            @Override
            public void audioOutput(File soundFile) throws FileNotFoundException {

            }

            @Override
            public void markWord(String word) throws WordNotFoundException {

            }

            @Override
            public void selectionOfText(String selectedText) {

            }

            @Override
            public void quitSession() {

            }

            @Override
            public void showFeedback(float time, float wordsPerMinute) {

            }

            @Override
            public void saveScore(File saveFile) throws FileNotFoundException {

            }

            @Override
            public void loadScore(File saveFile) throws FileNotFoundException {

            }
        };

        TypingTrainerDescriptor descriptor = new TypingTrainerDescriptor();

        //Bei Programmstart
        ArrayList<String> selectedText = new ArrayList<>();
        File audioWrongWord = new File("test.mp3");
        //MediaPlayer

        //Während TrainerSession
        descriptor.setSelectedText(selectedText);
        counterRightWords = descriptor.getCounterRightWords();
        time = descriptor.getTime();
        wordsPerMinute = descriptor.getWordsPerMinute();

    }
}
