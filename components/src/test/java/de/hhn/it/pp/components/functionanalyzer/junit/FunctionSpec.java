package de.hhn.it.pp.components.functionanalyzer.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import de.hhn.it.pp.components.functionanalyzer.Function;
import de.hhn.it.pp.components.functionanalyzer.FunctionElement;
import de.hhn.it.pp.components.functionanalyzer.Operator;
import de.hhn.it.pp.components.functionanalyzer.Term;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FunctionSpec {

  Function normal;
  Function linear;
  Function simple;

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
  }

  @Test
  void getDervitiveOfFunction() {
    Function expected = new Function(
        new FunctionElement(Operator.ADD, new Term(new Term(1), 10, "x")),
        new FunctionElement(Operator.ADD, new Term(-3))
    );
    Function actual = normal.getDerivative();
    assertEquals(expected.toString(), actual.toString(), "Sould be the derivative of Function");
  }

  @Test
  void getValueFromFunctionWithoutX() {
    assertEquals(null, simple.setFunctionEqualZero(), "Should produce null since its never gonna" +
            " Intercept with x Axis");
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
