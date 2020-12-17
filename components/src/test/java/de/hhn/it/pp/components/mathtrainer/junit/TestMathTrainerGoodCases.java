package de.hhn.it.pp.components.mathtrainer.junit;

import de.hhn.it.pp.components.exceptions.IllegalParameterException;
import de.hhn.it.pp.components.mathtrainer.BiKrMathTrainer;
import de.hhn.it.pp.components.mathtrainer.Difficulty;
import de.hhn.it.pp.components.mathtrainer.Section;
import de.hhn.it.pp.components.mathtrainer.Term;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


public class TestMathTrainerGoodCases {

    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(TestMathTrainerGoodCases.class);

    private BiKrMathTrainer mt;
    Term term;

    @BeforeEach
    public void init() {
        logger.info("Before each initialisation");
        mt = new BiKrMathTrainer();
        term = new Term(new BigDecimal(2), new BigDecimal(2), '-', 2);
    }

    @Test
    @DisplayName("Check userscore is 0 when created MathTrainer")
    public void checkUserScoreOfCreatedMathTrainer() {
        assertEquals(0, mt.getUserScore());
    }

    @Test
    @DisplayName("Test entering Username check")
    public void checkIfUserEnteredAName() throws IllegalParameterException {
        mt.setUsername("Xerxes");
        assertEquals("Xerxes", mt.getUsername());
    }

    @Test
    @DisplayName("Test entering decimal place")
    public void checkEnteringDecimalPlace() throws IllegalParameterException {
        mt.setDecimalPlace(2);
        assertEquals(2, mt.getDecimalPlace());
    }

    @Test
    @DisplayName("Test changing userscore")
    public void checkChangingUserscore() throws IllegalParameterException {
        mt.setUserScore(2);
        assertEquals(2, mt.getUserScore());
    }

    @Test
    @DisplayName("Test checking userscore")
    public void checkUserScoreAddificationWithAllDifficulties() throws IllegalParameterException {
        mt.setDifficulty(Difficulty.EASY);
        mt.addToUserScore(5);
        assertEquals(6, mt.getUserScore());
        mt.setUserScore(0);

        mt.setDifficulty(Difficulty.MEDIUM);
        mt.addToUserScore(5);
        assertEquals(7, mt.getUserScore());
        mt.setUserScore(0);

        mt.setDifficulty(Difficulty.HARD);
        mt.addToUserScore(5);
        assertEquals(8, mt.getUserScore());
        mt.setUserScore(0);
    }

    @Test
    @DisplayName("Test if object exists")
    public void checkIfCreateTermObjectExists() {
        boolean exists;
        Term t = mt.createTerm();

        exists = t != null;

        assertTrue(exists);
    }

    @Test
    @DisplayName("Test if difficulty is handled correctly")
    public void createTermBasedOnCurrentDifficulty() {
        mt.setDifficulty(Difficulty.MEDIUM);
        Term local = mt.createTerm();
        assertNotNull(local);
        mt.setDifficulty(Difficulty.HARD);
        local = mt.createTerm();
        assertNotNull(local);
    }

    @Test
    @DisplayName("Test if difficulty is handled correctly")
    public void createTermBasedOnCurrentSection() {
        mt.setSection(Section.MINUS);
        Term t = mt.createTerm();
        assertEquals(t.operator.charValue(), '-');

        mt.setSection(Section.MULTIPLICATION);
        t = mt.createTerm();
        assertEquals(t.operator.charValue(), '*');

        mt.setSection(Section.DIVISION);
        t = mt.createTerm();
        assertEquals(t.operator.charValue(), '/');

        mt.setSection(Section.MIXED);
        t = mt.createTerm();
        String helper = ""+t.operator;
        boolean b = helper.contains("*") || helper.contains("/") || helper.contains("+") || helper.contains("-");
        assertTrue(b);

        mt.setSection(Section.DIVISION);
        while(true) {
            t = mt.createTerm();

            if(t.secondNumber.equals(BigDecimal.ONE) && t.operator.equals('/')) {
                assertTrue(true);
                break;
            }
        }
    }

    @Test
    @DisplayName("Test checking user input with good cases")
    public void checkUserInputToSolveATerm() {
        boolean b = mt.solveTerm("0", term);
        assertTrue(b);
    }

    @Test
    @DisplayName("Test checking user input with good cases + Timebonus")
    public void checkUserInputToSolveATermWithTimebonus() {
        boolean b = mt.solveTerm("0", term, 4);
        assertTrue(b);
        assertEquals(5, mt.getUserScore());
    }

    @Test
    @DisplayName("Test adding new entry to history list")
    public void checkAddingNewEntryToHistoryList() {
        mt.addToHistory();
        assertTrue(mt.getHistory().size() > 0);
    }

    @Test
    @DisplayName("Test if help user resolves term")
    public void checkIfHelpUserHasParameter() throws IllegalParameterException {
        assertEquals(term.getSolution(), mt.helpUser(term));
    }

}
