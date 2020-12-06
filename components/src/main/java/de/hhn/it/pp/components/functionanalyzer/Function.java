package de.hhn.it.pp.components.functionanalyzer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import de.hhn.it.pp.components.functionanalyzer.exceptions.ValueNotDefinedException;

/**
 * Represent a function as a combination of Terms
 */
public class Function extends ArrayList<FunctionElement> {

  /**
   * Add FunctionElements into the function
   * @param elements Contains all FunctionElements
   */
  public Function(FunctionElement... elements) {
    addAll(Arrays.asList(elements));
  }

  /**
   * Calculate the Derivative of Function
   * @return Derivative of the Function
   */
  public Function getDerivative() {
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

  public List<Double> setFunctionEqualZero() {
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

  public double setLinearFunctionEqualZero() {
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

  public ArrayList<Double> setSquareFunctionEqualZero() {
    ArrayList<Double> result = new ArrayList<>();
    double p;
    double q;
    if (this.size() == 3) {
      p = ((Term) this.get(1).getComponents().get(0)).getFactor()
              / ((Term) this.get(0).getComponents().get(0)).getFactor();
      q = ((Term) this.get(2).getComponents().get(0)).getValue()
              / ((Term) this.get(0).getComponents().get(0)).getFactor();
      return pqFormel(p, q);
    } else if (((Term) this.get(1).getComponents().get(0)).getExponent() != null) {
      p = ((Term) this.get(1).getComponents().get(0)).getFactor()
              / ((Term) this.get(0).getComponents().get(0)).getFactor();
      q = 0;
      return pqFormel(p, q);
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

  public ArrayList<Double> pqFormel(double p, double q) {
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

  public void simplify() throws ValueNotDefinedException {
    if (size() < 2) {
      return;
    }
    FunctionElement functionAsFE = new FunctionElement(Operator.ADD, this);
    functionAsFE.simplify();
    clear();

    functionAsFE.getComponents().forEach(functionElementComponent -> add(
        (FunctionElement) functionElementComponent));
  }
}
