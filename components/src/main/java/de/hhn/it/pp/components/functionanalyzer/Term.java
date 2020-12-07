package de.hhn.it.pp.components.functionanalyzer;

import java.awt.event.TextEvent;
import java.util.Objects;
import de.hhn.it.pp.components.functionanalyzer.exceptions.ValueNotDefinedException;
import de.hhn.it.pp.components.functionanalyzer.provider.FunctionAnalyzer;


/**
 * Represent a single term inside a function.
 */
public class Term implements FunctionElementComponent {

  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(Term.class);


  private double value;
  public static final  Term ZERO = new Term(0);
  public static final Term E = new Term(Math.E);
  public static final Term PI = new Term(Math.PI);
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
   *                                  according to {@link #structurallyEqual}
   */
  public Term add(Term that) throws ValueNotDefinedException {
    logger.debug("Adding " + this.toString() + " with " + that.toString());
    if (!this.structurallyEqual(that) && !this.equals(Term.ZERO) && !that.equals(Term.ZERO)) {
      throw new ValueNotDefinedException("Cannot add Terms with different structure");
    }
    if (this.value == 0 && this.variable == null
        || this.variable != null && this.factor == 0 && !this.exponent.equals(Term.ZERO)) {
      return that;
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
   *                                  according to {@link #structurallyEqual}
   */
  public Term subtract(Term that) throws ValueNotDefinedException {
    logger.debug("Subtracting " + this.toString() + " from " + that.toString());
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
    logger.debug("Multiplying " + this.toString() + " with " + that.toString());
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

  /**
   * Calculates the result of dividing this Term with another Term.
   *
   * @param that the Term that will be divided by
   * @return result of the division
   * @throws ValueNotDefinedException if the Terms do not have the same variable
   */
  public Term divide(Term that) throws ValueNotDefinedException {
    logger.debug("Dividing " + this.toString() + " from " + that.toString());
    if (this.variable == null && that.variable == null) {
      return new Term(this.value / that.value);
    } else if (this.variable != null && that.variable != null) {
      double fac = this.factor / that.factor;
      Term exp = this.exponent.subtract(that.exponent);
      if (exp.value == 0) {
        return new Term(fac);
      } else {
        return new Term(exp, fac, this.variable);
      }
    } else if (this.variable != null) {
      return new Term(this.exponent, this.factor / that.value, this.variable);
    } else {
      return new Term(that.exponent.multiply(new Term(-1)),
          this.value / that.factor, that.variable);
    }
  }

  /**
   * Creates a copy of a Term.
   *
   * @return a copy of the Term
   */
  public Term copy() {
    logger.debug("Copying " + this.toString());
    if (this.exponent != null) {
      return new Term(this.exponent.copy(), this.factor, this.variable);
    } else if (this.variable != null) {
      return new Term(null, this.factor, this.variable);
    } else {
      return new Term(this.value);
    }
  }

  @Override
  public void simplify() {
    logger.debug("Simplifying + " + this.toString());
    if (exponent != null) {
      exponent.simplify();
    }
    if (variable != null) {
      return;
    }
    if (!exponentHasVariable() && exponent != null) {
      this.value = Math.pow(this.value, this.exponent.value);
      this.exponent = null;
    }
  }

  private boolean exponentHasVariable() {
    logger.debug("Checking if " + this.toString() + " has Exponent");
    boolean variableInExponent = false;
    Term temp = this.copy();
    while (temp.exponent != null) {
      temp = temp.exponent;
      if (temp.variable != null) {
        variableInExponent = true;
        break;
      }
    }
    return variableInExponent;
  }

  /**
   * Calculate the Derivative of Term.
   * @return Derivative of the Term
   */
  @Override
  public Term getDerivative() {
    logger.debug("Calculating derivative for " + this.toString());
    if (variable == null) {
      return null;
    } else {
      if (exponent.value <= 1) {
        return new Term(factor);
      } else {
        return new Term(new Term(exponent.value - 1), exponent.value * factor, variable);
      }
    }
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
    if (variable != null && exponent != null) {
      return "" + factor + variable + "^" + exponent.toString();
    } else if (exponent != null) {
      return "" + value + "^" + exponent.toString();
    } else if (variable != null) {
      return "" + factor + variable;
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
    logger.debug("Checking if " + this.toString() + " and "
            + that.toString() + " are structurally equal");
    if (this.variable != null && that.variable != null) {
      return this.variable.equals(that.variable) && this.exponent.equals(that.exponent);
    } else if (this.variable == null && that.variable == null) {
      return this.exponent == that.exponent;
    } else {
      return false;
    }
  }

  /**
   * Returns the value of a Term for certain x Value.
   * @param x Value to be put into x variable
   * @return Result of the Term
   */
  public double calcTermValue(double x) {
    logger.debug("Calculaing the Value of " + this.toString() + " for " + x);
    if (this.getVariable() == null) {
      return this.getValue();
    } else {
      if (this.getFactor() * Math.pow(
          x, this.getExponent().getValue()) == Double.POSITIVE_INFINITY) {
        try {
          throw new Exception();
        } catch (Exception exception) {
          System.out.println("Y-Achse wird nie geschnitten!");
        }
      }
      return this.getFactor() * Math.pow(x, this.getExponent().getValue());
    }
  }
}


