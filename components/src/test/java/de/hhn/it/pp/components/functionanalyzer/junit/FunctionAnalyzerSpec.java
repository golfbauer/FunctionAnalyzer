package de.hhn.it.pp.components.functionanalyzer.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import de.hhn.it.pp.components.functionanalyzer.Function;
import de.hhn.it.pp.components.functionanalyzer.FunctionElement;
import de.hhn.it.pp.components.functionanalyzer.Operator;
import de.hhn.it.pp.components.functionanalyzer.Term;
import de.hhn.it.pp.components.functionanalyzer.exceptions.ValueNotDefinedException;
import de.hhn.it.pp.components.functionanalyzer.provider.FunctionAnalyzer;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


public class FunctionAnalyzerSpec {

  FunctionAnalyzer functionAnalyzer;
  Function simpleBracketFunction;
  Function complexBracketFunction;
  Function quadraticFunction;

  @BeforeEach
  void init() {
    functionAnalyzer = new FunctionAnalyzer();
    quadraticFunction = new Function(
        new FunctionElement(Operator.ADD, new Term(new Term(2), 1, "x")),
        new FunctionElement(Operator.ADD, new Term(new Term(1), 2, "x")),
        new FunctionElement(Operator.ADD, new Term(-4))
    );
    simpleBracketFunction = new Function(
        new FunctionElement(Operator.ADD, new Term(new Term(1.0), 2.0, "x")),
        new FunctionElement(Operator.MULTIPLY,
            new FunctionElement(Operator.ADD, new Term(new Term(1), 3, "x")),
            new FunctionElement(Operator.ADD,
                new FunctionElement(Operator.ADD, new Term(5)),
                new FunctionElement(Operator.ADD,
                    new FunctionElement(Operator.ADD, new Term(7)),
                    new FunctionElement(Operator.ADD, new Term(9)))))
    );
    complexBracketFunction = new Function(
        new FunctionElement(Operator.ADD, new Term(new Term(2), -2, "x")),
        new FunctionElement(Operator.ADD, new Term(new Term(1), 7.0 / 8.0, "x")),
        new FunctionElement(Operator.MULTIPLY,
            new FunctionElement(Operator.ADD, new Term(5)),
            new FunctionElement(Operator.ADD, new Term(new Term(1), 9, "x"))),
        new FunctionElement(Operator.ADD, new Term(-7.0 / 9.0))
    );
  }

  @Nested
  class ReadFunction {

    @Test
    void functionCreatedFromWorkingString() {
      Function function = functionAnalyzer.readFunction("2*x*(3*x+(5+(7+9)))");
      Function expected = new Function(
          new FunctionElement(Operator.ADD, new Term(new Term(2), 6, "x")),
          new FunctionElement(Operator.ADD, new Term(new Term(1), 42, "x")));
      assertEquals(expected, function, "Function should be created");
    }

    @Test
    void functionCreatedFromComplexWorkingString() {
      Function function = functionAnalyzer.readFunction("-2*x^2 + 7/8*x*(5 + 9*x) - 7/9");
      Function expected = new Function(
          new FunctionElement(Operator.ADD, new Term(new Term(2), 5.875, "x")),
          new FunctionElement(Operator.ADD, new Term(new Term(1), 4.375, "x")),
          new FunctionElement(Operator.ADD, new Term(-7.0 / 9)));
      assertEquals(expected, function, "Function should be crated");
    }

    @Test
    void functionCreatedFromEmptyString() {
      Function actual = functionAnalyzer.readFunction("");
      assertEquals(new Function(), actual, "Should create an empty Function");
    }
  }

  @Nested
  class CalculateMinima {

    @Test
    void getOneMinimaForComplexFunction() throws ValueNotDefinedException {
      List<Double> actual = functionAnalyzer.calculateMinima(quadraticFunction);
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
      assertNull(actual, "This should give out null cause there is no minim");
    }
  }

  @Nested
  class CalculateMaxima {

    @Test
    void getNoMaximaFromMostSimpleFunction() throws ValueNotDefinedException {
      Function temp = new Function(new FunctionElement(Operator.ADD, new Term(5)));
      List<Double> actual = functionAnalyzer.calculateMaxima(temp);
      assertNull(actual, "Should get no Maxima cause of simple function");
    }

    @Test
    void getNoMaximaFromSecondMostSimpleFunction() throws ValueNotDefinedException {
      Function temp = new Function(new FunctionElement(Operator.ADD,
          new Term(new Term(1), 2, "x")));
      List<Double> actual = functionAnalyzer.calculateMaxima(temp);
      assertNull(actual, "Should get no Maxima cause of simple function");
    }
  }

  @Nested
  class CalculateXIntersection {

    @Test
    void getXIntersectionWithOneIntersection() throws ValueNotDefinedException {
      List<Double> expected = new ArrayList<>();
      expected.add(0.0);

      Function temp = new Function(
          new FunctionElement(Operator.ADD, new Term(new Term(2), 2, "x"))
      );
      List<Double> actual = functionAnalyzer.calculateXIntersection(temp);
      assertEquals(expected, actual, "Should get a List of intersictione with the X Axis");
    }

    @Test
    void getXIntersectionWithMultipleIntersections() throws ValueNotDefinedException {
      List<Double> expected = new ArrayList<>();
      expected.add(2.0);
      expected.add(-2.0);
      Function temp = new Function(
          new FunctionElement(Operator.ADD, new Term(new Term(2), 1, "x")),
          new FunctionElement(Operator.ADD, new Term(-4))
      );
      List<Double> actual = functionAnalyzer.calculateXIntersection(temp);
      assertEquals(expected, actual, "Should get a List of intersictione with the X Axis");
    }

    @Test
    void getXIntersectionWithNoIntersection() throws ValueNotDefinedException {
      Function temp = new Function(
          new FunctionElement(Operator.ADD, new Term(new Term(2), 1, "x")),
          new FunctionElement(Operator.ADD, new Term(4))
      );
      List<Double> actual = functionAnalyzer.calculateXIntersection(temp);
      assertNull(actual, "Should get a List of intersictione with the X Axis");
    }
  }

  @Nested
  class CalculateYIntersection {

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
  }

  @Nested
  class CalculateFunctionValue {

    @Test
    void calculateFunctionValueForSimpleFunction() throws ValueNotDefinedException {
      double actual = functionAnalyzer.calculateFunctionValue(new Function(
          new FunctionElement(Operator.ADD, new Term(new Term(4), -2, "x")),
          new FunctionElement(Operator.ADD, new Term(new Term(3), 1, "x")),
          new FunctionElement(Operator.ADD,
              new FunctionElement(Operator.ADD, new Term(5)),
              new FunctionElement(Operator.ADD, new Term(new Term(1), -6, "x")))
      ), 2);
      assertEquals(-31, actual, "Should get the exact Y Value");
    }

    @Test
    void calculateFunctionValueForFunctionWithoutX() throws ValueNotDefinedException {
      double actual = functionAnalyzer.calculateFunctionValue(new Function(
          new FunctionElement(Operator.ADD, new Term(7))
      ), 4);
      assertEquals(7, actual, "Should return the exact same as input");
    }
  }

  @Nested
  class CalculatePointIntersection {

    @Test
    void calcXForSpecificYValue() throws ValueNotDefinedException {
      List<Double> actual = functionAnalyzer
          .calculatePointIntersection(quadraticFunction, 3);
      List<Double> expected = new ArrayList<>();
      expected.add(1.8284271247461903);
      expected.add(-3.8284271247461903);
      assertEquals(expected, actual, "Should calc the X Values for Y Value");
    }

    @Test
    void calcOneXForSpecificYValue() throws ValueNotDefinedException {
      List<Double> actual = functionAnalyzer
          .calculatePointIntersection(quadraticFunction, -5);
      List<Double> expected = new ArrayList<>();
      expected.add(-1.0);
      assertEquals(expected, actual, "Should calc the X Values for Y Value");
    }

    @Test
    void calcOneXForSpecificyValue() throws ValueNotDefinedException {
      List<Double> actual = functionAnalyzer
          .calculatePointIntersection(new Function(
              new FunctionElement(Operator.ADD, new Term(3))), -5);
      List<Double> expected = new ArrayList<>();
      expected.add(-5.0);
      assertEquals(expected, actual, "Should calc the X Values for Y Value");
    }
  }
}
