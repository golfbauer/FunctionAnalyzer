package de.hhn.it.pp.components.mathtrainer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BiKrMathTrainer implements MathTrainer {

    private String username;
    private int userscore;
    private Section section;
    private Difficulty difficulty;
    private int decimalPlace;
    private List<String> history;

    /**
     * Constructor to instantiate the basic functions for MathTrainer.
     */
    public BiKrMathTrainer() {
        section = Section.MIXED;
        userscore = 0;
        history = new ArrayList<>();
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

    @Override
    public void setDecimalPlace(int decimalPlace) {
        this.decimalPlace = decimalPlace;
    }
    @Override
    public int getDecimalPlace() {
        return this.decimalPlace;
    }
    @Override
    public void setUserScore(int number) {
        this.userscore = number;
    }
    @Override
    public int getUserScore(){
        return this.userscore;
    }
    @Override
    public void addToUserScore(int timebonus){
        int points =
                difficulty == Difficulty.EASY ? 1 :
                        difficulty == Difficulty.MEDIUM ? 2 :
                                3;
        this.userscore = userscore + points+ timebonus;
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

        return new Term(firstNumber, secondNumber, operator, this.getDecimalPlace());
    }

    @Override
    public boolean solveTerm(String userInput, Term term){
        if(userInput.contains(",")){
            userInput = userInput.replace(',', '.');
        }
        try{
            BigDecimal number = new BigDecimal(userInput);
            BigDecimal correctSolution = term.getSolution();

            if(number.equals(correctSolution)){
                return true;
            }
        } catch(IllegalArgumentException e){
            //bei Falscheingabe wird die Frage als nicht geloest behandelt
        }
        return false;
    }

    @Override
    public boolean solveTerm(String userInput, Term term, int solvedInSeconds){
        if(userInput.contains(",")){
            userInput = userInput.replace(',', '.');
        }
        try{
            BigDecimal number = new BigDecimal(userInput);
            BigDecimal correctSolution = term.getSolution();

            if(number.equals(correctSolution)){
                this.addToUserScore(solvedInSeconds);
                return true;
            }
        } catch(IllegalArgumentException e){
            //bei Falscheingabe wird die Frage als nicht geloest behandelt
            //der User bekommt keine Punkte
        }
        return false;
    }
    @Override
    public void addToHistory(){
        String entry = ""+this.username+ " : "+this.userscore+" points on difficulty: "+this.difficulty+". ";
        history.add(entry);
    }
    @Override
    public List<String> getHistory(){
        return history;
    }
}
