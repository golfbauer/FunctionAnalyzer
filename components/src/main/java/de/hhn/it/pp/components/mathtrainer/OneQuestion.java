package de.hhn.it.pp.components.mathtrainer;

import de.hhn.it.pp.components.exceptions.IllegalParameterException;

import java.math.BigDecimal;

public class OneQuestion implements Runnable {
  private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(BiKrMathTrainer.class);
  private BigDecimal userinput;
  private Term question;
  private BigDecimal expected;
  private Watch stopwatch;
  private boolean solved;
  private int userscore;
  private BiKrMathTrainer trainer;

  public OneQuestion(BigDecimal userinput, Term question, BigDecimal expected, Watch stopwatch, int userscore, BiKrMathTrainer trainer) {
    this.userinput = userinput;
    this.question = question; //Term object
    this.expected = expected; //Term.solveTerm()
    this.stopwatch = stopwatch;
    solved = false;
    this.userscore = userscore;
    this.trainer = trainer;
  }
  @Override
  public void run() {
    stopwatch = new Watch();
    new Thread(stopwatch).start();
    while (!stopwatch.getTimeisup()) {
      //set string input = value from button via event? how?
      try {
        String helper = userinput.toString();
        logger.info("Userinput is " + helper);
        solved = trainer.solveTerm(helper, question, stopwatch.getSeconds());
        logger.info("solved set to: " + solved);
        if (solved && !stopwatch.getTimeisup()) {
          logger.info("Solved within timeframe");
          try {
            logger.info("Give user points");
            trainer.addToUserScore(stopwatch.getSeconds());
          } catch (IllegalParameterException e) {
            e.printStackTrace();
          }
          logger.info("Execute return, kill (this) OneQuestion");
          return;
        } else if (stopwatch.getTimeisup()) {
          logger.info("Watch returns timeisup true, kill (this) OneQuestion");
          solved = false;
          return;
        }
      } catch (IllegalArgumentException e) {
        logger.info("Input was not a BigDecimal");
      }
    }
    logger.info("OneQuestion last line before end.");
  }

  public boolean getSolved() {
    return solved;
  }
}
