package de.hhn.it.pp.components.mathtrainer;

import de.hhn.it.pp.components.example.coffeemakerservice.provider.WnckCoffeeMaker;
import de.hhn.it.pp.components.exceptions.IllegalParameterException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BiKrMathTrainer implements MathTrainer {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(BiKrMathTrainer.class);

    private String username;
    private int userscore;
    private Section section;
    private Difficulty difficulty;
    private int decimalPlace;
    private List<String> history;
    private boolean wantstoexit;
    private boolean timeisup;
    private int inturn;

    /**
     * Constructor to instantiate the basic functions for MathTrainer.
     */
    public BiKrMathTrainer() {
        section = Section.MIXED;
        difficulty = Difficulty.EASY;
        decimalPlace = 0;
        userscore = 0;
        history = new ArrayList<>();
        wantstoexit = false;
        timeisup = false;
        inturn = 0;
        createDemoHistoryData();
    }

    @Override
    public void setUsername(String username) throws IllegalParameterException{
        if(username.length() > 0){
            this.username = username;
        } else throw new IllegalParameterException("Bitte einen Namen mit mindestens einem Zeichen festlegen.");
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
    public void setDecimalPlace(int decimalPlace) throws IllegalParameterException {
        if(decimalPlace >= 0){
            this.decimalPlace = decimalPlace;
        } else throw new IllegalParameterException("Bitte nur positive Werte fuer die Nachkommastellenanzahl eingeben.");
    }
    @Override
    public int getDecimalPlace() {
        return this.decimalPlace;
    }
    @Override
    public void setUserScore(int number) throws IllegalParameterException{
        if(number >= 0){
            this.userscore = number;
        } else throw new IllegalParameterException("Bitte den UserScore nicht auf negative Werte setzen.");
    }
    @Override
    public int getUserScore(){
        return this.userscore;
    }
    @Override
    public void setWantsToExit(boolean exitboolean){
        this.wantstoexit = exitboolean;
    }
    @Override
    public boolean getWantsToExit(){
        return this.wantstoexit;
    }
    @Override
    public void setTimeIsUp(boolean timeboolean){
        this.timeisup = timeboolean;
    }
    @Override
    public boolean getTimeIsUp(){
        return this.timeisup;
    }
    @Override
    public int getInTurn(){
        return this.inturn;
    }
    @Override
    public void addToUserScore(int timebonus) throws IllegalParameterException{
        if(timebonus >=0){
            int points =
                difficulty == Difficulty.EASY ? 1 :
                        difficulty == Difficulty.MEDIUM ? 2 :
                                3;
            this.userscore = userscore + points+ timebonus;
        } else throw new IllegalParameterException("Der Zeitbonus kann nicht negativ sein.");
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

        if(secondNumber.equals(BigDecimal.ZERO) && operator.equals('/')) {
            secondNumber = BigDecimal.ONE;
        }

        return new Term(firstNumber, secondNumber, operator, this.getDecimalPlace());
    }

    @Override
    public boolean solveTerm(String userInput, Term term) throws IllegalArgumentException {
        if(userInput.contains(",")){
            userInput = userInput.replace(',', '.');
        }
        try{
            BigDecimal number = new BigDecimal(userInput);
            BigDecimal correctSolution = term.getSolution();

            if(number.equals(correctSolution)){
                return true;
            }
            else {
                return false;
            }
        } catch(IllegalArgumentException e){
            throw new IllegalArgumentException("Bitte eine Ganzzahl als Ergebnis eingeben!");
        }
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
            //wenn ungueltige Zeichen oder Ergebnisse eingegeben werden, werden diese mit diesem Catch Block abgefangen
            //und solveTerm gibt als Ergebnis false zurueck
            return false;
        } catch(IllegalParameterException p){
            //moegliche Exceptions von der Methode addToUserScore(solvedInSeconds) abfangen
            return false;
        }
        return false;
    }
    @Override
    public void addToHistory(){
        String entry = ""+this.username+"|"+this.userscore+"|"+this.difficulty+"|"+this.section+"|countdown mode";
        history.add(entry);
    }
    @Override
    public List<String> getHistory(){
        return history;
    }

    @Override
    public BigDecimal helpUser(Term term) throws IllegalParameterException {
        if(term != null){
            return term.getSolution();
        } else throw new IllegalParameterException("Es wurde kein gueltiger Term oder ein null Objekt uebergeben.");
    }
    @Override
    public void startGame(boolean warmup) throws IllegalParameterException { //kein user input via scanner oder anderer art nehmen, nur fixe werte verwenden
        if(warmup) {
            for(int i=0; i<20; i++) {
                inturn = i;
                Term current = this.createTerm();
                String userinput = "10000";
                boolean solved = solveTerm(userinput, current);
                if(solved) {
                    this.addToUserScore(0);
                }
                else {
                    //do not add points in case of wrong answer.
                }
                i = exitGame(i, wantstoexit);
            }
            if(wantstoexit == false){
                int storeScore = this.getUserScore();
                this.setUserScore(1);
                int finalScore = storeScore + userscore;
                this.setUserScore(finalScore);
                addToHistory();
            }
        }
        else{
            for(int i=0; i<20; i++) {
                inturn = i;
                Term current = this.createTerm();
                String userinput = "10000";
                boolean solved = solveTerm(userinput, current);

                if(timeisup == false) {
                    if(solved) {
                        this.addToUserScore(0);
                    }
                    else {
                        //do not add points in case of wrong answer.
                    }
                }

                i = exitGame(i, wantstoexit);

            }
            if(wantstoexit == false){
                int storeScore = this.getUserScore();
                this.setUserScore(1);
                int finalScore = storeScore + userscore;
                this.setUserScore(finalScore);
                addToHistory();
            }
            inturn = 0;
        }
    }


    @Override
    public int exitGame(int loopCount , boolean exit) throws IllegalParameterException{
        if(loopCount >=0){
            if(exit) {
                loopCount = 20;
                return loopCount;
            } else {
                return loopCount;
            }
        } else throw new IllegalParameterException("Der uebergebene LoopCount darf nicht negativ sein.");
    }

    public void createDemoHistoryData(){
        history.add("Matthew|15|EASY|MULTIPLICATION|countdown mode");
        history.add("Hammond|21|HARD|ADDITION|countdown mode");
        history.add("Erika|18|MEDIUM|MIXED|countdown mode");
    }
}
