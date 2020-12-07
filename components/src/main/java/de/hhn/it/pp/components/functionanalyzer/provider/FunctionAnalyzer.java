package de.hhn.it.pp.components.functionanalyzer.provider;

import de.hhn.it.pp.components.functionanalyzer.Function;
import de.hhn.it.pp.components.functionanalyzer.FunctionAnalyserService;
import de.hhn.it.pp.components.functionanalyzer.FunctionElement;
import de.hhn.it.pp.components.functionanalyzer.Operator;
import de.hhn.it.pp.components.functionanalyzer.Term;
import de.hhn.it.pp.components.functionanalyzer.exceptions.ValueNotDefinedException;
import java.util.ArrayList;
import java.util.List;

public class FunctionAnalyzer implements FunctionAnalyserService {

  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(FunctionAnalyzer.class);

  @Override
  public Function readFunction(String input) throws IllegalArgumentException {
    logger.debug("Translating input: " + input + " into a Function");
    Function result = new Function();
    input = input.replaceAll("\\s", "");
    ArrayList<String> functionString = getFunctionElementAsString(input, '+');
    ArrayList<String> temporary = new ArrayList<>();
    for (int i = 0; i < functionString.size(); i++) {
      if (!functionString.get(i).equals("*") && !functionString.get(i).equals("/")
          && !functionString.get(i).equals("+") && !functionString.get(i).equals("-")
          && !functionString.get(i).equals("")) {
        if (functionString.get(i).equals("(")) {
          int j;
          int check = 1;
          for (j = i + 1; check > 0; j++) {
            if (functionString.get(j).equals("(")) {
              check++;
            } else if (functionString.get(j + 1).equals(")")) {
              check--;
            }
            temporary.add(functionString.get(j));
            if (check == 0) {
              result.add(getFunctionElementBracket(temporary, functionString.get(i - 1).charAt(0)));
            }
          }
          i = j;
        } else {
          result.add(new FunctionElement(Operator.operatorFromSymbol(
              functionString.get(0).charAt(0)), getTermFromString(functionString.get(i))));
        }
      }
    }
    return result;
  }

  @Override
  public List<Double> calculateMinima(Function f) throws ValueNotDefinedException {
    logger.debug("Calculating the minima for function " + f.toString());
    List<Double> result = new ArrayList<>();
    List<Double> temp;
    temp = f.getDerivative().setFunctionEqualZero();
    if (temp == null) {
      return null;
    }
    Function secondDerivative = f.getDerivative().getDerivative();
    for (int i = 0; i < temp.size(); i++) {
      if (secondDerivative.calcFunctionValue(temp.get(i)) > 0) {
        result.add(f.calcFunctionValue(temp.get(i)));
      }
    }
    return result;
  }

  @Override
  public List<Double> calculateMaxima(Function f) throws ValueNotDefinedException {
    logger.debug("Calculating the maxima for function " + f.toString());
    List<Double> result = new ArrayList<>();
    List<Double> temp;
    temp = f.getDerivative().setFunctionEqualZero();
    if (temp == null) {
      return null;
    }
    Function secondDerivative = f.getDerivative().getDerivative();
    for (int i = 0; i < temp.size(); i++) {
      if (secondDerivative.calcFunctionValue(temp.get(i)) < 0) {
        result.add(f.calcFunctionValue(temp.get(i)));
      }
    }
    return result;
  }

  @Override
  public List<Double> calculateXIntersection(Function f) throws ValueNotDefinedException {
    logger.debug("Calculating the intersection with X Axis for function: " + f.toString());
    return f.setFunctionEqualZero();
  }

  @Override
  public double calculateYIntersection(Function f) throws ValueNotDefinedException {
    logger.debug("Calculating the intersection with Y Axis for function: " + f.toString());
    return f.calcFunctionValue(0);
  }

  @Override
  public double calculateFunctionValue(Function f, double functionParameter)
      throws ValueNotDefinedException {
    logger.debug("Calculating the Y Value for a specific X Value for Function: " + f.toString());
    return f.calcFunctionValue(functionParameter);
  }

  @Override
  public List<Double> calculatePointIntersection(Function f, double functionValue)
      throws ValueNotDefinedException {
    logger.debug("Calculating the X Value for a specific Y Value for Function: " + f.toString());
    List<Double> result;
    FunctionElement functionElement = f.get(f.size() - 1);
    Term smallestValue = (Term) functionElement.getComponents()
        .get(functionElement.getComponents().size() - 1);
    if (smallestValue.getExponent() == null) {
      smallestValue.setValue(smallestValue.getValue() + (-1) * functionValue);
      f.remove(f.size() - 1);
      f.add(new FunctionElement(Operator.ADD, smallestValue));
    } else {
      f.add(new FunctionElement(Operator.ADD, new Term((-1) * functionValue)));
    }
    result = f.setFunctionEqualZero();
    if (result == null) {
      result = new ArrayList<>();
      result.add(functionValue);
    }
    return result;
  }


  /**
   * Takes in a ArrayList of Strings which are all inside a Bracket.
   * In case another Bracket is inside this Bracket the method will call itself again until it gets
   * down to the root.
   * @param input ArrayList of Terms
   * @param operator The operator which stands in front of the Bracket
   * @return Returns a single FunctionElement which contains more FunctionElements
   */
  public FunctionElement getFunctionElementBracket(ArrayList<String> input, char operator) {
    logger.debug("Converting a bracket from String: " + input + " into a FunctionElement");
    FunctionElement functionElement = new FunctionElement(Operator.operatorFromSymbol(operator));
    ArrayList<String> temporary = new ArrayList<>();
    for (int i = 0; i < input.size(); i++) {
      if (!input.get(i).equals("*") && !input.get(i).equals("/")
          && !input.get(i).equals("+") && !input.get(i).equals("-")
          && !input.get(i).equals("")) {
        if (input.get(i).equals("(")) {
          int check = 1;
          int j;
          for (j = i + 1; check > 0; j++) {
            if (input.get(j).equals("(")) {
              check++;
            } else if (input.get(j + 1).equals(")")) {
              check--;
            }
            temporary.add(input.get(j));
            if (check == 0) {
              functionElement.addFunctionElementComponent(
                  getFunctionElementBracket(temporary, input.get(i - 1).charAt(0)));
            }
          }
          i = j + 1;
        } else {
          functionElement.addFunctionElementComponent(
              new FunctionElement(Operator.ADD, getTermFromString(input.get(i))));
        }
      }
    }
    return functionElement;
  }

  /**
   * Takes in the entire String and separates it into different FunctionElement Strings into an
   * ArrayList
   * @param input The entire Function given from the ControllerClass
   * @param operator The operator which stands in front of the first FunctionElement
   * @return A ArrayList of Strings separated into FunctionElements
   */
  public ArrayList<String> getFunctionElementAsString(String input, char operator) {
    logger.debug("Converting the String: " + input + " into a FunctionElement");
    ArrayList<String> terms = new ArrayList<>();
    ArrayList<String> temporary = new ArrayList<>();
    terms.add(String.valueOf(operator));
    String buffer = "";

    for (int i = 0; i < input.length(); i++) {
      if (i > 0 && !buffer.equals("")) {
        if ((input.charAt(i) == '+' || input.charAt(i) == '-') && (input.charAt(i - 1) != '*'
            && input.charAt(i - 1) != '/' && input.charAt(i - 1) != '^')) {
          if (buffer.charAt(0) != '+' && buffer.charAt(0) != '-' && buffer.charAt(0) != '*'
              && buffer.charAt(0) != '/') {
            buffer = "+" + buffer;
          }
          terms.add(buffer);
          buffer = "";
          if (!(temporary.isEmpty())) {
            buffer = terms.get(terms.size() - 1);
            buffer = buffer.substring(0, buffer.length() - 1);
            terms.remove(terms.size() - 1);
            terms.add(buffer);
            buffer = "";
            temporary.add(1, "(");
            terms.addAll(temporary);
            terms.add(")");
            temporary.clear();
          }
        }
      }
      if (input.charAt(i) == '(') {
        char op;
        if (i > 0) {
          op = input.charAt(i - 1);
        } else {
          op = '+';
        }
        String innerTerm = "";
        int check = 1;
        for (int j = i + 1; check > 0; j++) {
          if (input.charAt(j) == '(') {
            check++;
          } else if (input.charAt(j + 1) == ')') {
            check--;
          }
          innerTerm += input.charAt(j);
          if (check == 0) {
            input = input.substring(0, i - 1) + input.substring(j + 2);
            temporary = getFunctionElementAsString(innerTerm, op);
            i = i - 2;
            break;
          }
        }
      } else {
        buffer += input.charAt(i);
      }
      if (i == input.length() - 1 && !buffer.equals("")) {
        terms.add(buffer);
        buffer = "";

        if (!(temporary.isEmpty())) {
          buffer = terms.get(terms.size() - 1);
          buffer = buffer.substring(0, buffer.length() - 1);
          terms.remove(terms.size() - 1);
          terms.add(buffer);
          buffer = "";
          temporary.add(1, "(");
          terms.addAll(temporary);
          terms.add(")");
          temporary.clear();
        }
      }
    }
    return terms;
  }

  /**
   * Takes a single String which contains a Term and converts it into an actual Term
   * @param input To be converted String
   * @return Term out of String
   */
  public Term getTermFromString(String input) {
    logger.debug("Converting the String: " + input + " into a Term");
    String buffer = "";
    double factor = 1;
    double exponent = 1;
    String variable = null;
    ArrayList<String> nums = new ArrayList<>();
    for (int i = 0; i < input.length(); i++) {
      if (input.charAt(i) == '*' || input.charAt(i) == '/') {
        nums.add(buffer);
        nums.add(String.valueOf(input.charAt(i)));
        buffer = "";
      } else {
        buffer += input.charAt(i);
      }
      if (i == input.length() - 1 && !buffer.equals("")) {
        nums.add(buffer);
        buffer = "";
      }
    }

    for (int i = 0; i < nums.size(); i++) {
      if (nums.get(i).contains("x")) {
        variable = "x";
        if (nums.get(i).contains("^")) {
          boolean check = false;
          for (int j = 0; j < nums.get(i).length(); j++) {
            if (check) {
              buffer += nums.get(i).charAt(j);
            }
            if (nums.get(i).charAt(j) == '^') {
              check = true;
            }
          }
          if (i > 1 && nums.get(i - 1).equals("/")) {
            exponent = exponent * -1;
          }
          exponent = exponent * Double.parseDouble(buffer);
          buffer = "";
        } else if (i > 0) {
          if (nums.get(i - 1).equals("/")) {
            exponent = exponent * -1;
          }
        }
      } else if (!nums.get(i).contains("*") && !nums.get(i).contains("/")) {
        if (nums.get(i).contains("^")) {
          String buffer1 = "";
          boolean check = false;
          for (int j = 0; j < nums.get(i).length(); j++) {
            if (nums.get(i).charAt(j) == '^') {
              check = true;
              j++;
            }
            if (check) {
              buffer += nums.get(i).charAt(j);
            } else {
              buffer1 += nums.get(i).charAt(j);
            }
          }
          if (i > 0) {
            if (nums.get(i - 1).equals("*")) {
              factor = factor * Math.pow(Double.parseDouble(buffer1),
                  Double.parseDouble(buffer));
            } else {
              factor = (1) * factor / Math.pow(Double.parseDouble(buffer1),
                  Double.parseDouble(buffer));
            }
          } else {
            factor = Math.pow(Double.parseDouble(buffer1),
                Double.parseDouble(buffer));
          }
        } else {
          if (i > 0) {
            if (nums.get(i - 1).equals("*")) {
              factor = factor * Double.parseDouble(nums.get(i));
            } else {
              factor = factor / Double.parseDouble(nums.get(i));
            }
          } else {
            factor = Double.parseDouble(nums.get(i));
          }
        }
      }
    }
    if (variable == null) {
      return new Term(factor);
    }
    return new Term(new Term(exponent), factor, variable);
  }
}