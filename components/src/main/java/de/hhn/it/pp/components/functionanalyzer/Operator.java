package de.hhn.it.pp.components.functionanalyzer;

import javax.crypto.spec.OAEPParameterSpec;

public enum Operator {
  ADD('+'), DIVIDE('/'), MULTIPLY('*');

  private final char symbol;

  Operator(final char symbol){
    this.symbol = symbol;
  }

  public char getSymbol() {
    return symbol;
  }

  public static Operator operatorFromSymbol(char input) {
    switch(input) {
      case('+'): return Operator.ADD;
      case('*'): return Operator.MULTIPLY;
      case('/'): return Operator.DIVIDE;
      default: return null;
    }
  }
}
