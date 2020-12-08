package de.hhn.it.pp.components.functionanalyzer.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.util.ArrayList;
import java.util.List;
import de.hhn.it.pp.components.functionanalyzer.Function;
import de.hhn.it.pp.components.functionanalyzer.FunctionElement;
import de.hhn.it.pp.components.functionanalyzer.Operator;
import de.hhn.it.pp.components.functionanalyzer.Term;
import de.hhn.it.pp.components.functionanalyzer.exceptions.ValueNotDefinedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class FunctionSpec {

  Function normal;
  Function linear;
  Function simple;
  Function simplifiableFunction;
  Function simplifiableFunctionWithBrackets;


  @BeforeEach
  void init() {
    simple = new Function(
            new FunctionElement(Operator.ADD, new Term(5))
    );
    normal = new Function(
        new FunctionElement(Operator.ADD, new Term(new Term(2), 5, "x")),
        new FunctionElement(Operator.ADD, new Term(new Term(1), -3, "x")),
        new FunctionElement(Operator.ADD, new Term(8))
    );
    linear = new Function(
            new FunctionElement(Operator.ADD, new Term(new Term(1), -3, "x")),
            new FunctionElement(Operator.ADD, new Term(9))
    );
    simplifiableFunction = new Function(
        new FunctionElement(Operator.ADD, new Term(3)),
        new FunctionElement(Operator.MULTIPLY, new Term(new Term(1), 2, "x")),
        new FunctionElement(Operator.ADD, new Term(5))
    );
    simplifiableFunctionWithBrackets = new Function(
        new FunctionElement(Operator.ADD, new Term(10)),
        new FunctionElement(Operator.DIVIDE,
            new FunctionElement(Operator.ADD, new Term(new Term(1), 5, "x")),
            new FunctionElement(Operator.ADD, new Term(10))),
        new FunctionElement(Operator.MULTIPLY, new Term(10))
    );
  }

  @Nested
  class GetDerivative {

    @Test
    void getDerivativeOfFunction() {
      Function expected = new Function(
          new FunctionElement(Operator.ADD, new Term(new Term(1), 10, "x")),
          new FunctionElement(Operator.ADD, new Term(-3))
      );
      Function actual = normal.getDerivative();
      assertEquals(expected.toString(), actual.toString(),
          "Sould be the derivative of Function");
    }

    @Test
    void getDerivativeForEmptyFunction() {
      Function actual = new Function().getDerivative();
      assertEquals(new Function(), actual, "Sould be the derivative of Function");
    }

    @Test
    void getDerivativeForMostSimpleFunction() {
      Function actual = simple.getDerivative();
      assertEquals(new Function(), actual, "Sould be the derivative of Function");
    }
  }

  @Nested
  class SetFunctionEqualZero {

    @Test
    void getValueFromFunctionWithoutX() {
      assertNull(simple.setFunctionEqualZero(), "Should produce null since its never gonna"
          + " Intercept with x Axis");
    }

    @Test
    void getXValueFromLinearFunction() {
      List<Double> actual  = linear.setFunctionEqualZero();
      List<Double> expected = new ArrayList<>();
      expected.add(3.0);
      assertEquals(expected, actual, "Should get the x Values for Function equal to 0");
    }

    @Test
    void getXValueForSingleLinearFunction() {
      List<Double> actual = new Function(
          new FunctionElement(Operator.ADD, new Term(new Term(1), 4, "x"))
      ).setFunctionEqualZero();
      List<Double> expected = new ArrayList<>();
      expected.add(0.0);
      assertEquals(expected, actual, "Should get the x Values for Function equal to 0");
    }

    @Test
    void getValueForSquareFunction() {
      List<Double> actual = new Function(
          new FunctionElement(Operator.ADD, new Term(new Term(2), 3, "x")),
          new FunctionElement(Operator.ADD, new Term(new Term(1), 5, "x")),
          new FunctionElement(Operator.ADD, new Term(1))
      ).setFunctionEqualZero();
      List<Double> expected = new ArrayList<>();
      expected.add(-0.2324081207560017);
      expected.add(-1.434258545910665);
      assertEquals(expected, actual, "Should get the x Values for Function equal to 0");
    }

    @Test
    void getValueForSquareWithoutPFunction() {
      List<Double> actual = new Function(
          new FunctionElement(Operator.ADD, new Term(new Term(2), 3, "x")),
          new FunctionElement(Operator.ADD, new Term(new Term(1), 5, "x"))
      ).setFunctionEqualZero();
      List<Double> expected = new ArrayList<>();
      expected.add(0.0);
      expected.add(-1.6666666666666667);
      assertEquals(expected, actual, "Should get the x Values for Function equal to 0");
    }


    @Test
    void getValueForSquareWithoutQFunction() {
      List<Double> actual = new Function(
          new FunctionElement(Operator.ADD, new Term(new Term(2), 5, "x")),
          new FunctionElement(Operator.ADD, new Term(-8))
      ).setFunctionEqualZero();
      List<Double> expected = new ArrayList<>();
      expected.add(1.2649110640673518);
      expected.add(-1.2649110640673518);
      assertEquals(expected, actual, "Should get the x Values for Function equal to 0");
    }
  }

  @Test
  void simplifyAlreadySimpleFunctionWithOneTerm() throws ValueNotDefinedException {
    Function actual = new Function(
        new FunctionElement(Operator.ADD, new Term(5)));
    Function expected = simple;
    actual.simplify();
    assertEquals(expected, actual, "Function should not have changed");
  }

  @Test
  void simplifyAlreadySimpleFunctionWithMultipleElements() throws ValueNotDefinedException {
    Function actual = new Function(
        new FunctionElement(Operator.ADD, new Term(new Term(1), -3, "x")),
        new FunctionElement(Operator.ADD, new Term(9)));
    actual.simplify();
    Function expected = linear;
    assertEquals(expected, actual, "Function should not have changed");
  }

  @Test
  void simplifySimplifiableFunction() throws ValueNotDefinedException {
    Function actual = simplifiableFunction;
    Function expected = new Function(
        new FunctionElement(Operator.ADD, new Term(new Term(1), 6, "x")),
        new FunctionElement(Operator.ADD, new Term(5)));
    actual.simplify();
    assertEquals(expected, actual, "Function should be simplified");
  }

  @Test
  void simplifySimplifiableFunctionWithBrackets() throws ValueNotDefinedException {
    Function actual = simplifiableFunctionWithBrackets;
    actual.simplify();
    Function expected = new Function(
        new FunctionElement(Operator.ADD, new Term(new Term(-1), 20, "x")),
        new FunctionElement(Operator.ADD, new Term(10))
    );
    assertEquals(expected, actual, "Function should be simplified");
  }

  @Nested
  class CalcFunctionValue {

    @Test
    void getValueFromFunctionWithX() {
      double actual = normal.calcFunctionValue(2);
      assertEquals(22, actual, "Should give out y value for 2");
    }
  }
}
