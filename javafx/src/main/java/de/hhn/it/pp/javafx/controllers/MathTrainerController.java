package de.hhn.it.pp.javafx.controllers;

import de.hhn.it.pp.components.mathtrainer.BiKrMathTrainer;
import de.hhn.it.pp.components.mathtrainer.Difficulty;
import de.hhn.it.pp.components.mathtrainer.Section;
import de.hhn.it.pp.components.mathtrainer.Term;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MathTrainerController extends Controller implements Initializable {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(MathTrainerController.class);

    //--------- Scene: Main Menu --------
    @FXML
    BorderPane borderPaneMainMenu;
    @FXML
    ChoiceBox choiceboxDifficulty;
    @FXML
    ChoiceBox choiceboxMode;
    @FXML
    RadioButton radioButtonWarmup;
    @FXML
    RadioButton radioButtonCountdown;


    //--------- Scene: Question Run ---------
    @FXML
    BorderPane borderPaneQuestionRun;
    @FXML
    TextField textfieldTerm;
    @FXML
    TextField textfieldYourSolution;
    @FXML
    TextField textfieldActualSolution;
    @FXML
    Label labelQuestionInTurn;
    @FXML
    Label labelYourPoints;


    //------- Scene: History -------------
    @FXML
    BorderPane borderPaneHistory;
    @FXML
    Button buttonPlayAgain;



    private BiKrMathTrainer biKrMathTrainer;

    public MathTrainerController() {
        biKrMathTrainer = new BiKrMathTrainer();
    }


    public void switchScene(BorderPane bp) {
        borderPaneMainMenu.setVisible(false);
        borderPaneQuestionRun.setVisible(false);
        borderPaneHistory.setVisible(false);

        bp.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceboxDifficulty.getItems().add(Difficulty.EASY);
        choiceboxDifficulty.getItems().add(Difficulty.MEDIUM);
        choiceboxDifficulty.getItems().add(Difficulty.HARD);
        choiceboxDifficulty.setValue(Difficulty.EASY);

        choiceboxMode.getItems().add(Section.PLUS);
        choiceboxMode.getItems().add(Section.MINUS);
        choiceboxMode.getItems().add(Section.DIVISION);
        choiceboxMode.getItems().add(Section.MULTIPLICATION);
        choiceboxMode.getItems().add(Section.MIXED);
        choiceboxMode.setValue(Section.PLUS);

        ToggleGroup tg = new ToggleGroup();
        radioButtonWarmup.setToggleGroup(tg);
        radioButtonCountdown.setToggleGroup(tg);
        radioButtonWarmup.setSelected(true);
    }

    //----------- Scene: Main Menu ---------
    public void startGame(ActionEvent actionEvent) {
        switchScene(borderPaneQuestionRun);
        nextQuestion(null);

    }

    public void showHistoryFromMainMenu(ActionEvent actionEvent) {
        switchScene(borderPaneHistory);
        buttonPlayAgain.setVisible(false);
    }

    //---------- Scene: Question Run ----------
    public void stopPlaying(ActionEvent actionEvent) {
        switchScene(borderPaneMainMenu);
    }

    public void nextQuestion(ActionEvent actionEvent) {
        if(biKrMathTrainer.getInTurn() == 0) {
            labelYourPoints.setText("0");
        }

        if (biKrMathTrainer.getInTurn() == 20) {
            switchScene(borderPaneHistory);
            buttonPlayAgain.setVisible(true);
        }

        Term term = biKrMathTrainer.nextTerm();

        textfieldTerm.setText(term.toString());
        textfieldYourSolution.requestFocus();
        textfieldActualSolution.setText("");

        labelQuestionInTurn.setText(""+biKrMathTrainer.getInTurn());
    }

    public void keyReleasedAtYourSolution(KeyEvent k) {
        if(k.getCode().equals(KeyCode.ENTER)) {
            logger.debug("Return key pressed");
        }
    }

    public void showSolution(ActionEvent actionEvent) {
        textfieldActualSolution.setText(biKrMathTrainer.getCurrentTerm().getSolution().toString());
    }

    //-------- Scene: History -----------
    public void playAgain(ActionEvent actionEvent) {
        switchScene(borderPaneQuestionRun);
    }

    public void exitToMenu(ActionEvent actionEvent) {
        switchScene(borderPaneMainMenu);
    }
}
