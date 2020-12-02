package de.hhn.it.pp.components.functionanalyzer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import de.hhn.it.pp.components.functionanalyzer.exceptions.ValueNotDefinedException;

public class FunctionElement implements FunctionElementComponent {

  private List<FunctionElementComponent> components = new ArrayList<FunctionElementComponent>();
  private Operator operator;

  /**
   * Combines both the Term and the operator it belongs to
   * @param components = single term
   * @param operator = its operator
   */
  public FunctionElement(Operator operator, FunctionElementComponent... components) {
    this.components.addAll(Arrays.asList(components));
    this.operator = operator;
  }

  public FunctionElement(Operator operator) {
    this.operator = operator;
  }

  public List<FunctionElementComponent> getComponents() {
    return components;
  }

  public void addFunctionElementComponent(FunctionElementComponent functionElementComponent) {
    components.add(functionElementComponent);
  }

  public Operator getOperator() {
    return operator;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append(operator.getSymbol());
    if (components.size() > 1) {                       //Notdürftige Lösung: FEs mit mehr als einem Term
      components.forEach(fe -> builder.append("+" + fe.toString())); //haben untereinander keine
    } else {                                      //Verbindung deswegen das "+" + im stream
      components.forEach(fe -> builder.append(fe.toString()));
    }
    return builder.toString();
  }

  @Override
  public FunctionElementComponent add(FunctionElementComponent functionElementComponent)
      throws ValueNotDefinedException {
    return null;
  }

  @Override
  public FunctionElementComponent subtract(FunctionElementComponent functionElementComponent)
      throws ValueNotDefinedException {
    return null;
  }

  @Override
  public FunctionElementComponent divide(FunctionElementComponent functionElementComponent)
      throws ValueNotDefinedException {
    return null;
  }

  @Override
  public FunctionElementComponent multiply(FunctionElementComponent functionElementComponent)
      throws ValueNotDefinedException {
    return null;
  }

  @Override
  public double evaluate(double variableValue) {
    return 0;
  }

  @Override
  public FunctionElementComponent getDerivative() {
    return null;
  }
}

