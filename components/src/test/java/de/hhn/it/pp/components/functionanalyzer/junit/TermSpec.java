package de.hhn.it.pp.components.functionanalyzer.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import de.hhn.it.pp.components.functionanalyzer.Term;
import de.hhn.it.pp.components.functionanalyzer.exceptions.ValueNotDefinedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class TermSpec {
  Term constant;
  Term constant2;
  Term constantRaisedByConstant;
  Term constantRaisedByVariable;
  Term variableRaisedByConstants;
  Term linear;
  Term linear2;
  Term cube;
  Term cube2;

  @BeforeEach
  void init() {
    constant = new Term(4.5);
    constant2 = new Term(3);
    constantRaisedByConstant = new Term(new Term(3), 3);
    constantRaisedByVariable = new Term(new Term(null, 1, "x"), 2);
    variableRaisedByConstants = new Term(new Term(new Term(2), 2), 1, "x");
    linear = new Term(new Term(1),2, "x");
    linear2 = new Term(new Term(1), 3, "x");
    cube = new Term(new Term(2), 2, "x");
    cube2 = new Term(new Term(2), 2, "x");
  }


  @Nested
  class GetDerivative {
    @Test
    void derivativeOfTermWithVariableAndFactor() {
      Term actual = linear.getDerivative();
      Term expected = new Term(2);
      assertEquals(expected, actual, "Term should be the derivative");
    }

    @Test
    void derivativeOfTermWithNoVariable() {
      Term actual = constant2.getDerivative();
      assertNull(actual, "Term should be the derivative");
    }

    @Test
    void derivativeOfTermWithComplexVariableAndFactor() {
      Term actual = cube.getDerivative();
      Term expected = new Term(new Term(1), 4, "x");
      assertEquals(expected, actual, "Term should be the derivative");
    }
  }

  @Nested
  class CalcTermValue {

    @Test
    void calcTermValueForTermWithVariable() {
      double actual = cube.calcTermValue(2);
      assertEquals(8, actual, "Should calc the Term Value for specific x");
    }

    @Test
    void calcTermValueForTermWithoutVariable() {
      double actual = constant.calcTermValue(2);
      assertEquals(4.5, actual, "Should calc the Term Value for specific x");
    }
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

  @Test
  void constantsAreMultipliedByValue() throws ValueNotDefinedException {
    Term actual = constant.multiply(constant2);
    Term expected = new Term(13.5);
    assertEquals(expected, actual, "Constants should have been multiplied");
  }

  @Test
  void constantsAndVariableTermsAreMultipliedByFactor() throws ValueNotDefinedException {
    Term actual = constant2.multiply(linear);
    Term expected = new Term(new Term(1), 6, "x");
    assertEquals(expected, actual, "Terms should have been multiplied by factor");
  }

  @Test
  void variableTermsAreMultipliedByFactorAndAddedExponent() throws ValueNotDefinedException {
    Term actual = linear.multiply(linear2);
    Term expected = new Term(new Term(2), 6, "x");
    assertEquals(expected, actual, "Terms should have been multiplied by factor "
        + "and added exponent");
  }

  @Test
  void constantsAreDividedByValue() throws ValueNotDefinedException {
    Term actual = constant.divide(constant2);
    Term expected = new Term(1.5);
    assertEquals(expected, actual, "Constants should have been multiplied");
  }

  @Test
  void constantsAndVariableTermsAreDividedByFactor() throws ValueNotDefinedException {
    Term actual = linear.divide(constant2);
    Term expected = new Term(new Term(1), 2.0 / 3.0, "x");
    assertEquals(expected, actual, "Terms should have been multiplied by factor");
  }

  @Test
  void variableTermsAreDividedByFactorAndSubtractedExponent() throws ValueNotDefinedException {
    Term actual = linear.divide(linear2);
    Term expected = new Term(2.0 / 3.0);
    assertEquals(expected, actual, "Terms should have been multiplied by factor "
        + "and added exponent");
  }

  @Test
  void constantRaisedByConstantIsSimplifiedToOneConstant() {
    Term actual = constantRaisedByConstant;
    Term expected = new Term(Math.pow(constantRaisedByConstant.getValue(),
        constantRaisedByConstant.getExponent().getValue()));
    actual.simplify();
    assertEquals(expected, actual, "Value of Term should have been raised by exponent");
  }

  @Test
  void constantRaisedByVariableIsNotSimplified() {
    Term actual = constantRaisedByVariable;
    Term expected = new Term(new Term(null, 1, "x"), 2);
    actual.simplify();
    assertEquals(expected, actual, "Term should not have been simplified");
  }

  @Test
  void variableRaisedByConstantHasExponentSimplified() {
    Term actual = variableRaisedByConstants;
    Term expected = new Term(new Term(4), 1, "x");
    actual.simplify();
    assertEquals(expected, actual, "Only Exponent of Term should be simplified");
  }
}
