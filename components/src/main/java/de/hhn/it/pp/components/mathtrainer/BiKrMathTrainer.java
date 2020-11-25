package de.hhn.it.pp.components.mathtrainer;

import java.math.BigDecimal;

public class BiKrMathTrainer implements MathTrainer {

    private String username;
    private Section section;
    private Difficulty difficulty;
    private int decimalPlace;

    /**
     * Constructor to instantiate the basic functions for MathTrainer.
     */
    public BiKrMathTrainer() {
        section = Section.MIXED;
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


    public void setDecimalPlace(int decimalPlace) {
        this.decimalPlace = decimalPlace;
    }

    public int getDecimalPlace() {
        return this.decimalPlace;
    }

    @Override
    public Term createTerm() {
        int maxNumberSize =
            difficulty == Difficulty.EASY ? 9 :
                difficulty == Difficulty.MEDIUM ? 19 :
                    29;

        BigDecimal firstNumber = BigDecimal.valueOf((long)(Math.random()*maxNumberSize));
        BigDecimal secondNumber = BigDecimal.valueOf((long)(Math.random()*maxNumberSize));

        char [] operands = {'+','-','*','/'};
        Character operator =
            section == Section.PLUS ? operands[0] :
                section == Section.MINUS ? operands[1] :
                    section == Section.MULTIPLICATION ? operands[2] :
                        section == Section.DIVISION ? operands[3] :
                            operands[(int)(Math.random()*3)]; //Mixed Mode

        return new Term(firstNumber, secondNumber, operator);
    }

    /*public void startQuestion() {
        createTerm();
        Thread erzeugen
    }

    public void answerQuestion(int zeit, userinput) {
        int punkte = zeit * 2;
    }*/

    @Override
    public boolean solveTerm(String userInput, Term term){
        try{
            BigDecimal number = new BigDecimal(userInput);
            BigDecimal correctSolution = term.getSolution();

            if(number.equals(correctSolution)){
                return true;
            }
        } catch(IllegalArgumentException e){
            //System.out.println("Nutzereingabe: \"" +userInput+ "\". Bitte gib eine ganze Zahl als Lösung ein.");
        }
        return false;
    }
}
