package de.hhn.it.pp.components.functionalalyzer;

/**
 * Represent a single term inside a function
 */
public class Term {
  private double value;
  private Term exponent;
  private double factor;
  private String variable;

  public Term(Term exponent, double factor, String variable){
    this.exponent = exponent;
    this.factor = factor;
    this.variable = variable;
  }

  public Term(Term exponent, double value){
    this.exponent = exponent;
    this.value = value;
  }

  public Term(double value){
    this.value = value;
  }
}


