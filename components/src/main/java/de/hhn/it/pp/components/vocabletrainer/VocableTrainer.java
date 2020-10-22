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
     * Returns the currently selected Vocable.
     *
     * @return Vocable
     */
    Vocable getVocable();

    /**
     * Returns all Vocabulary.
     *
     * @return List of registered vocabulary
     */
    ArrayList<Vocable>  getVocabulary();

    /**
     * Add a new Vocable to the Vocabulary list
     *
     * @param originWord , foreignWord, id of the VocCategory
     * @return Vocable successfully added
     */
    boolean addVocable(String originWord, String foreignWord, VocCategory id);

    /**
     * Remove Vocable from Vocabulary list
     *
     * @param vocableId Vocabulary
     * @return Vocable successfully removed
     */
    boolean removeVocable(int vocableId);

    /**
     * Select a VocCategory from the VocCategory array
     *
     * @param vocableId Vocabulary
     * @return Vocable successfully selected
     */
    boolean selectVocable(int vocableId);

    /**
     * Returns a list of registered coffee makers.
     *
     * @return List of registered coffee makers
     */
    String setInput();

    /**
     * Canceling the current process
     */
    void cancel();

    /**
     *  Skips the current vocable
     */
    void skip();

    /**
     * Confirm
     */
    void ok();


    /**
     * Save the Vocable or Category and enter a new Vocable or Category
     *
     * @throws IllegalStateException if input is incomplete or already exists
     */
    void saveAndNew() throws IllegalStateException;

    /**
     * Save the Vocable or Category
     *
     * @throws IllegalStateException if input is incomplete or already exists
     */
    void save() throws IllegalStateException;
}
