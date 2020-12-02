package de.hhn.it.pp.components.functionanalyzer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represent a function as a combination of Terms
 */
public class Function extends ArrayList<FunctionElement> {

  /**
   * Add FunctionElements into the function
   * @param elements Contains all FunctionElements
   */
  public Function(FunctionElement... elements) {
    addAll(Arrays.asList(elements));
  }

  /**
   * Calculate the Derivative of Function
   * @return Derivative of the Function
   */
  public Function getDerivative() {
    Function derivative = new Function();
    List<Term> derivativeTerms = new ArrayList<>();
    forEach(functionElement ->
        functionElement.getComponents().forEach(term ->
            derivative.add(new FunctionElement
                (functionElement.getOperator(), term.getDerivative()))));
    return derivative;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("f(x) = ");
    this.forEach(fe -> builder.append(fe.toString()));
    return builder.toString();

  }
}
