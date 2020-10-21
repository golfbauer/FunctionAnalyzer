package de.hhn.it.pp.components.spellingtrainer;

import de.hhn.it.pp.components.spellingtrainer.exceptions.CounterNotFoundException;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


public class SpellingTrainerDescriptor {

    private HashMap<String, File> audioFiles= new HashMap<>();
    private ArrayList<String> spellingWords= new ArrayList<>();
    private int counterWrongWords;
    private int counterRightWords;
    private int counterRemainingWords;

    public void setAudioFiles(String spellingWord,File audioFile){
        audioFiles.put(spellingWord,audioFile);
    }
    public File getAudioFile(String spellingWord){
        return audioFiles.get(spellingWord);
    }
    public void setSpellingWord(String spellingWord){
        spellingWords.add(spellingWord);
    }
    public String getSpellingWord(int index){
        return spellingWords.get(index);
    }
    public int getCounter(String counterName) throws CounterNotFoundException{

        switch(counterName.toLowerCase()){
            case "wrong":
                return counterWrongWords;

            case "right":
                return counterRightWords;

            case "remaining":
                return counterRemainingWords;

            default:
                 throw new CounterNotFoundException();


        }
    }

}
