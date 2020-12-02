package de.hhn.it.pp.components.functionanalyzer;

public interface FunctionElementComponent {

  FunctionElementComponent add(FunctionElementComponent functionElementComponent);

  FunctionElementComponent subtract(FunctionElementComponent functionElementComponent);

  FunctionElementComponent divide(FunctionElementComponent functionElementComponent);

  FunctionElementComponent multiply(FunctionElementComponent functionElementComponent);

  double evaluate(double variableValue);

  FunctionElementComponent getDerivative();
}
