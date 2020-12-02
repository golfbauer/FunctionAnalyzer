package de.hhn.it.pp.javafx.controllers.mathtrainer;

import de.hhn.it.pp.components.exceptions.IllegalParameterException;
import de.hhn.it.pp.components.mathtrainer.BiKrMathTrainer;
import de.hhn.it.pp.components.mathtrainer.Difficulty;
import de.hhn.it.pp.components.mathtrainer.Section;
import de.hhn.it.pp.javafx.controllers.coffeemaker.CoffeeMakerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MathTrainerController extends AnchorPane {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(MathTrainerController.class);

    @FXML
    TextField usernameTextField;
    @FXML
    TextField decimalTextField;
    @FXML
    ChoiceBox difficultyChoiceBox;
    @FXML
    ChoiceBox modeChoiceBox;
    @FXML
    RadioButton warmupRadioButton;
    @FXML
    RadioButton countdownRadioButton;
    @FXML
    Button playButton;
    @FXML
    Button checkhistoryButton;

    private BiKrMathTrainer biKrMathTrainer;

    public MathTrainerController(final BiKrMathTrainer mathtrainer) {
      logger.info("Creating BiKrMathTrainer instance {}", mathtrainer);
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mathtrainer"
              + "/MathTrainer.fxml"));
      loader.setRoot(this);
      loader.setController(this);


      try {
          loader.load();
      } catch (IOException e) {
          throw new RuntimeException(e);
      }

      this.biKrMathTrainer = mathtrainer;
      difficultyChoiceBox.getItems().add(Difficulty.EASY);
      difficultyChoiceBox.getItems().add(Difficulty.MEDIUM);
      difficultyChoiceBox.getItems().add(Difficulty.HARD);

      modeChoiceBox.getItems().add(Section.PLUS);
      modeChoiceBox.getItems().add(Section.MINUS);
      modeChoiceBox.getItems().add(Section.DIVISION);
      modeChoiceBox.getItems().add(Section.MULTIPLICATION);
      modeChoiceBox.getItems().add(Section.MIXED);

      ToggleGroup group = new ToggleGroup();
      warmupRadioButton.setToggleGroup(group);
      countdownRadioButton.setToggleGroup(group);
      warmupRadioButton.setSelected(true);
    }

    private void raiseExceptionToUI(final Exception e, final String header) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }

    public void enteredUsername(ActionEvent event) {
      logger.debug("Username specified.");
        try {
            biKrMathTrainer.setUsername(usernameTextField.getText());
        } catch (IllegalParameterException | IllegalStateException e) {
            raiseExceptionToUI(e, "Error when setting username");
        }
    }

    public void enteredDecimal(ActionEvent event) {
        logger.debug("Decimal after period specified.");
        try {
            biKrMathTrainer.setDecimalPlace(Integer.parseInt(decimalTextField.getText()));
        } catch (IllegalParameterException | IllegalStateException e) {
            raiseExceptionToUI(e, "Error when setting decimal count after period");
        }
    }

    public void selectedDifficulty(ActionEvent event) {
        logger.debug("Difficulty level specified.");
        try {
            biKrMathTrainer.setDifficulty((Difficulty)difficultyChoiceBox.getValue());
        } catch (IllegalStateException e) {
            raiseExceptionToUI(e, "Error when setting difficulty");
        }
    }

    public void selectedMode(ActionEvent event) {
        logger.debug("Calculation mode specified.");
        try {
            biKrMathTrainer.setSection((Section)modeChoiceBox.getValue());
        } catch (IllegalStateException e) {
            raiseExceptionToUI(e, "Error when setting calculation mode");
        }
    }

    public void selectedWarmup(ActionEvent event) {
        logger.debug("Gamemode specified.");
        try {
            biKrMathTrainer.setWarmup(warmupRadioButton.isArmed());
        } catch (IllegalStateException e) {
            raiseExceptionToUI(e, "Error when setting warmup mode");
        }
    }

    public void selectedCountdown(ActionEvent event) {
        logger.debug("Gamemode specified.");
        try {
            biKrMathTrainer.setWarmup(!countdownRadioButton.isArmed());
        } catch (IllegalStateException e) {
            raiseExceptionToUI(e, "Error when setting calculation mode");
        }
    }

    public void clickedPlay(ActionEvent event) {
        logger.debug("Play clicked, game started.");
        try {
            biKrMathTrainer.setWarmup(!countdownRadioButton.isArmed());
        } catch (IllegalStateException e) {
            raiseExceptionToUI(e, "Error when setting calculation mode");
        }
    }

    public void clickedCheckHistory(ActionEvent event) {
        logger.debug("Check history clicked, display history view.");
        try {
            biKrMathTrainer.setWarmup(!countdownRadioButton.isArmed());
        } catch (IllegalStateException e) {
            raiseExceptionToUI(e, "Error when setting calculation mode");
        }
    }



}
