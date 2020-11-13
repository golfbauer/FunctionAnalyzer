package de.hhn.it.pp.components.functionanalyzer;

import java.util.ArrayList;
import java.util.Arrays;

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
  public Function getDerivative(){
    Function derivative = new Function();
    forEach(e -> derivative.add(new FunctionElement(e.getTerm().getDerivative(), e.getOperator())));
    return derivative;
  }
}
