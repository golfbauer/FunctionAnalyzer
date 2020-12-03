package de.hhn.it.pp.components.functionanalyzer.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import de.hhn.it.pp.components.functionanalyzer.FunctionElement;
import de.hhn.it.pp.components.functionanalyzer.Operator;
import de.hhn.it.pp.components.functionanalyzer.Term;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}
