package de.hhn.it.pp.components.typingtrainer;

import java.io.File;
import java.util.ArrayList;


public class TypingTrainerDescriptor {
    private File audioWrongWord; // Audio für falsche Wörter
    private int counterRightWords; // Zähler für richtige Wörter (WPM)
    private ArrayList<String> selectedText = new ArrayList<>(); // Text der ausgewählt wird
    private float time;
    private float wordsPerMinute;

    public void setAudioWrongWord(File media) {
        audioWrongWord = media;
    }

    public File getAudioWrongWord() {
        return audioWrongWord;
    }

    public int getCounterRightWords() {
        return counterRightWords;
    }

    public ArrayList<String> getSelectedText() {
        return selectedText;
    }

    public void setSelectedText(ArrayList<String> text) {
        selectedText = text;
    }

    public int increaseCounter(int counterRightWords){
        return counterRightWords;
    }

    public float getTime() {
        return time;
    }

    public float getWordsPerMinute() {
        float time = getTime();
        float counter = getCounterRightWords();

        wordsPerMinute = time/counter;

        return wordsPerMinute;
    }
}
