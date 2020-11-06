package de.hhn.it.pp.components.mathtrainer;

import java.math.BigDecimal;

public class BiKrMathTrainer implements MathTrainer {

    private String username;
    private Section currentSection;
    private Difficulty difficulty;

    /**
     * Constructor to instantiate the basic functions for MathTrainer.
     */
    public BiKrMathTrainer() {
        currentSection = Section.MIXED;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Difficulty getDifficulty() {
        return difficulty;
    }

    @Override
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }


    public Term createTerm() {
        char [] operands = {'+','-','*','/'};

        BigDecimal firstNumber = BigDecimal.valueOf((long)(Math.random()*29));
        BigDecimal secondNumber = BigDecimal.valueOf((long)(Math.random()*29));
        Character operator =
            currentSection == Section.PLUS ? operands[0] :
                currentSection == Section.MINUS ? operands[1] :
                    currentSection == Section.MULTIPLICATION ? operands[2] :
                        currentSection== Section.DIVISION ? operands[3]: operands[(int)(Math.random()*3)];

        return new Term(firstNumber, secondNumber, operator);
    }


    public boolean solveTerm(String userInput, Term term){
        try{
            BigDecimal number = new BigDecimal(userInput);
            BigDecimal correctSolution = term.getSolution();

            if(number.equals(correctSolution)){
                return true;
            }
        } catch(IllegalArgumentException e){
            System.out.println("Bitte gib eine ganze Zahl als Lösung ein.");
        }
        return false;
    }
}
