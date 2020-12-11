package de.hhn.it.pp.components.learningCards;

import de.hhn.it.pp.components.example.coffeemakerservice.provider.WnckCoffeeMakerService;

public class LearningProgress {

  private static final org.slf4j.Logger logger =
       org.slf4j.LoggerFactory.getLogger(LearningProgress.class);

  // number of right answered questions
  int right = 0;
  // number of wrong answered questions
  int wrong = 0;

  /**
   * increases right by 1.
   */
  public void updateRight() {
    right++;
  }

  /**
   * increases wrong by 1.
   */
  public void updateWrong() {
    wrong++;
  }


  @Override
  public String toString() {
    return "right answered: " + right + ",   wrong answered: " + wrong;
  }

  /**
   * makes 0 the Variables right and wrong.
   */
  public void reset() {
    right = 0;
    wrong = 0;
  }

}
