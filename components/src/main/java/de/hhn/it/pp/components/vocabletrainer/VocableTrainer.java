package de.hhn.it.pp.components.vocabletrainer;

import de.hhn.it.pp.components.example.coffeemakerservice.CoffeeMakerDescriptor;

import java.io.File;
import java.util.List;

public interface VocableTrainer {
    /**
     * Returns a list of registered coffee makers.
     *
     * @return List of registered coffee makers
     */
    String setInput();

    /**
     * Returns a list of registered coffee makers.
     *
     * @return List of registered coffee makers
     */
    int getScore(int id);

    /**
     * Returns a list of registered coffee makers.
     *
     * @return List of registered coffee makers
     */
    String getVocab(File f);

    /**
     * Returns a list of registered coffee makers.
     *
     * @return List of registered coffee makers
     */
    void addNewVocab(String eng,String de,String category);

    /**
     * Returns a list of registered coffee makers.
     *
     * @return List of registered coffee makers
     */
    void selectCategory();


    /**
     * Returns a list of registered coffee makers.
     *
     * @return List of registered coffee makers
     */
    void cancel();

    /**
     * Returns a list of registered coffee makers.
     *
     * @return List of registered coffee makers
     */
    void skip();

    /**
     * Returns a list of registered coffee makers.
     *
     * @return List of registered coffee makers
     */
    //void checkVocab();

}
