package de.hhn.it.pp.components.functionanalyzer.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import de.hhn.it.pp.components.functionanalyzer.Function;
import de.hhn.it.pp.components.functionanalyzer.FunctionElement;
import de.hhn.it.pp.components.functionanalyzer.Operator;
import de.hhn.it.pp.components.functionanalyzer.Term;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FunctionSpec {

  Function normal;

  @BeforeEach
  void init() {
    normal = new Function(
        new FunctionElement(Operator.ADD, new Term(new Term(2), 5, "x")),
        new FunctionElement(Operator.ADD, new Term(new Term(1), -3, "x")),
        new FunctionElement(Operator.ADD, new Term(8))
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
}
