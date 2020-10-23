package de.hhn.it.pp.components.functionalalyzer;

import java.util.List;

public interface FunctionAnalyserService {
  /**
   * Converts a String into a Function
   * @param input String that should be converted to Function
   * @return Resulting function
   * @throws IllegalArgumentException For inputs that cannot be converted into a Function
   */
  Function readFunction(String input) throws IllegalArgumentException;

  /**
   * Calculates the minima values for a Funtion
   * @param f Function who's minima is to be determent
   * @return List of minima ascending from the smallest to the largest, empty List if none are found
   */
  List<Double> calculateMinima(Function f);
  /**
   * Calculates the maxima values for a Function
   * @param f Function who's maxima is to be determent
   * @return List of maxima descending from the largest to smallest, empty List if none are found
   */
  List<Double> calculateMaxima(Function f);

  /**
   * Calculates the intersection with the x axis
   * @param f Function who's intersections are to be determent
   * @return List of intersections ascending from smallest x-Value to largest, empty List if none are found
   */
  List<Double> calculateXIntersection(Function f);
  /**
   * Calculates the intersection with the y axis
   * @param f Function who's intersections are to be determent
   * @return List of intersection ascending from smallest x-Value to largest, empty List if none are found
   */
  List<Double> calculateYIntersection(Function f);

  /**
   * Calculates the function values for a specific x Value
   * @param f Function who's Values are to be determent
   * @param xValue Specific function value
   * @return List of all calculated y values ascending from smallest to largest
   * @throws ArithmeticException For xValue that result in arithmetic errors eg. divide by 0
   */
  List<Double> calculateFunctionValue(Function f, double xValue)
      throws IllegalArgumentException;

  /**
   * Calculates all x values that result in function value y
   * @param f Function who's x values are to be determent
   * @param yValue Specific function value
   * @return List of all calculated x values ascending from smallest to largest
   */
  List<Double> calculatePointIntersection(Function f, double yValue);

}
