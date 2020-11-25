package de.hhn.it.pp.components.functionanalyzer;

import java.util.List;
import de.hhn.it.pp.components.functionanalyzer.Function;
import de.hhn.it.pp.components.functionanalyzer.FunctionAnalyserService;
import de.hhn.it.pp.components.functionanalyzer.exceptions.ValueNotDefinedException;

public class DemoFunctionanalyzerUsage {

    public static FunctionAnalyserService functionAnalyserService = null;

    public static void main(String[] args)throws IllegalArgumentException,
        ValueNotDefinedException {
        Function f = functionAnalyserService.readFunction("2x^2+x");    //Value passed from GUI to readFunction
        List<Double> min = functionAnalyserService.calculateMinima(f);
        List<Double> max = functionAnalyserService.calculateMaxima(f);
        List<Double> xIntersection = functionAnalyserService.calculateXIntersection(f);
        List<Double> yIntersection = functionAnalyserService.calculateYIntersection(f);
        List<Double> functionValueTest = functionAnalyserService.calculateFunctionValue(f, 25);
        List<Double> functionPointTest = functionAnalyserService.calculatePointIntersection(f, 25);
    }
}
