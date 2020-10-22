package de.hhn.it.pp.components.vocabletrainer;

import de.hhn.it.pp.components.example.coffeemakerservice.CoffeeMakerDescriptor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public interface VocableTrainer {

    /**
     * Returns the currently selected User.
     *
     * @return user object
     */
    User getUser();

    /**
     * Returns a list of users.
     *
     * @return List of registered users
     */
    ArrayList<User> getUsers();

    /**
     * Add a new User to the user list
     *
     * @param name of the new user
     * @return user successfully added
     */
    boolean addUser(String name);

    /**
     * Remove user from user list
     *
     * @param id User
     * @return user successfully removed
     */
    boolean removeUser(int id);

    /**
     * Select a user from the user list
     *
     * @param id User
     * @return user successfully selected
     */
    boolean selectUser(int id);

    /**
     * Returns the currently selected VocCategory.
     *
     * @return VocCategory array
     */
    VocCategory getVocCategory();

    /**
     * Returns all VocCategories.
     *
     * @return List of registered VocCategories
     */
    VocCategory[] getVocCategories();

    /**
     * Add a new VocCategory to the VocCategory array
     *
     * @param id of the new VocCategory
     * @return VocCategory successfully added
     */
    boolean addVocCategory(int id);

    /**
     * Remove VocCategory from VocCategory array
     *
     * @param id VocCategory
     * @return VocCategory successfully removed
     */
    boolean removeVocCategory(int id);

    /**
     * Select a VocCategory from the VocCategory array
     *
     * @param id VocCategory
     * @return VocCategory successfully selected
     */
    boolean selectVocCategory(int id);


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
    void addNewVocab(String eng, String de, String category);

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
    void ok();

}
