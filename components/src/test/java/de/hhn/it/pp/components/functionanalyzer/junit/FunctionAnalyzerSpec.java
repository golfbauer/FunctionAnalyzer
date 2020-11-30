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

  @BeforeEach
  void init(){
    functionAnalyzer = new FunctionAnalyzer();
  }

  @Test
  void functionCreatedFromString() {
    Function function = functionAnalyzer.readFunction("2x^2-3x - 2 + 4");
    assertEquals(new Function(
        new FunctionElement(new Term(new Term(1.0), 3.0, "x"), Operator.ADD),
        new FunctionElement(new Term(4), Operator.ADD)),
        function,
        () -> "Function should be created");
  }
}
