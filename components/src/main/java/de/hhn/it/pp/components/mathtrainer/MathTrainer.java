package de.hhn.it.pp.components.mathtrainer;

import de.hhn.it.pp.components.exceptions.IllegalParameterException;

import java.math.BigDecimal;
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
     * @throws IllegalParameterException if input is invalid
     */
    void setUsername(String username) throws IllegalParameterException;

    /**
     * returns current Section value
     * @return section
     */
    Section getSection();

    /**
     * sets the section to the value specified in the parameter
     * @param section
     */
    void setSection(Section section);


    /**
     * returns the currently active difficulty level
     * @return difficulty
     */
    Difficulty getDifficulty();

    /**
     * sets the difficulty level equal to the parameter value
     * @param difficulty
     * @thorws IllegalParameterException if difficulty does not match any present values
     */
    void setDifficulty(Difficulty difficulty) throws IllegalParameterException;

    /**
     * returns the number of decimals after the period specified by the user
     * @return decimalPlace
     */
    int getDecimalPlace();

    /**
     * sets the number of decimals after the period to the value specified inside the parameter
     * @param decimalPlace
     * @thorws IllegalParameterException if negative numbers are entered
     */
    void setDecimalPlace(int decimalPlace) throws IllegalParameterException;

    /**
     * returns the current user score
     * @return userscore
     */
    int getUserScore();

    /**
     * allowes to reset or change the user score to the value specified in the parameter
     * @param number
     * @thorws IllegalParameterException if userscore is going to become negative through this methodcall
     */
    void setUserScore(int number) throws IllegalParameterException;

    /**
     * changes the boolean value of wantstoexit
     * @param exitboolean
     */
    void setWantsToExit(boolean exitboolean);

    /**
     * returns the boolean value of wantstoexit
     * @return wantstoexit
     */
    boolean getWantsToExit();

    /**
     * changes the boolean value of timeisup
     * @param timeboolean
     */
    void setTimeIsUp(boolean timeboolean);

    /**
     * returns the boolean value of timeisup
     * @return
     */
    boolean getTimeIsUp();

    /**
     * returns the integer value of the local datafield inturn
     * @return inturn
     */
    int getInTurn();

    /**
     * changes the boolean value of warmup
     * @param warmupboolean
     */
    void setWarmup(boolean warmupboolean);

    /**
     * returns the boolean value of warmup
     * @return warmup
     */
    boolean getWarmup();

    /**
     * adds the timebonus for fast solutions and the bonus for difficult questions to the userscore
     * @param timebonus
     * @thorws IllegalParameterException if negative values for timebonus should appear
     */
    void addToUserScore(int timebonus) throws IllegalParameterException;

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
     * @catch IllegalArgumentException if invalid solutions are entered by the user.
     */
    boolean solveTerm(String userInput, Term term);

    /**
     * createTerm for generating a random term.
     *
     * @param userInput
     * @param term
     * @param solvedInSeconds
     * @return returns true or false depending on whether or not the term has been solved successfully.
     * @catch IllegalArgumentException if invalid solutions are entered by user.
     * @catch IllegalParameterException if secondary method addToUserScore() within this method causes issues.
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

    /**
     * returns the solution of the question if the user asks for it, note no points are added to the user score
     * @param term
     * @return BigDecimal object of the terms solution
     * @throws IllegalParameterException if the term is somehow invalid
     */
    BigDecimal helpUser(Term term) throws IllegalParameterException;

    /**
     * starts a game in which the user has to solve 20 random math questions
     * @param warmup
     * @throws IllegalParameterException if addToUserScore or setUserScore methods cause any input problems
     */
    void startGame(boolean warmup) throws IllegalParameterException;

    /**
     * quitting the game gives this method the loopCount to know at which question the game was exited and a boolean to signal exit is true
     * @param loopCount
     * @param exit
     * @return loopcount is set to 20 so the for loop of the question run reaches its exit condition on the next attempt to run.
     * @throws IllegalParameterException if loopcount has negative values
     */
    int exitGame(int loopCount , boolean exit) throws IllegalParameterException;

    /**
     * creates a few demo string entries to be used in the GUI interface later.
     *
     */
    void createDemoHistoryData();
}
