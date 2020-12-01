package de.hhn.it.pp.components.functionanalyzer.provider;

import java.util.ArrayList;
import java.util.List;
import de.hhn.it.pp.components.functionanalyzer.Function;
import de.hhn.it.pp.components.functionanalyzer.FunctionAnalyserService;
import de.hhn.it.pp.components.functionanalyzer.FunctionElement;
import de.hhn.it.pp.components.functionanalyzer.Operator;
import de.hhn.it.pp.components.functionanalyzer.Term;
import de.hhn.it.pp.components.functionanalyzer.exceptions.ValueNotDefinedException;

public class FunctionAnalyzer implements FunctionAnalyserService {


  @Override
  public Function readFunction(String input) throws IllegalArgumentException {
    Function res = new Function();
    input = input.replaceAll("\\s", "");
    ArrayList<String> terms = new ArrayList<>();
    int lastTerm = 0;
    for (int i = 0; i < input.length(); i++) {
      if (input.charAt(i) == '+' || input.charAt(i) == '-' && i != 0) {
        terms.add((input.charAt(lastTerm) != '-'
            && input.charAt(lastTerm) != '+' ? "+" : "") + input.substring(lastTerm, i));
        lastTerm = i;
      }
    }
    terms.add(input.substring(lastTerm));
    for (int i = 0; i < terms.size(); i++) {
      FunctionElement element = getTerm(terms.get(i));
      res.add(element);
    }
    return res;
  }

  public FunctionElement getTerm(String input) {
    String buffer = "";
    double factor = 0;
    double exponent = 1;
    String variable = null;
    ArrayList<String> nums = new ArrayList<>();
    for (int i = 0; i < input.length(); i++) {
      if (input.charAt(i) == 'x') {
        nums.add(buffer.substring(0,i - 1));
        buffer = buffer.substring(buffer.length() - 1);
      } else if (input.charAt(i) == '^') {
        nums.add(buffer);
        buffer = "";
      }
      buffer += input.charAt(i);
      if (i == input.length() - 1 && buffer != "") {
        nums.add(buffer);
      }
    }

    for (int i = 0; i < nums.size(); i++) {
      if(i == 0 && nums.get(i).contains("x")) {
        factor = Double.parseDouble(nums.get(0).charAt(0) + "1");
        variable = "x";
      } else  {
        factor = Double.parseDouble(nums.get(0));
      }
      if (nums.get(i).equals("/x")) {
        variable = "x";
        exponent = -1;
      } else if (nums.get(i).equals("*x")) {
        variable = "x";
        exponent = 1;
      }
      if (nums.get(i).contains("^")) {
        exponent = exponent * Double.parseDouble(nums.get(i).substring(1));
      }
    }

    if (variable == null && exponent > 1) {
      return new FunctionElement(new Term(new Term(exponent), factor), Operator.operatorFromSymbol('+'));
    } else if(variable != null) {
      return new FunctionElement(new Term(new Term(exponent), factor, variable),
          Operator.operatorFromSymbol('+'));
    }
    return new FunctionElement(new Term(factor), Operator.operatorFromSymbol('+'));
  }








  @Override
  public List<Double> calculateMinima(Function f) throws ValueNotDefinedException {
    return null;
  }

  @Override
  public List<Double> calculateMaxima(Function f) throws ValueNotDefinedException {
    return null;
  }

  @Override
  public List<Double> calculateXIntersection(Function f) throws ValueNotDefinedException {
    return null;
  }

  @Override
  public List<Double> calculateYIntersection(Function f) throws ValueNotDefinedException {
    return null;
  }

  @Override
  public List<Double> calculateFunctionValue(Function f, double functionParameter)
      throws ValueNotDefinedException {
    return null;
  }

  @Override
  public List<Double> calculatePointIntersection(Function f, double functionValue)
      throws ValueNotDefinedException {
    return null;
  }
}
