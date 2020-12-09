package de.hhn.it.pp.components.mathtrainer;

import de.hhn.it.pp.components.exceptions.IllegalParameterException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BiKrMathTrainer implements MathTrainer {
  private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(BiKrMathTrainer.class);

  private String username;
  private int userscore;
  private Section section;
  private Difficulty difficulty;
  private int decimalPlace;
  private List<String> history;
  private boolean timeisup;
  private int inturn;
  private boolean warmup;
  private Term currentTerm;

  /**
   * Constructor to instantiate the basic functions for MathTrainer.
   */
  public BiKrMathTrainer() {
    logger.debug("Constructor is building MathTrainer object");
    section = Section.PLUS;
    difficulty = Difficulty.EASY;
    decimalPlace = 2;
    userscore = 0;
    history = new ArrayList<>();
    timeisup = false;
    inturn = 0;
    warmup = true;
    createDemoHistoryData();
  }

  @Override
  public void setUsername(String username) throws IllegalParameterException {
    logger.debug("Setting username to : " + username);
    if (username.length() > 0) {
      this.username = username;
    } else {
      throw new IllegalParameterException("Bitte einen Namen mit mind. einem Zeichen festlegen.");
    }
  }

  @Override
  public String getUsername() {
    logger.debug("Retrieving username with getter");
    return username;
  }

  @Override
  public Difficulty getDifficulty() {
    logger.debug("Retrieving this objects difficulty with getter");
    return this.difficulty;
  }

  @Override
  public void setDifficulty(Difficulty difficulty) {
    logger.debug("Setting difficulty to: " + difficulty);
    this.difficulty = difficulty;
  }

  @Override
  public void setDecimalPlace(int decimalPlace) throws IllegalParameterException {
    logger.debug("Setting visible decimals after period to: " + decimalPlace);
    if (decimalPlace >= 0) {
      this.decimalPlace = decimalPlace;
    } else {
      throw new IllegalParameterException("Nur positive Werte fuer die Nachkommastellen eingeben.");
    }
  }

  @Override
  public int getDecimalPlace() {
    logger.debug("Retrieving currently set decimal places after period");
    return this.decimalPlace;
  }

  @Override
  public void setUserScore(int number) throws IllegalParameterException {
    logger.debug("Setting userscore to: " + number);
    if (number >= 0) {
      this.userscore = number;
    } else {
      throw new IllegalParameterException("Bitte den UserScore nicht auf negative Werte setzen.");
    }
  }

  @Override
  public int getUserScore() {
    logger.debug("Retrieving current userscore");
    return this.userscore;
  }

  @Override
  public Section getSection() {
    logger.debug("Retrieving currently set Section of the MathTrainer");
    return this.section;
  }

  @Override
  public void setSection(Section section) {
    logger.debug("Setting section of the MathTrainer");
    this.section = section;
  }

  @Override
  public void setTimeIsUp(boolean timeboolean) {
    logger.debug("Setting timeisup boolean");
    this.timeisup = timeboolean;
  }

  @Override
  public boolean getTimeIsUp() {
    logger.debug("Retrieving timeisup boolean value");
    return this.timeisup;
  }

  @Override
  public void setInTurn(int inturn) {
    logger.debug("Setting inturn integer to: " + inturn);
    this.inturn = inturn;
  }

  @Override
  public int getInTurn() {
    logger.debug("Retrieving inturn integer value");
    return this.inturn;
  }

  @Override
  public boolean getWarmup() {
    logger.debug("Retrieving warmup boolean value");
    return this.warmup;
  }

  @Override
  public void setWarmup(boolean warmupboolean) {
    logger.debug("Setting warmup boolean value to: " + warmupboolean);
    this.warmup = warmupboolean;
  }

  @Override
  public void addToUserScore(int timebonus) throws IllegalParameterException {
    logger.debug("addToUserScore() call");
    if (timebonus >= 0) {
      int points =
                  difficulty == Difficulty.EASY ? 1 :
                          difficulty == Difficulty.MEDIUM ? 2 :
                                  3;
      this.userscore = userscore + points + timebonus;
    } else {
      throw new IllegalParameterException("Der Zeitbonus kann nicht negativ sein.");
    }
  }

  public Term getCurrentTerm() {
    logger.debug("getCurrentTerm() call");
    return this.currentTerm;
  }

  @Override
  public Term createTerm() {
    logger.debug("createTerm() call");
    int maxNumberSize =
            difficulty == Difficulty.EASY ? 9 :
                difficulty == Difficulty.MEDIUM ? 19 :
                    29;

    BigDecimal firstNumber = BigDecimal.valueOf((long)(Math.random() * maxNumberSize));
    BigDecimal secondNumber = BigDecimal.valueOf((long)(Math.random() * maxNumberSize));

    char [] operands = {'+','-','*','/'};
    Character operator =
            section == Section.PLUS ? operands[0] :
                section == Section.MINUS ? operands[1] :
                    section == Section.MULTIPLICATION ? operands[2] :
                        section == Section.DIVISION ? operands[3] :
                            operands[(int)(Math.random() * 4)]; //Mixed Mode

    if (secondNumber.equals(BigDecimal.ZERO) && operator.equals('/')) {
      secondNumber = BigDecimal.ONE;
    }

    Term term = new Term(firstNumber, secondNumber, operator, this.getDecimalPlace());
    currentTerm = term;
    return term;
  }

  public Term nextTerm() {
    logger.debug("nextTerm() call");
    inturn++;
    return createTerm();
  }

  @Override
  public boolean solveTerm(String userInput, Term term) throws IllegalArgumentException {
    logger.debug("solveTerm() call ,without timer for fast solution");
    if (userInput.contains(",")) {
      userInput = userInput.replace(',', '.');
    }
    try {
      BigDecimal number = new BigDecimal(userInput);
      BigDecimal correctSolution = term.getSolution();
      return number.equals(correctSolution);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Bitte eine Ganzzahl als Ergebnis eingeben!");
    }
  }

  @Override
  public boolean solveTerm(String userInput, Term term, int solvedInSeconds) {
    logger.debug("solveTerm() call, with timer for fast solution");
    if (userInput.contains(",")) {
      userInput = userInput.replace(',', '.');
    }
    try {
      BigDecimal number = new BigDecimal(userInput);
      BigDecimal correctSolution = term.getSolution();
      if (number.equals(correctSolution)) {
        this.addToUserScore(solvedInSeconds);
        return true;
      }
    } catch (IllegalArgumentException | IllegalParameterException e) {
      //wenn ungueltige Zeichen oder Ergebnisse eingegeben werden, mit Catch Block abfangen
      //und solveTerm gibt als Ergebnis false zurueck
      //moegliche Exceptions von der Methode addToUserScore(solvedInSeconds) abfangen
      return false;
    }
    return false;
  }

  @Override
  public void addToHistory() {
    logger.debug("addToHistory() call, add momentary MathTrainer object data to the history list");
    String entry = "" + this.username + " | " + this.userscore
            + " | " + this.difficulty + " | " + this.section + " | Countdown mode";
    history.add(entry);
  }

  @Override
  public List<String> getHistory() {
    logger.debug("getHistory() call, retrieve the history list from the MathTrainer");
    return history;
  }

  @Override
  public BigDecimal helpUser(Term term) throws IllegalParameterException {
    logger.debug("helpUser() call, return solution of the Term object");
    if (term != null) {
      return term.getSolution();
    } else {
      throw new IllegalParameterException("Kein gueltiger Term oder ein null Objekt uebergeben.");
    }
  }

  /**
   * populate history list with a few entries for display in gui.
   */
  public void createDemoHistoryData() {
    logger.debug("createDemoHistoryData call to add test data.");
    history.add("Matthew | 15 | EASY | MULTIPLICATION | countdown mode");
    history.add("Hammond | 21 | HARD | ADDITION | countdown mode");
    history.add("Erika | 18 | MEDIUM | MIXED | countdown mode");
  }
}
