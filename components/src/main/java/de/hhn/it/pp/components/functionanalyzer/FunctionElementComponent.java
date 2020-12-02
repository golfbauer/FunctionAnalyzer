package de.hhn.it.pp.components.functionanalyzer;

import de.hhn.it.pp.components.functionanalyzer.exceptions.ValueNotDefinedException;

public interface FunctionElementComponent {

  FunctionElementComponent add(FunctionElementComponent functionElementComponent)
      throws ValueNotDefinedException;

  FunctionElementComponent subtract(FunctionElementComponent functionElementComponent)
      throws ValueNotDefinedException;

  FunctionElementComponent divide(FunctionElementComponent functionElementComponent)
      throws ValueNotDefinedException;

  FunctionElementComponent multiply(FunctionElementComponent functionElementComponent)
      throws ValueNotDefinedException;

  double evaluate(double variableValue);

  FunctionElementComponent getDerivative();
}
