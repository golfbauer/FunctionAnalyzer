package de.hhn.it.pp.components.functionanalyzer;

import de.hhn.it.pp.components.functionanalyzer.exceptions.ValueNotDefinedException;
import java.util.Objects;


/**
 * Represent a single term inside a function.
 */
public class Term implements FunctionElementComponent {
  public static final Term E = new Term(Math.E);
  public static final Term PI = new Term(Math.PI);

  private double value;
  private Term exponent;
  private double factor;
  private String variable;

  /**
   * For Terms with variable in Base.
   *
   * @param exponent Term that represents exponent
   * @param factor   Factor of the variable
   * @param variable name of the variable eg. x,y...
   */
  public Term(Term exponent, double factor, String variable) {
    this.exponent = exponent;
    this.factor = factor;
    this.variable = variable;
  }

  /**
   * For constants raised to a variable.
   *
   * @param exponent Term that represents exponent
   * @param value    Base value
   */
  public Term(Term exponent, double value) {
    this.exponent = exponent;
    this.value = value;
  }

  /**
   * For constants without unknown variable.
   *
   * @param value Value of constant
   */
  public Term(double value) {
    this.value = value;
  }

  /**
   * Calculates the result of adding another Term to this Term.
   *
   * @param that the term that will be added
   * @return result of the addition
   * @throws ValueNotDefinedException if the Terms are not equal
   *         according to {@link #structurallyEqual}
   */
  public Term add(Term that) throws ValueNotDefinedException {
    if (!this.structurallyEqual(that)) {
      throw new ValueNotDefinedException("Cannot add Terms with different structure");
    }
    if (this.variable != null) {
      return new Term(this.exponent, this.factor + that.factor, this.variable);
    } else {
      return new Term(this.value + that.value);
    }
  }

  /**
   * Calculates the result of subtracting another Term from this Term.
   *
   * @param that the term that will be added
   * @return result of the addition
   * @throws ValueNotDefinedException if the Terms are not equal
   *         according to {@link #structurallyEqual}
   */
  public Term subtract(Term that) throws ValueNotDefinedException {
    return this.add(that.multiply(new Term(-1)));
  }

  /**
   * Calculates the result of multiplying this Term with another Term.
   *
   * @param that the Term that will be multiplied by
   * @return result of the multiplication
   * @throws ValueNotDefinedException if the Terms do not have the same variable
   */
  public Term multiply(Term that) throws ValueNotDefinedException {
    if (!Objects.equals(this.variable, that.variable)
        && (this.variable != null) == (that.variable != null)) {
      throw new ValueNotDefinedException("Cannot multiply Term with different variables");
    }
    if ((this.variable == null) && (that.variable == null)) {
      return new Term(this.value * that.value);
    } else if (this.variable == null) {
      return new Term(that.exponent, this.value * that.factor, that.variable);
    } else if (that.variable == null) {
      return new Term(this.exponent, this.factor * that.value, this.variable);
    } else {
      return new Term(this.exponent.add(that.exponent),
          this.factor * that.factor, this.variable);
    }
  }

  @Override
  public void simplify() {
    //todo
  }

  @Override
  public double evaluate(double variableValue) {
    return 0;
  }

  /**
   * Calculate the Derivative of Term.
   *
   * @return Derivative of the Term
   */
  public Term getDerivative() {
    return null;
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

  @Override
  public String toString() {
    if (variable != null) {
      return "" + factor + variable + "^" + exponent.toString();
    } else if (exponent != null) {
      return "" + value + "^" + exponent.toString();
    }
    return "" + value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Term term = (Term) o;
    return Double.compare(term.value, value) == 0
        && Double.compare(term.factor, factor) == 0
        && Objects.equals(exponent, term.exponent)
        && Objects.equals(variable, term.variable);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, exponent, factor, variable);
  }

  /**
   * Checks if two Terms are equal in structure, meaning they have the same variable and exponent.
   *
   * @param that the Term to compare against
   * @return {@code true} if both variable and exponents are equal {@code false} if not
   */
  public boolean structurallyEqual(Term that) {
    if (this.variable != null && that.variable != null) {
      return this.variable.equals(that.variable) && this.exponent.equals(that.exponent);
    } else if (this.variable == null && that.variable == null) {
      return this.exponent == that.exponent;
    } else {
      return false;
    }
  }
}


