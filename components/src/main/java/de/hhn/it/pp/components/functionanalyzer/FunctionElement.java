package de.hhn.it.pp.components.functionanalyzer;

import de.hhn.it.pp.components.functionanalyzer.exceptions.ValueNotDefinedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;



public class FunctionElement implements FunctionElementComponent {

  private List<FunctionElementComponent> components = new ArrayList<>();
  private Operator operator;
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(FunctionElement.class);

  /**
   * Combines both the Term and the operator it belongs to.
   *
   * @param components = single term
   * @param operator   = its operator
   */
  public FunctionElement(Operator operator, FunctionElementComponent... components) {
    logger.debug("Creating a FunctionElement");
    this.components.addAll(Arrays.asList(components));
    this.operator = operator;
  }

  /**
   * Combines both the Term and the operator it belongs to.
   *
   * @param components = single term
   * @param operator   = its operator
   */
  public FunctionElement(Operator operator, List<FunctionElement> components) {
    logger.debug("Creating a FunctionElement with existing List of components");
    this.components.addAll(components);
    this.operator = operator;
  }

  /**
   * Creates FunctionElement without being forced to enter a Term.
   *
   * @param operator = its operator
   */
  public FunctionElement(Operator operator) {
    logger.debug("Creating a FunctionElement without a List of components");
    this.operator = operator;
  }

  /**
   * Sums all FunctionElements in a List of FunctionElements.
   *
   * @param functionElements List of FunctionElements that are to be summed
   * @return FunctionElement that contains the sum of all Elements that were entered with the
   *         Operator Operator.ADD
   */
  private static FunctionElement sum(List<FunctionElement> functionElements) {
    logger.debug("Summing: " + functionElements.toString());
    List<FunctionElement> sums = new ArrayList<>();
    sums.add(new FunctionElement(Operator.ADD, new Term(0)));
    for (FunctionElement functionElement :
        functionElements) {
      try {
        for (int i = 0; i < sums.size(); i++) {
          FunctionElement sum = sums.get(i);
          try {
            FunctionElement replacement = new FunctionElement(Operator.ADD,
                ((Term) sum.components.get(0)).add(((Term) functionElement.components.get(0))));
            int replacementIndex = sums.indexOf(sum);
            sums.remove(sum);
            sums.add(replacementIndex, replacement);
            break;

          } catch (ValueNotDefinedException e) {
            if (sum == sums.get(sums.size() - 1)) {
              throw e;
            }
          }

        }
      } catch (ValueNotDefinedException ignored) {
        sums.add(new FunctionElement(Operator.ADD, functionElement.components.get(0)));
      }
    }
    return new FunctionElement(Operator.ADD, sums);
  }

  /**
   * Multiplies two Function Elements.
   *
   * @param that The FunctionElement that the current element will be multiplied by
   * @return FunctionElement containing the result of the multiplication with the Operator
   *         from the object this method is being called on
   */
  private FunctionElement multiply(FunctionElement that) throws ValueNotDefinedException {
    logger.debug("Multiplying " + this.toString() + " with " + that.toString());
    FunctionElement result = new FunctionElement(this.operator);
    if (that.isBracket()) {
      that.resolveBrackets();
    }
    for (FunctionElementComponent component : components) {
      for (FunctionElementComponent thatComponent : that.components) {

        Term factor1 = component instanceof Term ? (Term) component :
            (Term) ((FunctionElement) component).components.get(0);
        Term factor2 = thatComponent instanceof Term ? (Term) thatComponent :
            (Term) ((FunctionElement) thatComponent).components.get(0);


        FunctionElement product = new FunctionElement(Operator.ADD, factor1.multiply(factor2));
        result.addFunctionElementComponent(product);
      }
    }
    List<FunctionElement> values = new ArrayList<>();
    for (FunctionElementComponent component : result.components) {
      if (component instanceof FunctionElement) {
        values.add(((FunctionElement) component));
      }
    }
    FunctionElement replacement = sum(values);
    result.components = replacement.components;
    result.removeBrackets();
    return result;
  }

  /**
   * Divides two Function Elements.
   *
   * @param that The FunctionElement that the current element will be divided by
   * @return FunctionElement containing the result of the division with the Operator
   *         from the object this method is being called on.
   */
  private FunctionElement divide(FunctionElement that) throws ValueNotDefinedException {
    logger.debug("Dividing " + this.toString() + " from " + that.toString());
    if (this.equals(that)) {
      return new FunctionElement(this.operator, new Term(1));
    }
    FunctionElement result = new FunctionElement(this.operator);
    if (that.isBracket()) {
      that.resolveBrackets();
    }
    for (FunctionElementComponent component : components) {
      for (FunctionElementComponent thatComponent : that.components) {

        Term factor1 = component instanceof Term ? (Term) component :
            (Term) ((FunctionElement) component).components.get(0);
        Term factor2 = thatComponent instanceof Term ? (Term) thatComponent :
            (Term) ((FunctionElement) thatComponent).components.get(0);


        FunctionElement product = new FunctionElement(Operator.ADD, factor1.divide(factor2));
        result.addFunctionElementComponent(product);
      }
    }
    List<FunctionElement> values = new ArrayList<>();
    for (FunctionElementComponent component : result.components) {
      if (component instanceof FunctionElement) {
        values.add(((FunctionElement) component));
      }
    }
    FunctionElement replacement = sum(values);
    result.components = replacement.components;
    result.removeBrackets();
    return result;
  }

  public List<FunctionElementComponent> getComponents() {
    return components;
  }

  public void addFunctionElementComponent(FunctionElementComponent functionElementComponent) {
    components.add(functionElementComponent);
  }

  public Operator getOperator() {
    return operator;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append(operator.getSymbol());
    components.forEach(fe -> builder.append(fe.toString()));

    return builder.toString();
  }

  /**
   * Attempts to simplify a FunctionElement.
   * A FunctionElement is considered simple if it meets the following conditions:
   * {@code if} The FunctionElement only contains a single Term, that Term is not nested in other
   * FunctionElements
   * {@code if} The FunctionElement contains more than one Term:
   * - all FunctionElements are contained directly in the components of the highest
   * level FunctionElement(no nested FunctionElements)
   * - there are only FunctionElements with the Operator {@link Operator#ADD}
   * - all Terms of the same degree are summed into a single Term
   * - the Terms are sorted by the highest degree descending
   */
  @Override
  public void simplify() throws ValueNotDefinedException {
    logger.debug("Simplifying " + this.toString());
    if (isBracket()) {
      List<FunctionElement> simplificationCandidates = new ArrayList<>();
      for (FunctionElementComponent component : components) {
        if (component instanceof FunctionElement
            && ((FunctionElement) component).isBracket()) { //nested Bracket -> recursion
          component.simplify();
        } else { // Function elment with only 1 Term
          simplificationCandidates.add(((FunctionElement) component));
        }
      }
      for (int i = 1; i < simplificationCandidates.size();
           i++) { // remove multiplication and division
        FunctionElement previous = simplificationCandidates.get(i - 1);
        FunctionElement candidate = simplificationCandidates.get(i);
        int replacementIndex = components.indexOf(previous);
        FunctionElement replacement;

        switch (candidate.getOperator()) {
          case MULTIPLY:
            replacement = new FunctionElement(previous.getOperator(),
                previous.operator == Operator.MULTIPLY || previous.operator == Operator.ADD
                    ? ((Term) candidate.components.get(0)).multiply(
                    ((Term) previous.components.get(0))) :
                    ((Term) candidate.components.get(0)).divide(
                        ((Term) previous.components.get(0))));
            break;
          case DIVIDE:
            replacement = new FunctionElement(previous.getOperator(),
                previous.operator == Operator.MULTIPLY || previous.operator == Operator.ADD
                    ? ((Term) previous.components.get(0)).divide(
                    ((Term) candidate.components.get(0))) :
                    ((Term) candidate.components.get(0)).multiply(
                        ((Term) previous.components.get(0))));
            break;

          case ADD:
            continue;

          default:
            throw new IllegalStateException("Unexpected value: " + candidate.getOperator());
        }

        components.add(replacementIndex, replacement);
        components.remove(previous);
        components.remove(candidate);
        simplificationCandidates.remove(candidate);
        removeBrackets();
      }

      removeBrackets();
      resolveBrackets();
      simplificationCandidates.clear();
      if (components.size() > 1) {
        for (FunctionElementComponent component : components) {
          simplificationCandidates.add(((FunctionElement) component));
        }
      }
      if (simplificationCandidates.size() > 1) {
        FunctionElement replacement = sum(simplificationCandidates);
        this.components = replacement.components;
      }
      removeBrackets();
      sortByHighestExponent();
    }
  }

  /**
   * Checks if a FunctionElement is a Bracket (contains one or more FunctionElements).
   * @return {@code true} if the FunctionElement contains other FunctionElements
   *         {@code false} if it only contains a Term
   */
  private boolean isBracket() {
    logger.debug("Checks if " + this.toString() + " is a bracket");
    if (components.size() < 2 && components.get(0) instanceof Term) {
      return false;
    }
    if (components.size() > 1) {
      return true;
    }
    return components.get(0) instanceof FunctionElement;
  }


  /**
   * Resolves bracket inside a FunctionElement.
   */
  public void resolveBrackets() throws ValueNotDefinedException {
    logger.debug("Resolving internal brackets for " + this.toString());
    if (components.size() < 2) {
      removeBrackets();
      return;
    }

    for (int i = 0; i < components.size(); i++) {
      FunctionElement component = (FunctionElement) components.get(i);
      boolean isLast = i == components.size() - 1;
      boolean isFirst = i == 0;
      FunctionElement next = isLast ? null : (FunctionElement) components.get(i + 1);
      FunctionElement prev = isFirst ? null : (FunctionElement) components.get(i - 1);

      if (component.isBracket()) {
        component.resolveBrackets();
        FunctionElement replacement;

        FunctionElement linked;
        if (isFirst) {
          linked = next;
        } else if (isLast) {
          linked = prev;
        } else {
          if (component.operator == Operator.ADD && next.operator == Operator.MULTIPLY
                  || next.operator == Operator.DIVIDE) {
            linked = next;
          } else if (next.operator == Operator.ADD && component.operator == Operator.MULTIPLY
                  || component.operator == Operator.DIVIDE) {
            linked = prev;
          } else if (component.operator == Operator.ADD && next.operator == Operator.ADD) {
            linked = next;
          } else {
            throw new IllegalStateException();
          }
        }
        Operator linkingOperator = linked == prev ? component.operator : linked.operator;

        switch (linkingOperator) {
          case DIVIDE:
            if (linked.equals(prev)) {
              replacement =
                  new FunctionElement(component.operator, linked.divide(component));
            } else {
              replacement =
                  new FunctionElement(component.operator, component.divide(linked));
            }
            replacement.removeBrackets();
            components.add(i, replacement);
            components.remove(component);
            components.remove(linked);
            break;
          case MULTIPLY:
            if (linked.equals(prev)) {
              replacement =
                  new FunctionElement(prev.operator, linked.multiply(component));
            } else {
              replacement =
                  new FunctionElement(component.operator, component.multiply(linked));
            }
            replacement.removeBrackets();
            for (FunctionElementComponent replacementComponent : replacement.components) {
              components.add(i, replacementComponent);
            }
            components.remove(component);
            components.remove(linked);
            break;
          case ADD:
            for (FunctionElementComponent innerComponent : component.components) {
              components.add(i, innerComponent);
            }
            components.remove(component);
            break;
          default:
            throw new IllegalStateException("Unexpected value: " + next.operator);
        }
      }

    }
    removeBrackets();
    List<FunctionElement> values = new ArrayList<>();
    for (FunctionElementComponent component : components) {
      values.add(((FunctionElement) component));
    }
    FunctionElement replacement = sum(values);
    components = replacement.components;
    removeBrackets();

  }


  /**
   * Removes unnecessary Brackets from a FunctionElement.
   */
  private void removeBrackets() {
    logger.debug("Removing unnecessary brackets from " + this.toString());
    if (isBracket()) {
      if (components.get(0) instanceof FunctionElement) {
        ((FunctionElement) components.get(0)).removeBrackets();
      }
      if (components.size() > 1) {
        for (FunctionElementComponent component : components) {
          if (((FunctionElement) component).isBracket()) {
            ((FunctionElement) component).removeBrackets();
          }
        }
      } else {
        components = ((FunctionElement) components.get(0)).components;
      }


    }
  }

  @Override
  public FunctionElement getDerivative() {
    logger.debug("Calculating derivative for " + this.toString());
    FunctionElement result = new FunctionElement(operator);
    for (int i = 0; i < components.size(); i++) {
      if (components.get(i) instanceof FunctionElement) {
        result.addFunctionElementComponent(components.get(i).getDerivative());
      } else if (components.get(i) instanceof Term) {
        if (components.get(i).getDerivative() != null) {
          result.addFunctionElementComponent(components.get(i).getDerivative());
        } else if (components.size() == 1) {
          return null;
        }
      }
    }
    for (int i = 0; i < result.components.size(); i++) {
      if (result.components.get(i) == null) {
        result.components.remove(i);
      }
    }
    return result;
  }

  /**
   * Gets the highest exponent as double in this FunctionElement.
   *
   * @return exponent
   */
  public double getMaxExponent() {
    logger.debug("Getting the max Exponent in " + this.toString());
    double result = 0;
    for (int i = 0; i < components.size(); i++) {
      if (components.get(i) instanceof FunctionElement) {
        double temp = ((FunctionElement) components.get(i)).getMaxExponent();
        if (result < temp) {
          result = temp;
        }
      } else if (components.get(i) instanceof Term) {
        if (((Term) components.get(i)).getExponent() != null) {
          double temp = ((Term) components.get(i)).getExponent().getValue();
          if (result < temp) {
            result = temp;
          }
        }
      }
    }
    return result;
  }

  /**
   * Sorts FunctionElements inside a FunctionElement by their highest exponent in descending order.
   */
  private void sortByHighestExponent() {
    logger.debug("Sorting " + this.toString() + " by Exponent size");
    if (components.size() > 1) {
      final List<FunctionElement> componentList = new ArrayList<>();
      FunctionElement constant = null;
      for (FunctionElementComponent component : components) {
        if (((FunctionElement) component).components.get(0) instanceof Term
            && ((Term) ((FunctionElement) component).components.get(0)).getVariable() == null) {
          constant = (FunctionElement) component;
        }
      }
      components.stream().filter(component -> ((Term) ((FunctionElement) component).components
          .get(0)).getExponent() != null).forEach(functionElementComponent ->
          componentList.add((FunctionElement) functionElementComponent));
      Comparator<FunctionElement> expComp = Comparator.comparing(functionElement ->
          ((Term) functionElement.components.get(0)).getExponent().getValue());
      expComp = expComp.reversed();
      List<FunctionElement> sortedList =
          componentList.stream().sorted(expComp).collect(Collectors.toList());
      for (FunctionElement element : sortedList) {
        components.remove(element);
        components.add(components.size() - 1, element);
      }
      if (constant != null) {
        components.remove(constant);
        components.add(components.size(), constant);
      }
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FunctionElement that = (FunctionElement) o;
    return components.equals(that.components)
        && operator == that.operator;
  }

  @Override
  public int hashCode() {
    return Objects.hash(components, operator);
  }

  /**
   * Calculates value for the entire FunctionElement with specific x.
   *
   * @param x to be put into variable x
   * @return result of FunctionElement
   */
  public double calcFunctionElementValue(double x) {
    logger.debug("Calculaing the Value of " + this.toString() + " for " + x);
    double result = 0;
    List<FunctionElementComponent> temp = this.getComponents();
    for (FunctionElementComponent functionElementComponent : temp) {
      if (temp.get(0) instanceof FunctionElement) {
        result += ((FunctionElement) functionElementComponent).calcFunctionElementValue(x);
      } else if (temp.get(0) instanceof Term) {
        result += ((Term) functionElementComponent).calcTermValue(x);
      }
    }
    return result;
  }
}

