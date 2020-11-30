package de.hhn.it.pp.components.functionanalyzer;

public class FunctionElement {

  private Term term;
  private Operator operator;

  /**
   * Combines both the Term and the operator it belongs to
   * @param term = single term
   * @param operator = its operator
   */
  public FunctionElement(Term term, Operator operator) {
    this.term = term;
    this.operator = operator;
  }

  public Term getTerm() {
    return term;
  }

  public Operator getOperator() {
    return operator;
  }

  @Override
  public String toString() {
    return "" + operator.getSymbol() + term;
  }
}

