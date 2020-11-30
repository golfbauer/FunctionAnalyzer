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
    input = input.replaceAll("\\s", "");
    ArrayList<String> terms = new ArrayList<>();
    int lastTerm = 0;
    for (int i = 0; i < input.length(); i++){
      if (input.charAt(i) == '+' || input.charAt(i) == '-' && i != 0){
        terms.add((input.charAt(lastTerm) != '-' && input.charAt(lastTerm) != '+' ? "+" : "") +
            input.substring(lastTerm, i));
        lastTerm = i;
      }
    }
    terms.add(input.substring(lastTerm));
    return null;
  }

  public FunctionElement translate(String input){
    String buffer = "";
    boolean klammer = false;
    for(int i = 1; i < input.length(); i++) {
      switch(input.charAt(i)){
        case('('): klammer = true;
        case('*'): ;
        case('/'): ;
        case('x'):
          if(buffer == ""){
            double val = 1;
          }else {
            Term term = new Term(new Term(1), Double.parseDouble(buffer), "x");
          }
        case('y'): ;
        case('^'): ;
        case(')'): klammer = false;
        default: buffer += input.charAt(i);
      }
    }

    return null;
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
