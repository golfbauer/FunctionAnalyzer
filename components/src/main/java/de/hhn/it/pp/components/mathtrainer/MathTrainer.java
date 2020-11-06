package de.hhn.it.pp.components.mathtrainer;

import de.hhn.it.pp.components.exceptions.IllegalParameterException;

/**
 * This MathTainer is an interface which represents the basic functions
 * for the application.
 */
public interface MathTrainer {

    String getUsername();

    void setUsername(String username) throws IllegalParameterException;

    Difficulty getDifficulty();

    void setDifficulty(Difficulty difficulty);

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
     */
    boolean solveTerm(String userInput, Term term);
}
