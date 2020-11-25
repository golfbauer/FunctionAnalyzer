package de.hhn.it.pp.components.mathtrainer;

import de.hhn.it.pp.components.exceptions.IllegalParameterException;

import java.util.List;

/**
 * This MathTainer is an interface which represents the basic functions
 * for the application.
 */
public interface MathTrainer {

    /**
     * return current user name
     * @return username
     */
    String getUsername();

    /**
     * sets the current user name to the value that was given as parameter
     * @param username
     * @throws IllegalParameterException
     */
    void setUsername(String username) throws IllegalParameterException;

    /**
     * returns the currently active difficulty level
     * @return difficulty
     */
    Difficulty getDifficulty();

    /**
     * sets the difficulty level equal to the parameter value
     * @param difficulty
     */
    void setDifficulty(Difficulty difficulty);

    /**
     * returns the number of decimals after the period specified by the user
     * @return decimalPlace
     */
    int getDecimalPlace();

    /**
     * sets the number of decimals after the period to the value specified inside the parameter
     * @param decimalPlace
     */
    void setDecimalPlace(int decimalPlace);

    /**
     * returns the current user score
     * @return userscore
     */
    int getUserScore();

    /**
     * allowes to reset or change the user score to the value specified in the parameter
     * @param number
     */
    void setUserScore(int number);

    /**
     * adds the timebonus for fast solutions and the bonus for difficult questions to the userscore
     * @param timebonus
     */
    void addToUserScore(int timebonus);

    /**
     * createTerm for generating a random term.
     *
     * @return the random generated term
     */
    Term createTerm();

    /**
     * solveTerm to check if a term is equal with the solution that the user entered in.
     *
     * @param userInput
     * @param term
     * @return returns true or false depending on whether or not the term has been solved successfully.
     */
    boolean solveTerm(String userInput, Term term);

    /**
     * createTerm for generating a random term.
     *
     * @param userInput
     * @param term
     * @param solvedInSeconds
     * @return returns true or false depending on whether or not the term has been solved successfully.
     */
    boolean solveTerm(String userInput, Term term, int solvedInSeconds);

    /**
     * adds the current object datafields name, userscore and difficulty to a list
     *
     */
    void addToHistory();

    /**
     * returns a list for further use
     *
     * @return a list element
     */
    List<String> getHistory();
}
