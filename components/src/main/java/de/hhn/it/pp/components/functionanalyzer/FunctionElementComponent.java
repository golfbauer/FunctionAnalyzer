package de.hhn.it.pp.components.functionanalyzer;

import de.hhn.it.pp.components.functionanalyzer.exceptions.ValueNotDefinedException;

public interface FunctionElementComponent {

  void simplify() throws ValueNotDefinedException;

  double evaluate(double variableValue);

  FunctionElementComponent getDerivative();
}
