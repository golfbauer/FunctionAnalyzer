package de.hhn.it.pp.components.functionanalyzer.provider;

import java.util.ArrayList;
import java.util.List;
import de.hhn.it.pp.components.functionanalyzer.Function;
import de.hhn.it.pp.components.functionanalyzer.FunctionAnalyserService;
import de.hhn.it.pp.components.functionanalyzer.FunctionElement;
import de.hhn.it.pp.components.functionanalyzer.Operator;
import de.hhn.it.pp.components.functionanalyzer.Term;
import de.hhn.it.pp.components.functionanalyzer.exceptions.ValueNotDefinedException;

public class FunctionAnalyzer implements FunctionAnalyserService {

  @Override
  public Function readFunction(String input) throws IllegalArgumentException {
    Function result = new Function();
    input = input.replaceAll("\\s", "");
    ArrayList<String> functionString = getFEasString(input, '+');
    ArrayList<String> temporary = new ArrayList<>();
    for (int i = 0; i < functionString.size(); i++) {
      if (!functionString.get(i).equals("*") && !functionString.get(i).equals("/") &&
          !functionString.get(i).equals("+") && !functionString.get(i).equals("-") &&
              !functionString.get(i).equals("")) {
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
              result.add(getFinalFe(temporary, functionString.get(i - 1).charAt(0)));
            }
          }
          i = j;
        } else {
          result.add(new FunctionElement(Operator.operatorFromSymbol(
              functionString.get(0).charAt(0)), getTerm(functionString.get(i))));
        }
      }
    }
    return result;
  }

  public FunctionElement getFinalFe(ArrayList<String> input, char operator) {
    FunctionElement functionElement = new FunctionElement(Operator.operatorFromSymbol(operator));
    ArrayList<String> temporary = new ArrayList<>();
    for (int i = 0; i < input.size(); i++) {
      if (!input.get(i).equals("*") && !input.get(i).equals("/") &&
          !input.get(i).equals("+") && !input.get(i).equals("-") &&
          !input.get(i).equals("")) {
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
                  getFinalFe(temporary, input.get(i - 1).charAt(0)));
            }
          }
          i = j + 1;
        } else {
          functionElement.addFunctionElementComponent(getTerm(input.get(i)));
        }
      }
    }
    return functionElement;
  }

  public ArrayList<String> getFEasString(String input, char operator) {
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
            buffer = buffer.substring(0, buffer.length() - 1);    //Das ist sau unnötig und mein Fehler aber es wäre anstrengender den code umzuschreiben als das hier zu lassen sowwy uwu
            terms.remove(terms.size() - 1);                 //Das muss ich noch umschreiben für den Fall (terme)*(terme)
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
            temporary = getFEasString(innerTerm, op);
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
          buffer = buffer.substring(0, buffer.length() - 1);    //Das ist sau unnötig und mein Fehler aber es wäre anstrengender den code umzuschreiben als das hier zu lassen sowwy uwu
          terms.remove(terms.size() - 1);                 //Das muss ich noch umschreiben für den Fall (terme)*(terme)
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

  public Term getTerm(String input) {
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
          if (nums.get(i - 1).equals("/")) {
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
      return new Term(new Term(exponent), factor);
    }
    return new Term(new Term(exponent), factor, variable);
  }

  @Override
  public List<Double> calculateMinima(Function f) throws ValueNotDefinedException {
    return null;
  }

  @Override
  public List<Double> calculateMaxima(Function f) throws ValueNotDefinedException {
    return null;
  }

  @Override
  public List<Double> calculateXIntersection(Function f) throws ValueNotDefinedException {
    return null;
  }

  @Override
  public List<Double> calculateYIntersection(Function f) throws ValueNotDefinedException {
    return null;
  }

  @Override
  public List<Double> calculateFunctionValue(Function f, double functionParameter)
      throws ValueNotDefinedException {
    return null;
  }

  @Override
  public List<Double> calculatePointIntersection(Function f, double functionValue)
      throws ValueNotDefinedException {
    return null;
  }
}
