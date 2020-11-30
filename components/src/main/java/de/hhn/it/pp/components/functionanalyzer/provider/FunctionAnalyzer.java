package de.hhn.it.pp.components.functionanalyzer.provider;

import java.util.List;
import de.hhn.it.pp.components.functionanalyzer.Function;
import de.hhn.it.pp.components.functionanalyzer.FunctionAnalyserService;
import de.hhn.it.pp.components.functionanalyzer.exceptions.ValueNotDefinedException;

public class FunctionAnalyzer implements FunctionAnalyserService {


  @Override
  public Function readFunction(String input) throws IllegalArgumentException {
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
