package de.hhn.it.pp.components.functionanalyzer;

public enum Operator {
  ADD('+'), DIVIDE('/'), MULTIPLY('*');

  private final char symbol;

  Operator(final char symbol) {
    this.symbol = symbol;
  }

  public char getSymbol() {
    return symbol;
  }

  /**
   * returns the operator corresponding to the entered symbol.
   * @param input symbol of the desired operator +, *, / are recognized symbols
   * @return operator corresponding to the entered symbol, {@code null} if no match
   */
  public static Operator operatorFromSymbol(char input) {
    switch (input) {
      case('+'): return Operator.ADD;
      case('*'): return Operator.MULTIPLY;
      case('/'): return Operator.DIVIDE;
      default: return null;
    }
  }
}
