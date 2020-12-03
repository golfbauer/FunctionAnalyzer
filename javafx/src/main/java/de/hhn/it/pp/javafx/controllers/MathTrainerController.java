package de.hhn.it.pp.javafx.controllers;

import de.hhn.it.pp.components.exceptions.IllegalParameterException;
import de.hhn.it.pp.components.mathtrainer.BiKrMathTrainer;
import de.hhn.it.pp.components.mathtrainer.Difficulty;
import de.hhn.it.pp.components.mathtrainer.Section;
import de.hhn.it.pp.components.mathtrainer.Term;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    TextField textfieldUsername;
    @FXML
    TextField textfieldDecimal;
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
        logger.info("Scene switched to: "+bp.toString());
        borderPaneMainMenu.setVisible(false);
        borderPaneQuestionRun.setVisible(false);
        borderPaneHistory.setVisible(false);

        bp.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            logger.info("Set username to MathTrainerDemo until changed by user");
            biKrMathTrainer.setUsername("MathTrainerDemo");
        } catch (IllegalParameterException e) {
            logger.info("Supress Exception if needed.");
            //e.printStackTrace();
        }

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

        textfieldUsername.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    biKrMathTrainer.setUsername(newValue);
                } catch (IllegalParameterException e) {
                    logger.info("Supress IllegalParameterException on empty string");
                    logger.info("Set Username to MathTrainerDemo if no other value is given");
                    try {
                        biKrMathTrainer.setUsername("MathTrainerDemo");
                    } catch (IllegalParameterException illegalParameterException) {
                        illegalParameterException.printStackTrace();
                    }
                }
            }
        });

        textfieldDecimal.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    biKrMathTrainer.setDecimalPlace(Integer.parseInt(newValue));
                } catch (IllegalParameterException e) {
                    logger.info("Provided decimal count was not an integer");
                    try {
                        biKrMathTrainer.setDecimalPlace(0);
                    } catch (IllegalParameterException illegalParameterException) {
                        logger.info("Set decimal count to 0 for damage control");
                        illegalParameterException.printStackTrace();
                    }
                } catch (NumberFormatException f){
                        logger.info("Supress NumberFormatException because of empty/false input");
                        logger.info("Decimal count will be set to 0 if no other value is provided");
                }
            }
        });

        choiceboxDifficulty.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int local = newValue.intValue();
                switch(local) {
                    case 0: biKrMathTrainer.setDifficulty(Difficulty.EASY);
                    break;
                    case 1: biKrMathTrainer.setDifficulty(Difficulty.MEDIUM);
                    break;
                    case 2: biKrMathTrainer.setDifficulty(Difficulty.HARD);
                    break;
                }
                logger.info("Changed difficulty level to "+biKrMathTrainer.getDifficulty());
            }
        });

        choiceboxMode.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                switch((Integer) newValue) {
                    case 0: biKrMathTrainer.setSection(Section.PLUS);
                        break;
                    case 1: biKrMathTrainer.setSection(Section.MINUS);
                        break;
                    case 2: biKrMathTrainer.setSection(Section.DIVISION);
                        break;
                    case 3: biKrMathTrainer.setSection(Section.MULTIPLICATION);
                        break;
                    case 4: biKrMathTrainer.setSection(Section.MIXED);
                }
                logger.info("Changing calculation mode to "+biKrMathTrainer.getSection());
            }
        });

        radioButtonWarmup.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue) {
                    biKrMathTrainer.setWarmup(true);
                } else {
                    biKrMathTrainer.setWarmup(false);
                }
                logger.info("Warmupmode selected: " + biKrMathTrainer.getWarmup());
            }
        });


    }

    //----------- Scene: Main Menu ---------

    public void startGame(ActionEvent actionEvent) {
      logger.info("Play button pressed, game started as " + biKrMathTrainer.getUsername() + " with "
                + biKrMathTrainer.getDecimalPlace() + " decimals on "
                + biKrMathTrainer.getDifficulty() + " with calculation mode "
                + biKrMathTrainer.getSection() + " in warmup mode: " + biKrMathTrainer.getWarmup());

      switchScene(borderPaneQuestionRun);
      nextQuestion(null);
        try {
            biKrMathTrainer.startGame(biKrMathTrainer.getWarmup());
        } catch (IllegalParameterException e) {
            logger.info("Bad input, please correct your entires.");
            e.printStackTrace();
        }
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
