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
                        new Term(new Term(1), 7), new Term(new Term(1), 9)
                    ))));
    second = new Function(
            new FunctionElement(Operator.ADD, new Term(new Term(2), -2, "x")),
            new FunctionElement(Operator.ADD, new Term(new Term(1), 7.0/8.0, "x")),
            new FunctionElement(Operator.MULTIPLY,
                    new Term(new Term(1), 5),
                    new Term(new Term(1), 9, "x")),
                new FunctionElement(Operator.ADD,
                    new Term(new Term(1), -7.0/9.0))
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
  void FunctionCreatedFromStringCheckEverything() {
    Function function = functionAnalyzer.readFunction("-2*x^2 + 7/8*x*(5 + 9*x) - 7/9");
    assertEquals(second.toString(),
        function.toString(),
        () -> "Function should be crated");
  }
}
