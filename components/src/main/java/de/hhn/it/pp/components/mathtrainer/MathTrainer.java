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
  * return current user name.
  * @return username.
  */
  String getUsername();

  /**
  * sets the current user name to the value that was given as parameter.
  * @param username name of the player
  * @throws IllegalParameterException if input is invalid
  */
  void setUsername(String username) throws IllegalParameterException;

  /**
  * returns current Section value.
  * @return section
  */
  Section getSection();

  /**
  * sets the section to the value specified in the parameter.
  * @param section calculation mode
  */
  void setSection(Section section);

  /**
  * returns the currently active difficulty level.
  * @return difficulty
  */
  Difficulty getDifficulty();

  /**
  * sets the difficulty level equal to the parameter value.
  * @param difficulty one of 3 difficulties
  * @throws IllegalParameterException if difficulty does not match any present values
  */
  void setDifficulty(Difficulty difficulty) throws IllegalParameterException;

  /**
  * returns the number of decimals after the period specified by the user.
  * @return decimalPlace
  */
  int getDecimalPlace();

  /**
  * sets the number of decimals after the period to the value specified inside the parameter.
  * @param decimalPlace decimal count after period
  * @throws IllegalParameterException if negative numbers are entered
  */
  void setDecimalPlace(int decimalPlace) throws IllegalParameterException;

  /**
  * returns the current user score.
  * @return userscore.
  */
  int getUserScore();

  /**
  * allowes to reset or change the user score to the value specified in the parameter.
  * @param number replaces userscore value
  * @throws IllegalParameterException if userscore is negative through this methodcall
  */
  void setUserScore(int number) throws IllegalParameterException;

  /**
  * changes the boolean value of wantstoexit.
  * @param exitboolean boolean value true if user wants to exit
  */
  void setWantsToExit(boolean exitboolean);

  /**
  * returns the boolean value of wantstoexit.
  * @return wantstoexit
  */
  boolean getWantsToExit();

  /**
  * changes the boolean value of timeisup.
  * @param timeboolean boolean value true when time is up
  */
  void setTimeIsUp(boolean timeboolean);

  /**
  * returns the boolean value of timeisup.
  * @return timeisup
  */
  boolean getTimeIsUp();

  /**
   * set the integer value inside the MathTrainer class
   * @param inturn integer value
   */
  void setInTurn(int inturn);

  /**
  * returns the integer value of the local datafield inturn.
  * @return inturn
  */
  int getInTurn();

  /**
  * changes the boolean value of warmup.
  * @param warmupboolean boolean value to determine which mode is played
  */
  void setWarmup(boolean warmupboolean);

  /**
  * returns the boolean value of warmup.
  * @return warmup
  */
  boolean getWarmup();

  /**
  * adds the timebonus for fast solutions and the bonus for difficult questions to the userscore.
  * @param timebonus bonus points for quick solutions
  * @throws IllegalParameterException if negative values for timebonus should appear
  */
  void addToUserScore(int timebonus) throws IllegalParameterException;

  /**
  * createTerm for generating a random term.
  * @return the random generated term
  */
  Term createTerm();

  /**
  * solveTerm to check if a term is equal with the solution that the user entered in.
  *
  * @param userInput solution input from user
  * @param term current term object that needs solving
  * @return returns true or false depending on whether or not the term has been solved successfully.
  * @exception IllegalArgumentException if invalid solutions are entered by the user.
  */
  boolean solveTerm(String userInput, Term term);

  /**
  * createTerm for generating a random term.
  *
  * @param userInput solution input from user
  * @param term current term that needs solving
  * @param solvedInSeconds timestamp at which term was solved by user
  * @return returns true or false depending on whether or not the term has been solved successfully.
  * @exception IllegalArgumentException if invalid solutions are entered by user.
  * @exception IllegalParameterException if secondary method addToUserScore() inside causes issues.
  */
  boolean solveTerm(String userInput, Term term, int solvedInSeconds);

  /**
  * adds the current object datafields name, userscore and difficulty to a list.
  */
  void addToHistory();

  /**
  * returns a list for further use.
  * @return a list element
  */
  List<String> getHistory();

  /**
  * returns solution of term if the user asks for it, note no points are added to the user score.
  * @param term term object
  * @return BigDecimal object of the terms solution
  * @throws IllegalParameterException if the term is somehow invalid
  */
  BigDecimal helpUser(Term term) throws IllegalParameterException;

  /**
  * starts a game in which the user has to solve 20 random math questions.
  * @param warmup boolean to decide which mode is selected
  * @throws IllegalParameterException if addToUserScore or setUserScore methods cause any problems.
  */
  void startGame(boolean warmup) throws IllegalParameterException;

  /**
  * uses the loopCount and a boolean to know when the game was exited and signal exit is true.
  * @param loopCount number of the current question e.g. 5
  * @param exit boolean value
  * @return loopcount is set to 20 so the for loop ends on the next attempt to run.
  * @throws IllegalParameterException if loopcount has negative values
  */
  int exitGame(int loopCount, boolean exit) throws IllegalParameterException;

  /**
  * creates a few demo string entries to be used in the GUI interface later.
  *
  */
  void createDemoHistoryData();
}
