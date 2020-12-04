package de.hhn.it.pp.components.typingtrainer.junit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.hhn.it.pp.components.typingtrainer.Feedback;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Typing Trainer Tests")
public class TestsTypingTrainerGoodCases {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(TestsTypingTrainerGoodCases.class);



  //region Tutorial
  @Test
  @DisplayName("Should demonstrate a simple assertion")
  void shouldShowGoodAssertion() {
    Assertions.assertEquals(1,1);
  }

  @Test
  void shouldShowWorstAssertion() {
    Assertions.assertEquals(1,2);
  }

  @Test
  @DisplayName("Should check all items in the list")
  void shouldCheckAllItemsInTheList() {
    List<Integer> numbers = List.of(2,3,5,7);

    Assertions.assertEquals(java.util.Optional.of(1), numbers.get(0));
    Assertions.assertEquals(java.util.Optional.of(3),numbers.get(1));
    Assertions.assertEquals(java.util.Optional.of(5),numbers.get(2));
    Assertions.assertEquals(java.util.Optional.of(7),numbers.get(3));
  }

  @Test
  void shouldOnlyRunTheTestIfSomeCriteriaAreMet() {
    Assumptions.assumeTrue(11 > 10);
    assertEquals(1,1);
  }
  //endregion


  @Test
  @DisplayName("test Feedback")
  void testFeedback() {
    Feedback feedback = new Feedback(420.0, 13);

    //Set & Get time and wpm
    feedback.setTime(430.0);
    feedback.setWordsPerMinute(15);

    Assertions.assertEquals(430.0,feedback.getTime());
    Assertions.assertEquals(15, feedback.getWordsPerMinute());

    //Set & Get AvgWordLength
    feedback.setAvgWordLength(10);
    Assertions.assertEquals(10, feedback.getAvgWordLength());

    //CntrRightWords
    feedback.setCounterRightWords(88);
    Assertions.assertEquals(88, feedback.getCounterRightWords());

    feedback.increaseCounterRightWords();
    Assertions.assertEquals(89, feedback.getCounterRightWords());

    //Start- and endTime
    feedback.setStartTime(1344);
    feedback.setEndTime(1458);

    Assertions.assertEquals(1344, feedback.getStartTime());
    Assertions.assertEquals(1458, feedback.getEndTime());

    feedback.calculateTime();
    Assertions.assertEquals(1458-1344, feedback.getTime());

    //Calculate WPM
    String[] typedWords = {"hallo", "parkuhr", "Marachi", "Marakki", "Marazi", "Mariachi", "Marichi", "Marasi"};
    String[] selectedText = {"Zanzikki", "Maciaszek", "Marachi", "Marakki", "Marazi", "Mariachi", "Marichi", "Marasi"};
    feedback.calculateWordsPerMinute(typedWords, selectedText);
    Assertions.assertEquals(0.31578947368421, feedback.getWordsPerMinute()); // <- bissl abfuck
  }
}
