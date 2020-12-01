package de.hhn.it.pp.components.functionanalyzer;

import static java.util.Collections.addAll;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FunctionElement {

  private List<Term> terms = new ArrayList<>();
  private Operator operator;

  /**
   * Combines both the Term and the operator it belongs to
   * @param terms = single term
   * @param operator = its operator
   */
  public FunctionElement(Operator operator, Term... terms) {
    this.terms.addAll(Arrays.asList(terms));
    this.operator = operator;
  }

  public List<Term> getTerm() {
    return terms;
  }

  public Operator getOperator() {
    return operator;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append(operator);
    terms.forEach(fe -> builder.append(fe.toString()));
    return builder.toString();
  }
}

