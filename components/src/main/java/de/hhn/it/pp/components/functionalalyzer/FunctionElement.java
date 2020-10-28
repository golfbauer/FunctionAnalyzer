package de.hhn.it.pp.components.functionalalyzer;

public class FunctionElement {

  private Term term;
  private String operator;

  /**
   * Combines both the Term and the operator it belongs to
   * @param term = single term
   * @param operator = its operator
   */
  public FunctionElement(Term term, String operator) {
    this.term = term;
    this.operator = operator;
  }
}

