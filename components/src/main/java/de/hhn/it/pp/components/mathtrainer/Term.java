package de.hhn.it.pp.components.mathtrainer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Term {
    public BigDecimal firstNumber;
    public BigDecimal secondNumber;
    public Character operator;
    public int decimals;


    /**
     * Constructor for instantiating the term.
     *
     * @param firstNumber
     * @param secondNumber
     * @param operator
     */
    public Term(BigDecimal firstNumber, BigDecimal secondNumber, Character operator, int decimals) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.operator = operator;
        this.decimals = decimals;
    }

    /**
     * allows the program to solve its own questions
     * @return solution of the term.
     */
    public BigDecimal getSolution() {
        BigDecimal sum;

        switch(operator) {
            case '*':
                sum = firstNumber.multiply(secondNumber);
                break;

            case '/':
                sum = firstNumber.divide(secondNumber, this.decimals, RoundingMode.UP);
                break;

            case '+':
                sum = firstNumber.add(secondNumber);
                break;

            case '-':
                sum = firstNumber.subtract(secondNumber);
                break;

            default:
                sum = BigDecimal.ZERO;
        }

        return sum;
    }

    @Override
    public String toString() {
        return firstNumber+ " " +operator+ " " +secondNumber+ " = ";
    }
}
