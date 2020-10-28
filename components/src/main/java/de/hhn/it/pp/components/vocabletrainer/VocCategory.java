package de.hhn.it.pp.components.vocabletrainer;

import java.util.ArrayList;

public class VocCategory {
private int id;
private String name;
private ArrayList<Vocable> vocabulary = new ArrayList<>();

    public int getId() {
        return id;
    }

    public ArrayList<Vocable> getVocabulary() {
        return vocabulary;
    }

    public String getName() {
        return name;
    }
}
