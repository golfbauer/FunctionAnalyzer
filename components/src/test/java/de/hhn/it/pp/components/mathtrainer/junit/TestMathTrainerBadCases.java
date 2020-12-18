package de.hhn.it.pp.components.mathtrainer.junit;

import de.hhn.it.pp.components.exceptions.IllegalParameterException;
import de.hhn.it.pp.components.mathtrainer.BiKrMathTrainer;
import de.hhn.it.pp.components.mathtrainer.Term;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


public class TestMathTrainerBadCases {

    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(TestMathTrainerBadCases.class);

    private BiKrMathTrainer mt;
    Term term;

    @BeforeEach
    public void init() {
        logger.info("Before each initialisation");
        mt = new BiKrMathTrainer();
        term = new Term(new BigDecimal(2), new BigDecimal(2), '-', 2);
    }

    @Test
    @DisplayName("Test entering username with bad cases")
    public void checkIfUserEnteredAName() {
        assertThrows(IllegalParameterException.class, () -> mt.setUsername("")); //Check if exception is thrown
    }

    @Test
    @DisplayName("Test entering decimal place with bad cases")
    public void checkEnteringDecimalPlace() {
        assertThrows(IllegalParameterException.class, () -> mt.setDecimalPlace(-1));
    }

    @Test
    @DisplayName("Test changing userscore with bad cases")
    public void checkChangingUserscore() {
        assertThrows(IllegalParameterException.class, () -> mt.setUserScore(-1));
    }

    @Test
    @DisplayName("Test checking userscore with bad cases")
    public void checkUserScoreAddificationWithAllDifficulties() {
        assertThrows(IllegalParameterException.class, () -> mt.addToUserScore(-1));
    }

    @Test
    @DisplayName("Test checking user input with bad cases")
    public void checkUserInputToSolveATerm() {
        boolean b = mt.solveTerm("1", term);
        assertFalse(b);

        assertThrows(IllegalArgumentException.class, () -> mt.solveTerm("ABC", term));
    }

    @Test
    @DisplayName("Test checking user input with bad cases + Timebonus")
    public void checkUserInputToSolveATermWithTimebonus() {
        boolean b = mt.solveTerm("1", term, 4);
        assertFalse(b);
        assertEquals(0, mt.getUserScore());

        b = mt.solveTerm("ABC", term, 4);
        assertFalse(b);

        //assertThrows(IllegalArgumentException.class, () -> mt.solveTerm("ABC", term));
    }

    @Test
    @DisplayName("Test if help user has a valid parameter")
    public void checkIfHelpUserHasParameter() {
        assertThrows(IllegalParameterException.class, () -> mt.helpUser(null));
    }

}
