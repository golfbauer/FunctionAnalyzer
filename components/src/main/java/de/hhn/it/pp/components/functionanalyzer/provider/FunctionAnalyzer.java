package de.hhn.it.pp.components.functionanalyzer.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    boolean checkIfBracket = false;

    input = input.replaceAll("\\s", "");
    ArrayList<String> functionString = getFEasString(input, '+');
    for (int i = 0; i < functionString.size(); i++) {
      if (!functionString.get(i).equals("*") && !functionString.get(i).equals("/") &&
          !functionString.get(i).equals("+") && !functionString.get(i).equals("-")) {
        if (functionString.get(i).equals("(")) {
          FunctionElement temp = new FunctionElement(
              Operator.operatorFromSymbol(functionString.get(i - 1).charAt(0)));
          int j;
          for (j = i + 1; !functionString.get(j).equals(")"); j++) {
            temp.addFunctionElementComponent(getTerm(functionString.get(j)));
          }
          result.add(temp);
          i = j;
        } else {
          result.add(new FunctionElement(Operator.operatorFromSymbol(
              functionString.get(0).charAt(0)), getTerm(functionString.get(i))));
        }
      }
    }
    return result;
  }

  public ArrayList<String> getFEasString(String input, char operator) {
    ArrayList<String> terms = new ArrayList<>();
    ArrayList<String> temporary = new ArrayList<>();
    terms.add(String.valueOf(operator));
    String buffer = "";

    for (int i = 0; i < input.length(); i++) {
      if (i > 0 && buffer != "") {
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
            for (int j = 0; j < temporary.size(); j++) {
              terms.add(temporary.get(j));
            }
            terms.add(")");
            temporary.clear();
          }
        }
      }
      if (input.charAt(i) == '(') {
        char op;
        if(i > 0) {
          op = input.charAt(i - 1);
        } else {
          op = '+';
        }
        String innerTerm = "";
        for (int j = i + 1; input.charAt(j) != ')'; j++) {
          innerTerm += input.charAt(j);
          if (input.charAt(j + 1) == ')') {
            input = input.substring(0, i - 1) + input.substring(j + 2);
            temporary = getFEasString(innerTerm, op);
            i = i - 2;
            break;
          }
        }
      } else {
        buffer += input.charAt(i);
      }
      if (i == input.length() - 1 && buffer != "") {
        terms.add(buffer);
        buffer = "";

        if (!(temporary.isEmpty())) {
          temporary.add(1, "(");
          for (int j = 0; j < temporary.size(); j++) {
            terms.add(temporary.get(j));
          }
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
      } else buffer += input.charAt(i);
      if (i == input.length() - 1 && buffer != "") {
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
            if (nums.get(i).charAt(i) == '^') {
              check = true;
            }
            if (check) {
              buffer += nums.get(i).charAt(i);
            }
          }
          if (nums.get(i - 1) == "/") {
            exponent = exponent * -1;
          }
          exponent = Double.parseDouble(buffer);
          buffer = "";
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
          if(i > 0) {
            if (nums.get(i - 1).equals("*")) {
              factor = factor * Math.pow(Double.parseDouble(buffer1), Double.parseDouble(buffer));
            } else {
              factor = (1) * factor / Math.pow(Double.parseDouble(buffer1), Double.parseDouble(buffer));
            }
          } else {
            factor = Math.pow(Double.parseDouble(buffer1), Double.parseDouble(buffer));
          }
        } else {
          if(i > 0) {
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
    if (variable == null ) {
      return new Term(new Term(exponent), factor);
    }
    return new Term(new Term(exponent), factor, variable);
  }


  public double convertNumString(String input) {
    double exponent = 1;
    List<String> inputElements = new ArrayList<>();
    final String regex = "[\\/*^]|\\d*";

    final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
    final Matcher matcher = pattern.matcher(input);

    while (matcher.find()) {
      inputElements.add(matcher.group());
    }

    for (int i = 0; i < inputElements.size(); i++) {
      if (inputElements.get(i).contains("^")) {
        exponent = Math.pow(Double.parseDouble(inputElements.get(i - 1)),
            Double.parseDouble(inputElements.get(i + 1)));
        inputElements.remove(i + 1);
        inputElements.remove(i);
        inputElements.remove(i - 1);
        inputElements.add(i - 1,String.valueOf(exponent));
        i = i - 1;

      }
    }
    for (int i = 0; i < inputElements.size(); i++) {
      if (inputElements.get(i).contains("*")) {
        exponent = Double.parseDouble(inputElements.get(i - 1))
            * Double.parseDouble(inputElements.get(i + 1));
        inputElements.remove(i + 1);
        inputElements.remove(i);
        inputElements.remove(i - 1);
        inputElements.add(i - 1,String.valueOf(exponent));
        i = i - 1;
      } else if (inputElements.get(i).contains("/")) {
        exponent = Double.parseDouble(inputElements.get(i - 1))
            / Double.parseDouble(inputElements.get(i + 1));
        inputElements.remove(i + 1);
        inputElements.remove(i);
        inputElements.remove(i - 1);
        inputElements.add(i - 1,String.valueOf(exponent));
        i = i - 1;
      }
    }
    return Double.parseDouble(inputElements.get(1));
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
