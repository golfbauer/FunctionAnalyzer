package de.hhn.it.pp.components.functionanalyzer;

/**
 * Represent a single term inside a function
 */
public class Term {
  public static final Term E = new Term(Math.E);
  public static final Term PI = new Term(Math.PI);

  private double value;
  private Term exponent;
  private double factor;
  private String variable;

  /**
   * For Terms with variable in Base
   * @param exponent Term that represents exponent
   * @param factor Factor of the variable
   * @param variable name of the variable eg. x,y...
   */
  public Term(Term exponent, double factor, String variable) {
    this.exponent = exponent;
    this.factor = factor;
    this.variable = variable;
  }

  /**
   * For constants raised to a variable
   * @param exponent Term that represents exponent
   * @param value Base value
   */
  public Term(Term exponent, double value) {
    this.exponent = exponent;
    this.value = value;
  }

  /**
   * For constants without unknown variable
   * @param value Value of constant
   */
  public Term(double value) {
    this.value = value;
  }

  /**
   * Multiplies the Term with another Term
   */
  public void multiplyBy(Term multiplier) throws IllegalArgumentException {

  }

  /**
   * Adds another Term to the Term
   */
  public void add(Term addend) throws IllegalArgumentException {

  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }

  public Term getExponent() {
    return exponent;
  }

  public void setExponent(Term exponent) {
    this.exponent = exponent;
  }

  public double getFactor() {
    return factor;
  }

  public void setFactor(double factor) {
    this.factor = factor;
  }

  public String getVariable() {
    return variable;
  }

  public void setVariable(String variable) {
    this.variable = variable;
  }
}


