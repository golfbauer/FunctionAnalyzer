package de.hhn.it.pp.components.functionanalyzer;

import de.hhn.it.pp.components.functionanalyzer.exceptions.ValueNotDefinedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Represent a function as a combination of Terms.
 */
public class Function extends ArrayList<FunctionElement> {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(Function.class);

  /**
   * Add FunctionElements into the function.
   * @param elements Contains all FunctionElements
   */
  public Function(FunctionElement... elements) {
    logger.debug("Creating a Function");
    addAll(Arrays.asList(elements));
  }

  /**
   * Calculate the Derivative of Function.
   * @return Derivative of the Function
   */
  public Function getDerivative() {
    logger.debug("Getting the derivative for " + this.toString());
    Function derivative = new Function();
    for (int i = 0; i < this.size(); i++) {
      if (this.get(i).getDerivative() != null) {
        derivative.add(this.get(i).getDerivative());
      }
    }
    return derivative;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("f(x) = ");
    this.forEach(fe -> builder.append(fe.toString()));
    return builder.toString();

  }

  /**
   * Sets given Function equal to zero.
   * @return List of x values
   */
  public List<Double> setFunctionEqualZero() {
    logger.debug("Setting " + this.toString() + " = 0");
    if (this.size() == 0) {
      return null;
    }
    double maxExponent = 0;
    ArrayList<Double> result = new ArrayList<>();
    for (FunctionElement functionElement : this) {
      if (maxExponent < functionElement.getMaxExponent()) {
        maxExponent = functionElement.getMaxExponent();
      }
    }
    if (maxExponent == 0) {
      if (((Term) this.get(0).getComponents().get(0)).getValue() == 0) {
        result.add(0.0);
        return result;
      }
      return null;
    } else if (maxExponent == 1) {
      result.add(setLinearFunctionEqualZero());
      return result;
    } else if (maxExponent == 2) {
      result = setSquareFunctionEqualZero();
      return result;
    }
    return new ArrayList<>();
  }

  /**
   * Sets a linear Function equal to zero.
   * @return single x value
   */
  public double setLinearFunctionEqualZero() {
    logger.debug("Setting the linear Function: " + this.toString() + " = 0");
    double result;
    if (this.size() == 2) {
      result = ((Term) this.get(1).getComponents().get(0)).getValue()
              / ((Term) this.get(0).getComponents().get(0)).getFactor();
      result = result * (-1);
      return result;
    } else {
      return 0;
    }
  }

  /**
   * Sets quadratic Function equal to zero.
   * @return List of x values, two at max
   */
  public ArrayList<Double> setSquareFunctionEqualZero() {
    logger.debug("Setting the square Function: " + this.toString() + " = 0");
    ArrayList<Double> result = new ArrayList<>();
    double p;
    double q;
    if (this.size() == 3) {
      p = ((Term) this.get(1).getComponents().get(0)).getFactor()
              / ((Term) this.get(0).getComponents().get(0)).getFactor();
      q = ((Term) this.get(2).getComponents().get(0)).getValue()
              / ((Term) this.get(0).getComponents().get(0)).getFactor();
      return pqFormal(p, q);
    } else if (this.size() == 1) {
      result.add(0.0);
      return result;
    } else if (((Term) this.get(1).getComponents().get(0)).getExponent() != null) {
      p = ((Term) this.get(1).getComponents().get(0)).getFactor()
              / ((Term) this.get(0).getComponents().get(0)).getFactor();
      q = 0;
      return pqFormal(p, q);
    } else {
      q = ((Term) this.get(1).getComponents().get(0)).getValue()
              / ((Term) this.get(0).getComponents().get(0)).getFactor();
      if (q > 0) {
        return null;
      } else {
        q = q * (-1);
        q = Math.sqrt(q);
        result.add(q);
        result.add(q * -1);
        return result;
      }
    }
  }


  /**
   * Uses the PQFormal to calculate x values for square Function.
   * @param p p in PQFormal
   * @param q q in PQFormal
   * @return List of x values
   */
  public ArrayList<Double> pqFormal(double p, double q) {
    logger.debug("Using the PQFormal on " + this.toString());
    ArrayList<Double> result = new ArrayList<>();
    double discriminant;
    discriminant = (p / 2) * (p / 2) - q;
    if (discriminant >= 0) {
      double x1;
      double x2;
      x1 = -(p / 2) + Math.sqrt(discriminant);
      x2 = -(p / 2) - Math.sqrt(discriminant);
      if (x1 == x2) {
        result.add(x1);
        return result;
      } else {
        result.add(x1);
        result.add(x2);
        return result;
      }
    } else {
      return null;
    }
  }

  /**
   * Simplifies a Function by converting it to a FunctionElement
   * and simplifying that FunctionElement.
   */
  public void simplify() throws ValueNotDefinedException {
    logger.debug("Simplifying " + this.toString());
    if (size() < 2) {
      return;
    }
    FunctionElement functionAsFE = new FunctionElement(Operator.ADD, this);
    functionAsFE.simplify();
    clear();

    if (functionAsFE.getComponents().get(0) instanceof Term) {
      add(functionAsFE);
    } else {
      functionAsFE.getComponents().forEach(functionElementComponent -> add(
          (FunctionElement) functionElementComponent));
    }
  }

  /**
   * Calculates Function value for specific x value.
   * @param x Value to replace x variable
   * @return One value
   */
  public double calcFunctionValue(double x) {
    logger.debug("Calculating the Value for " + this.toString() + " with X = " + x);
    double result = this.get(0).calcFunctionElementValue(x);
    for (int i = 1; i < this.size(); i++) {
      if (this.get(i).getOperator().getSymbol() == '*'
          || this.get(i).getOperator().getSymbol() == '/') {
        throw new IllegalStateException();
      } else if (this.get(i).getOperator().getSymbol() == '+') {
        result += this.get(i).calcFunctionElementValue(x);
      } else {
        result -= this.get(i).calcFunctionElementValue(x);
      }
    }
    return result;
  }
}
