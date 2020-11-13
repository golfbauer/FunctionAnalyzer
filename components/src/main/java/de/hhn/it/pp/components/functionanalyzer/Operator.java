package de.hhn.it.pp.components.functionanalyzer;

public enum Operator {
  ADD("+"), MULTIPLY("*");

  private final String symbol;

  Operator(final String symbol){
    this.symbol = symbol;
  }

  public String getSymbol() {
    return symbol;
  }
}
