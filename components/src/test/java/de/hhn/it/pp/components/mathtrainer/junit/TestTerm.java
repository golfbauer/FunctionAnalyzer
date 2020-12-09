package de.hhn.it.pp.components.mathtrainer.junit;

import de.hhn.it.pp.components.exceptions.IllegalParameterException;
import de.hhn.it.pp.components.mathtrainer.BiKrMathTrainer;
import de.hhn.it.pp.components.mathtrainer.Term;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTerm {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(TestTerm.class);

    Term term;
    Term term2;
    Term term3;
    Term term4;

    @BeforeEach
    public void init() {
        term = new Term(new BigDecimal(2), new BigDecimal(1), '/', 2);
        term2 = new Term(new BigDecimal(2), new BigDecimal(1), '*', 2);
        term3 = new Term(new BigDecimal(2), new BigDecimal(1), '+', 2);
        term4 = new Term(new BigDecimal(2), new BigDecimal(1), 'r', 2);
    }

    @Test
    @DisplayName("Test if getSolution solves the term correctly")
    public void checkIfGetSolutionDeliversCorrectValue() throws IllegalParameterException {
        assertEquals(new BigDecimal("2"), term.getSolution());
        assertEquals(new BigDecimal("2"), term2.getSolution());
        assertEquals(new BigDecimal("3"), term3.getSolution());
        assertEquals(new BigDecimal("0"), term4.getSolution());
    }

    @Test
    @DisplayName("Test if toString returns the right string")
    public void checkIfToStringReturnsRightString() throws IllegalParameterException {
        assertEquals("2 / 1 = ", term.toString());
    }
}
