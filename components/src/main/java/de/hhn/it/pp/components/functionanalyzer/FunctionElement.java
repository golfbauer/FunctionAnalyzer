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
      components.forEach(fe -> builder.append(fe.toString()));

    return builder.toString();
  }


  @Override
  public void simplify() {

  }

  @Override
  public double evaluate(double variableValue) {
    return 0;
  }

  @Override
  public FunctionElement getDerivative() {
    FunctionElement result = new FunctionElement(operator);
    if (components.get(0) instanceof FunctionElement) {
      for (int i = 0; i < components.size(); i++) {
        result.addFunctionElementComponent(components.get(i).getDerivative());
      }
    } else if (components.get(0) instanceof Term) {
      for (int j = 0; j < components.size(); j++) {
        if (components.get(j).getDerivative() != null) {
          result.addFunctionElementComponent(components.get(j).getDerivative());
        } else if(components.size() == 1) {
          return null;
        }
      }
    }
    for(int i = 0; i < result.components.size(); i++) {
      if (result.components.get(i) == null) {
        result.components.remove(i);
      }
    }
    return result;
  }
}

