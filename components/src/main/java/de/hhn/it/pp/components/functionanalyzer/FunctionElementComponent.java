package de.hhn.it.pp.components.functionanalyzer;

import de.hhn.it.pp.components.functionanalyzer.exceptions.ValueNotDefinedException;

public interface FunctionElementComponent {

  void simplify();

  double evaluate(double variableValue);

  FunctionElementComponent getDerivative();
}
