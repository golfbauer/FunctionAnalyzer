package de.hhn.it.pp.components.spellingtrainer;

import de.hhn.it.pp.components.spellingtrainer.exceptions.CounterNotFoundException;
import de.hhn.it.pp.components.spellingtrainer.exceptions.WordAlreadyAddedException;
import de.hhn.it.pp.components.spellingtrainer.exceptions.WordNotFoundException;

import java.io.File;
import java.io.FileNotFoundException;

public class DemoSpellingTrainerUsage {
    public static void main(String[] args) throws FileNotFoundException, WordAlreadyAddedException, WordNotFoundException, CounterNotFoundException {
        String word="Testwort";
        File audioFile = new File("Audio.mp3");
        SpellingTrainerService service = new SpellingTrainerService() {
            @Override
            public void audioOutput(File audioFile) throws FileNotFoundException {
                            }

            @Override
            public boolean checkSpelling(String writtenWord) {
                return false;
            }

            @Override
            public void addWord(String word, File audio) throws WordAlreadyAddedException, FileNotFoundException {

            }

            @Override
            public void deleteWord(String word) throws WordNotFoundException {

            }
        };
        service.audioOutput(audioFile);
        service.addWord(word,audioFile);
        service.checkSpelling(word);
        service.deleteWord(word);
        SpellingTrainerDescriptor descriptor = new SpellingTrainerDescriptor();
        descriptor.setSpellingWord(word);
        descriptor.getAudioFile(word);
        descriptor.getCounter("Wrong");
        descriptor.setAudioFiles(word,audioFile);
        descriptor.getSpellingWord(0);




        }

    }

