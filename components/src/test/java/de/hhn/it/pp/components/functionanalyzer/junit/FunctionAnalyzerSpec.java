package de.hhn.it.pp.components.functionanalyzer.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import de.hhn.it.pp.components.functionanalyzer.Function;
import de.hhn.it.pp.components.functionanalyzer.FunctionElement;
import de.hhn.it.pp.components.functionanalyzer.Operator;
import de.hhn.it.pp.components.functionanalyzer.Term;
import de.hhn.it.pp.components.functionanalyzer.provider.FunctionAnalyzer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FunctionAnalyzerSpec {

  FunctionAnalyzer functionAnalyzer;
  Function first;

  @BeforeEach
  void init(){
    functionAnalyzer = new FunctionAnalyzer();
    first = new Function(
        new FunctionElement(new Term(new Term(-2.0), 2.0, "x"), Operator.ADD),
        new FunctionElement(new Term(new Term(1.0), -3.0, "x"), Operator.ADD),
        new FunctionElement(new Term(-2.0), Operator.ADD),
        new FunctionElement(new Term(4), Operator.ADD));
  }

  @Test
  void functionCreatedFromString() {
    Function function = functionAnalyzer.readFunction("2/x^2-3*x - 2 + 4");
    assertEquals(first.toString(),
        function.toString(),
        () -> "Function should be created");
  }
}
