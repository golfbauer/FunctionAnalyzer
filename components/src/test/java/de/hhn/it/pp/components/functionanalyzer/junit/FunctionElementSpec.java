package de.hhn.it.pp.components.functionanalyzer.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import de.hhn.it.pp.components.functionanalyzer.FunctionElement;
import de.hhn.it.pp.components.functionanalyzer.Operator;
import de.hhn.it.pp.components.functionanalyzer.Term;
import de.hhn.it.pp.components.functionanalyzer.exceptions.ValueNotDefinedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.css.CSSImportRule;

public class FunctionElementSpec {

  FunctionElement simple;
  FunctionElement normal;
  FunctionElement multiple;
  FunctionElement multipleFe;

  @BeforeEach
  void init() {
    simple = new FunctionElement(Operator.ADD, new Term(18));
    normal = new FunctionElement(Operator.ADD, new Term(new Term(2), -5, "x"));
    multiple = new FunctionElement(Operator.ADD,
        new FunctionElement(Operator.ADD, new Term(new Term(2), 3, "x")),
        new FunctionElement(Operator.ADD, new Term(new Term(1), -5, "x")),
        new FunctionElement(Operator.ADD, new Term(7))
    );
    multipleFe = new FunctionElement(Operator.ADD,
        new FunctionElement(Operator.ADD, new Term(7)),
        new FunctionElement(Operator.MULTIPLY,
            new FunctionElement(Operator.ADD, new Term(3)),
            new FunctionElement(Operator.ADD, new Term(new Term(2), -4, "x")
            )));
  }

  @Test
  void getDerivativeForNormalFunctionElement() {
    FunctionElement expected = new FunctionElement(Operator.ADD,
        new Term(new Term(1), -10, "x"));
    FunctionElement actual = normal.getDerivative();
    assertEquals(expected.toString(), actual.toString(), "Should get the Derivative -10*x^1");
  }

  @Test
  void getDerivativeForFunctionElementWithMultipleTerms() {
    FunctionElement expected = new FunctionElement(Operator.ADD,
        new FunctionElement(Operator.ADD, new Term(new Term(1), 6, "x")),
        new FunctionElement(Operator.ADD, new Term(-5)));
    FunctionElement actual = multiple.getDerivative();
    assertEquals(expected.toString(), actual.toString(), "Should get the derivative");
  }

  @Test
  void getDerivativeForNullFunctionElement() {
    FunctionElement actual = simple.getDerivative();
    assertEquals(null, actual, "Derivative should be null");
  }

  @Test
  void getMaxExponentFromSquare() {
    double actual = normal.getMaxExponent();
    assertEquals(2, actual, "should get the highest exponent");
  }

  @Test
  void getMaxExponentFromComplexSquare() {
    double actual = multiple.getMaxExponent();
    assertEquals(2, actual, "Should get the highest exponent");
  }

  @Test
  void getMaxExponenetFromComplexHigherSquare() {
    double actual = multipleFe.getMaxExponent();
    assertEquals(2, actual, "Shoulg get the highest Exponent");
  }

  @Test
  void getMaxExponentFromMostSimpleFunction() {
    double actual = simple.getMaxExponent();
    assertEquals(0, actual,"Should give back the highest exponent");
  }

  @Test
  void removeBracketsDoesNotRemoveNecessaryBrackets() {
    FunctionElement actual = multiple;
    FunctionElement expected = multiple;
    actual.removeBrackets();
    assertEquals(expected, actual);
  }

  @Test
  void resolveBracketFollowedByAddition() throws ValueNotDefinedException {
    FunctionElement actual = new FunctionElement(Operator.ADD,
        new FunctionElement(Operator.ADD,
            new FunctionElement(Operator.ADD, new Term(new Term(2), 2, "x")),
            new FunctionElement(Operator.ADD, new Term(new Term(1), 1, "x"))),
        new FunctionElement(Operator.ADD, new Term(new Term(1), 1, "x")));
    FunctionElement expected = new FunctionElement(Operator.ADD,
        new FunctionElement(Operator.ADD, new Term(new Term(2), 2, "x")),
        new FunctionElement(Operator.ADD, new Term(new Term(1), 2, "x")));
    actual.resolveBrackets();
    assertEquals(expected, actual);

  }

  @Test
  void simplificationRemovesMultiplicationAndDivisionFromNonBracketFunctionElements()
      throws ValueNotDefinedException {
    FunctionElement actual = new FunctionElement(Operator.ADD,
        new FunctionElement(Operator.ADD, new Term(5)),
        new FunctionElement(Operator.MULTIPLY,
            new Term(new Term(1), 3, "x")));
    FunctionElement expected = new FunctionElement(Operator.ADD,
        new Term(new Term(1), 15, "x"));
    actual.simplify();
    assertEquals(expected, actual, "Multiplication should be removed");
  }

  @Test
  void simplificationMergesTermsOnSameLevelWithBracketsInBetweenComponents()
      throws ValueNotDefinedException {
    FunctionElement actual  = new FunctionElement(Operator.ADD,
        new FunctionElement(Operator.ADD,
            new FunctionElement(Operator.ADD, new Term(5)),
            new FunctionElement(Operator.ADD, new Term(7))),
        new FunctionElement(Operator.MULTIPLY, new Term(new Term(1), 2, "x")));

    actual.simplify();
  }

  @Test
  void simplifyElementWithNestedBracket() throws ValueNotDefinedException {
    FunctionElement actual = multipleFe;
    multipleFe.simplify();
  }

  @Test
  void simplificationSumsTermsInBracket() throws ValueNotDefinedException {
    FunctionElement actual = new FunctionElement(Operator.ADD,
        new FunctionElement(Operator.ADD, new Term(new Term(1), 5, "x")),
        new FunctionElement(Operator.ADD, new Term(2)),
        new FunctionElement(Operator.ADD,
            new FunctionElement(Operator.ADD, new Term(4)),
            new FunctionElement(Operator.ADD, new Term(new Term(1), 3, "x")),
            new FunctionElement(Operator.ADD, new Term(2)),
            new FunctionElement(Operator.MULTIPLY, new Term(2))));

    FunctionElement expected = new FunctionElement(Operator.ADD,
        new FunctionElement(Operator.ADD, new Term(new Term(1), 8, "x")),
        new FunctionElement(Operator.ADD, new Term(10)));
    actual.simplify();
    assertEquals(expected, actual, "Elements should be summed");
  }

  @Test
  void multiplyNormalTerms() throws ValueNotDefinedException {
    FunctionElement actual = normal.multiply(normal);
    FunctionElement expected = new FunctionElement(Operator.ADD,
        new Term(new Term(4), 25, "x"));
    assertEquals(expected, actual, "Elements should have been multiplied");
  }

  @Test
  void multiplyNormalTermWithBracket() throws ValueNotDefinedException {
    FunctionElement actual = normal.multiply(multiple);
    FunctionElement expected = new FunctionElement(Operator.ADD,
        new FunctionElement(Operator.ADD, new Term(new Term(4), -15, "x")),
        new FunctionElement(Operator.ADD, new Term(new Term(3), 25, "x")),
        new FunctionElement(Operator.ADD, new Term(new Term(2), -35, "x")));
    assertEquals(expected, actual, "Elements should have been multiplied");
  }

  @Test
  void multiplyBracketWithBracket() throws ValueNotDefinedException {
    FunctionElement actual = multiple.multiply(multiple);
    FunctionElement expected = new FunctionElement(Operator.ADD,
        new FunctionElement(Operator.ADD, new Term(new Term(4), 9, "x")),
        new FunctionElement(Operator.ADD, new Term(new Term(3), -30, "x")),
        new FunctionElement(Operator.ADD, new Term(new Term(2), 67, "x")),
        new FunctionElement(Operator.ADD, new Term(new Term(1), -70, "x")),
        new FunctionElement(Operator.ADD, new Term(49)));
    assertEquals(expected, actual, "Elements should have been multiplied");
  }

  @Test
  void multiplyNormalWithNestedBracket() throws ValueNotDefinedException {
    FunctionElement actual = normal.multiply(multipleFe);
    FunctionElement expected;
  }

  @Test
  void divideNormalTerms() throws ValueNotDefinedException {
    FunctionElement actual = normal.divide(normal);
    FunctionElement expected = new FunctionElement(Operator.ADD,
        new Term(1));
    assertEquals(expected, actual, "Elements should have been divided");
  }

  @Test
  void divideNormalTermWithBracket() throws ValueNotDefinedException {
    FunctionElement actual = normal.divide(multiple);
    FunctionElement expected = new FunctionElement(Operator.ADD,
        new FunctionElement(Operator.ADD, new Term(-5.0 / 3)),
        new FunctionElement(Operator.ADD, new Term(new Term(1), 1, "x")),
        new FunctionElement(Operator.ADD, new Term(new Term(2), -5.0 / 7, "x")));
    assertEquals(expected, actual, "Elements should have been divided");
  }

  @Test
  void divideBracketWithBracket() throws ValueNotDefinedException {
    FunctionElement actual = multiple.divide(multiple);
    FunctionElement expected = new FunctionElement(Operator.ADD,
        new Term(1));
    assertEquals(expected, actual, "Elements should have been divided");
  }
}
