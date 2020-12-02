package de.hhn.it.pp.components.functionanalyzer.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import de.hhn.it.pp.components.functionanalyzer.Term;
import de.hhn.it.pp.components.functionanalyzer.exceptions.ValueNotDefinedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TermSpec {
  Term constant;
  Term constant2;
  Term linear;
  Term linear2;
  Term cube;
  Term cube2;

  @BeforeEach
  void init() {
    constant = new Term(4.5);
    constant2 = new Term(3);
    linear = new Term(new Term(1),2, "x");
    linear2 = new Term(new Term(1), 3, "x");
    cube = new Term(new Term(2), 2, "x");
    cube2 = new Term(new Term(2), 2, "x");
  }

  @Test
  void termsWithDifferentStructureCannotBeAdded() {
    try {
      constant.add(linear);
      fail("This test should throw an exception");
    } catch (ValueNotDefinedException ignored) { }

    try {
      linear.add(cube);
      fail("This test should throw an exception");
    } catch (Exception e) {
      assertTrue(e instanceof ValueNotDefinedException,
          "Should throw ValueNotDefinedException");
    }
  }

  @Test
  void constantsAreAddedByValue() throws ValueNotDefinedException {
    Term actual = constant.add(constant2);
    Term expected = new Term(7.5);
    assertEquals(expected, actual, "Constants should have been added");
  }

  @Test
  void constantsAreSubtractedByValue() throws ValueNotDefinedException {
    Term actual = constant2.subtract(constant);
    Term expected = new Term(-1.5);
    assertEquals(expected, actual, "Constants should have been subtracted");
  }

  @Test
  void termsWithSameUnkownAndFactorAreSubtractedByFactor() throws ValueNotDefinedException {
    Term actual = linear.subtract(linear2);
    Term expected = new Term(new Term(1), -1, "x");
    assertEquals(expected, actual, "Factors should be subtracted");
  }

  @Test
  void termsWithDifferentStructureCannotBeSubtracted() {
    try {
      constant.subtract(linear);
      fail("This test should throw an exception");
    } catch (ValueNotDefinedException ignored) { }

    try {
      linear.add(cube);
      fail("This test should throw an exception");
    } catch (Exception e) {
      assertTrue(e instanceof ValueNotDefinedException,
          "Should throw ValueNotDefinedException");
    }
  }
}
