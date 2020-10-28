package de.hhn.it.pp.components.functionanalyzer;

import java.util.List;
import de.hhn.it.pp.components.functionanalyzer.Function;
import de.hhn.it.pp.components.functionanalyzer.FunctionAnalyserService;

public class DemoFunctionanalyzerUsage {

    public static FunctionAnalyserService functionAnalyserService = new FunctionAnalyserService() { //funcationanalyzer Interface to check for mistakes inside methods
        @Override
        public Function readFunction(String input) throws IllegalArgumentException {
            return null;
        }

        @Override
        public List<Double> calculateMinima(Function f) {
            return null;
        }

        @Override
        public List<Double> calculateMaxima(Function f) {
            return null;
        }

        @Override
        public List<Double> calculateXIntersection(Function f) {
            return null;
        }

        @Override
        public List<Double> calculateYIntersection(Function f) {
            return null;
        }

        @Override
        public List<Double> calculateFunctionValue(Function f, double xValue)
            throws IllegalArgumentException {
            return null;
        }

        @Override
        public List<Double> calculatePointIntersection(Function f, double yValue) {
            return null;
        }
    };
    public static void main(String[] args)throws IllegalArgumentException{
        Function f = functionAnalyserService.readFunction("2x^2+x");    //Value passed from GUI to readFunction
        List<Double> min = functionAnalyserService.calculateMinima(f);
        List<Double> max = functionAnalyserService.calculateMaxima(f);
        List<Double> xIntersection = functionAnalyserService.calculateXIntersection(f);
        List<Double> yIntersection = functionAnalyserService.calculateYIntersection(f);
        List<Double> functionValueTest = functionAnalyserService.calculateFunctionValue(f, 25);
        List<Double> functionPointTest = functionAnalyserService.calculatePointIntersection(f, 25);
    }
}
