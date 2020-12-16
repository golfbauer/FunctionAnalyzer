package de.hhn.it.pp.components.functionanalyzer;

import de.hhn.it.pp.components.functionanalyzer.exceptions.ValueNotDefinedException;

public interface FunctionElementComponent {

  /**
   * Simplifys Function into a fixed structure
   * @throws ValueNotDefinedException throw error
   */
  void simplify() throws ValueNotDefinedException;

  /**
   * Calculate the Derivative.
   * @return Derivative
   */
  FunctionElementComponent getDerivative();
}
