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
import de.hhn.it.pp.components.functionanalyzer.provider.FunctionAnalyzer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FunctionAnalyzerSpec {

  FunctionAnalyzer functionAnalyzer;
  Function first;
  Function second;

  @BeforeEach
  void init(){
    functionAnalyzer = new FunctionAnalyzer();
    first = new Function(
        new FunctionElement(Operator.ADD, new Term(new Term(1.0), 2.0, "x")),
            new FunctionElement(Operator.MULTIPLY,
                new Term(new Term(1), 3, "x"),
                new FunctionElement(Operator.ADD,
                    new Term(new Term(1), 5),
                    new FunctionElement(Operator.ADD,
                        new Term(new Term(1), 7), new Term(new Term(1), 9))))
    );
    second = new Function(
            new FunctionElement(Operator.ADD, new Term(new Term(2), -2, "x")),
            new FunctionElement(Operator.ADD, new Term(new Term(1), 7.0/8.0, "x")),
            new FunctionElement(Operator.MULTIPLY,
                    new Term(new Term(1), 5),
                    new Term(new Term(1), 9, "x")),
            new FunctionElement(Operator.ADD, new Term(new Term(1), -7.0/9.0))
    );
  }

   @Test
  void functionCreatedFromString() {
    Function function = functionAnalyzer.readFunction("2*x*(3*x+(5+(7+9)))");
    assertEquals(first.toString(),
        function.toString(),
        () -> "Function should be created");
  }

  @Test
  void functionCreatedFromStringCheckEverything() {
    Function function = functionAnalyzer.readFunction("-2*x^2 + 7/8*x*(5 + 9*x) - 7/9");
    assertEquals(second.toString(),
        function.toString(),
        () -> "Function should be crated");
  }

  @Test
  void functionElementReturnsRequiredYPoint() {
    double expected = 9;
    double actual = functionAnalyzer.getTermValueFromFunctionElement(new FunctionElement(Operator.ADD,
            new Term(new Term(1), 3, "x")), 3);
    assertEquals(expected, actual, "should calc exact y Point");
  }

  @Test
  void functionElementWitchMultipleTermReturnsExactYPoint() {
    double actual = functionAnalyzer.getTermValueFromFunctionElement(
            new FunctionElement(Operator.ADD,
                    new FunctionElement(Operator.ADD, new Term(new Term(2), -5, "x")),
                    new FunctionElement(Operator.ADD, new Term(27))), 2);
    assertEquals(7, actual, "Should be the Exact Y Point for x = 2");
  }

  @Test
  void calculateFunctionValueForSimpleFunction() throws ValueNotDefinedException {
    double actual = functionAnalyzer.calculateFunctionValue(new Function(
            new FunctionElement(Operator.ADD, new Term(new Term(4), -2, "x")),
            new FunctionElement(Operator.ADD, new Term(new Term(3), 1, "x")),
            new FunctionElement(Operator.ADD,
                    new FunctionElement(Operator.ADD, new Term(5)),
                    new FunctionElement(Operator.ADD, new Term(new Term(1), -6, "x")))
    ), 2);
    assertEquals(-31,actual, "Should get the exact Y Value");
  }

  @Test
  void getXIntersectionWithOneIntersection() {
    List<Double> expected = new ArrayList<>();
    expected.add(2.0);

    List<Double> actual = new Function(
        new FunctionElement(Operator.ADD, new Term(new Term(1), 2, "x")),
        new FunctionElement(Operator.ADD, new Term(-4))
    ).setFunctionEqualZero();

    assertEquals(expected, actual, "Should get a List of intersictione with the X Axis");
  }

  @Test
  void getXIntersectionWithMultipleIntersections() {
    List<Double> expected = new ArrayList<>();
    expected.add(2.0);
    expected.add(-2.0);
    List<Double> actual = new Function(
        new FunctionElement(Operator.ADD, new Term(new Term(2), 1, "x")),
        new FunctionElement(Operator.ADD, new Term(-4))
    ).setFunctionEqualZero();
    assertEquals(expected, actual, "Should get a List of intersictione with the X Axis");
  }

  @Test
  void getXIntersectionWithNoIntersection() {
    List<Double> actual = new Function(
        new FunctionElement(Operator.ADD, new Term(new Term(2), 1, "x")),
        new FunctionElement(Operator.ADD, new Term(4))
    ).setFunctionEqualZero();
    assertNull(actual, "Should get a List of intersictione with the X Axis");
  }

  @Test
  void getYIntersectionWithOneIntersection() throws ValueNotDefinedException {
    Function temp = new Function(
        new FunctionElement(Operator.ADD, new Term(new Term(2), 1, "x")),
        new FunctionElement(Operator.ADD, new Term(new Term(1), 2, "x")),
        new FunctionElement(Operator.ADD, new Term(-4))
    );
    double actual = functionAnalyzer.calculateYIntersection(temp);
    assertEquals(-4, actual, "Should get a List of intersictione with the X Axis");
  }

  @Test
  void getYIntersectionWithNoIntersection() throws ValueNotDefinedException {
    Function temp = new Function(
        new FunctionElement(Operator.ADD, new Term(new Term(-1), 1, "x"))
    );
    double actual = functionAnalyzer.calculateYIntersection(temp);
    assertEquals(Double.POSITIVE_INFINITY, actual,
        "Should get a List of intersictione with the X Axis");
  }

  @Test
  void getOneMinimaForComplexFunction() throws ValueNotDefinedException {
    Function temp = new Function(
        new FunctionElement(Operator.ADD, new Term(new Term(2), 1, "x")),
        new FunctionElement(Operator.ADD, new Term(new Term(1), 2, "x")),
        new FunctionElement(Operator.ADD, new Term(-4))
    );
    List<Double> actual = functionAnalyzer.calculateMinima(temp);
    List<Double> expected = new ArrayList<>();
    expected.add(-5.0);
    assertEquals(expected, actual, "This should calculate the Minima");
  }

  @Test
  void getNoMinimaForComplexFunction() throws ValueNotDefinedException {
    Function temp = new Function(
        new FunctionElement(Operator.ADD, new Term(new Term(3), 3, "x")),
        new FunctionElement(Operator.ADD, new Term(new Term(2), 1, "x")),
        new FunctionElement(Operator.ADD, new Term(new Term(1), 2, "x")),
        new FunctionElement(Operator.ADD, new Term(-4))
    );
    List<Double> actual = functionAnalyzer.calculateMinima(temp);
    assertNull(actual, "This should calculate the Minima");
  }
}
