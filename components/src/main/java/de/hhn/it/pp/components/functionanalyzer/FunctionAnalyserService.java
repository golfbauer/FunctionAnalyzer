package de.hhn.it.pp.components.functionanalyzer;

import java.util.List;
import de.hhn.it.pp.components.functionanalyzer.exceptions.ValueNotDefinedException;

public interface FunctionAnalyserService {
  /**
   * Converts a String into a Function.
   * @param input String that should be converted to Function
   * @return Resulting function
   * @throws IllegalArgumentException For inputs that cannot be converted into a Function
   */
  Function readFunction(String input) throws IllegalArgumentException;

  /**
   * Calculates the minima values for a Funtion.
   * @param f Function who's minima is to be determent
   * @return List of minima ascending from the smallest to the largest, empty List if none are found
   * @throws IllegalStateException If operation can not be performed with current object state
   */
  List<Double> calculateMinima(Function f) throws ValueNotDefinedException;

  /**
   * Calculates the maxima values for a Function.
   * @param f Function who's maxima is to be determent
   * @return List of maxima descending from the largest to smallest, empty List if none are found
   * @throws IllegalStateException If operation can not be performed with current object state
   */
  List<Double> calculateMaxima(Function f) throws ValueNotDefinedException;

  /**
   * Calculates the intersection with the x axis.
   * @param f Function who's intersections are to be determent
   * @return List of intersections ascending from smallest x-Value to largest,
   *     empty List if none are found
   * @throws IllegalStateException If operation can not be performed with current object state
   */
  List<Double> calculateXIntersection(Function f) throws ValueNotDefinedException;

  /**
   * Calculates the intersection with the y axis.
   * @param f Function who's intersections are to be determent
   * @return List of intersection ascending from smallest x-Value to largest,
   *     empty List if none are found
   * @throws IllegalStateException If operation can not be performed with current object state
   */
  List<Double> calculateYIntersection(Function f) throws ValueNotDefinedException;

  /**
   * Calculates the function values for a specific x Value.
   * @param f Function who's Values are to be determent
   * @param functionParameter Specific function value
   * @return List of all calculated y values ascending from smallest to largest
   * @throws ArithmeticException For functionParameter that result in arithmetic errors
   *     eg. divide by 0
   * @throws IllegalStateException If operation can not be performed with current object state
   */
  double calculateFunctionValue(Function f, double functionParameter)
      throws ValueNotDefinedException;

  /**
   * Calculates all x values that result in function value y.
   * @param f Function who's x values are to be determent
   * @param functionValue Specific function value
   * @return List of all calculated x values ascending from smallest to largest
   * @throws IllegalStateException If operation can not be performed with current object state
   */
  List<Double> calculatePointIntersection(Function f, double functionValue)
      throws ValueNotDefinedException;

}
