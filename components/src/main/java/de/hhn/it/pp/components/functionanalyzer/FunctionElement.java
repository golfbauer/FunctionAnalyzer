package de.hhn.it.pp.components.functionanalyzer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FunctionElement implements FunctionElementComponent {

  private List<Term> components = new ArrayList<>();
  private Operator operator;

  /**
   * Combines both the Term and the operator it belongs to
   * @param components = single term
   * @param operator = its operator
   */
  public FunctionElement(Operator operator, Term... components) {
    this.components.addAll(Arrays.asList(components));
    this.operator = operator;
  }

  public FunctionElement(Operator operator) {
    this.operator = operator;
  }

  public List<Term> getComponents() {
    return components;
  }

  public void addTerm(Term term) {
    components.add(term);
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
  public FunctionElementComponent add(FunctionElementComponent functionElementComponent) {
    return null;
  }

  @Override
  public FunctionElementComponent subtract(FunctionElementComponent functionElementComponent) {
    return null;
  }

  @Override
  public FunctionElementComponent divide(FunctionElementComponent functionElementComponent) {
    return null;
  }

  @Override
  public FunctionElementComponent multiply(FunctionElementComponent functionElementComponent) {
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

