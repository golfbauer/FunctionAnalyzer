package de.hhn.it.pp.javafx.controllers;

import de.hhn.it.pp.components.functionanalyzer.Function;
import de.hhn.it.pp.components.functionanalyzer.exceptions.ValueNotDefinedException;
import de.hhn.it.pp.components.functionanalyzer.provider.FunctionAnalyzer;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;

public class FunctionAnalyzerController extends Controller implements Initializable {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(FunctionAnalyzerController.class);

  private FunctionAnalyzer currentFa = new FunctionAnalyzer();
  private Function currentF;


  @FXML
  TextField functionInput;
  @FXML
  Label minima;
  @FXML
  Label maxima;
  @FXML
  Label xIntersection;
  @FXML
  Label yIntersection;
  @FXML
  Label xAndyValue;
  @FXML
  VBox history;
  @FXML
  Label functionIdentifier;
  @FXML
  Label errorLabel;

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

  /**
   * Event opens new Window in which user is asked to enter a x Value.
   * @param actionEvent Triggered when button pressed
   */
  public void chooseXDialog(ActionEvent actionEvent) {
    logger.info("Choose X Button pressed opening a new Dialog Window");
    currentF = currentFa.readFunction(functionInput.getText());
    TextInputDialog dialog = new TextInputDialog("");
    dialog.setTitle("Choose X");
    dialog.setHeaderText("Please choose a X Value for your Function.");
    dialog.setContentText("x = ");

    Optional<String> result = dialog.showAndWait();

    result.ifPresent(value -> {
      try {
        xAndyValue.setText(currentFa.calculateFunctionValue(currentF,
            Double.parseDouble(value)) + "");
        functionIdentifier.setText("X: " + result.get() + ", Y: ");
        logger.info("Setting Value of Y to " + functionIdentifier.toString());
      } catch (ValueNotDefinedException e) {
        xAndyValue.setText("Value is not defined.");
        logger.debug("Entered Value " + value + " for X is not valid");
      }
    });
  }

  /**
   * Event opens new Window in which user is asked to enter a y Value.
   * @param actionEvent Triggered when button pressed
   */
  public void chooseYDialog(ActionEvent actionEvent) {
    logger.info("Find x for f(x) = y Button pressed opening a new Dialog Window");
    currentF = currentFa.readFunction(functionInput.getText());
    TextInputDialog dialog = new TextInputDialog("");
    dialog.setTitle("Choose Y");
    dialog.setHeaderText("Please choose a Y Value for your Function.");
    dialog.setContentText("y = ");

    Optional<String> result = dialog.showAndWait();

    result.ifPresent(value -> {
      try {
        xAndyValue.setText(checkReturnValue(currentFa.calculatePointIntersection(currentF,
            Double.parseDouble(value))) + "");
        functionIdentifier.setText("Y: " + result.get() + ", X: ");
        logger.info("Setting Value of X to " + functionIdentifier.toString());
      } catch (ValueNotDefinedException e) {
        xAndyValue.setText("Value is not defined");
        logger.debug("Entered Value " + value + " for Y is not valid");
      }
    });
  }

  /**
   * Displays all result for the String that was typed into the TextField.
   * @param actionEvent Triggered when button pressed
   * @throws ValueNotDefinedException Throws Exception in case of illegal entry
   */
  @FXML
  public void enterFunction(ActionEvent actionEvent) throws ValueNotDefinedException {
    logger.info("Enter Button pressed and String in text field is given on");
    String input = functionInput.getText();
    input = input.replaceAll("\\s", "");
    if (input.matches("[()x^+*\\/\\-.0-9]*") && !input.equals("")) {
      errorLabel.setVisible(false);
      logger.info("String " + input + " contains allowed characters");
      currentF = currentFa.readFunction(input);
      Button currentFButton = new Button();
      currentFButton.setText(functionInput.getText());
      currentFButton.setPrefSize(600, 50);
      currentFButton.setMinHeight(50);
      currentFButton.setMnemonicParsing(false);
      currentFButton.setOnAction(this::parseToText);
      history.getChildren().add(currentFButton);
      VBox.setMargin(currentFButton, new Insets(5, 15, 5, 15));
      logger.info("Created Button " + currentFButton.toString() + " and added it to history");
      minima.setText(checkReturnValue(currentFa.calculateMinima(currentF)));
      logger.info("Setting Label " + minima + " to " + minima.toString());
      maxima.setText(checkReturnValue(currentFa.calculateMaxima(currentF)));
      logger.info("Setting Label " + maxima + " to " + maxima.toString());
      xIntersection.setText(checkReturnValue(currentFa.calculateXIntersection(currentF)));
      logger.info("Setting Label " + xIntersection + " to " + xIntersection.toString());
      yIntersection.setText(currentFa.calculateYIntersection(currentF) + "");
      logger.info("Setting Label " + yIntersection + " to " + yIntersection.toString());
    } else {
      errorLabel.setVisible(true);
      logger.debug("String " + input + " contains non valid characters");
    }
  }

  /**
   * Checks if return Value is empty and returns empty Brackets instead.
   * @param input List of x values
   * @return Final values all rounded
   */
  public String checkReturnValue(List<Double> input) {
    logger.debug("Checking " + input);
    if (input == null) {
      return "[]";
    } else {
      for (int i = 0; i < input.size(); i++) {
        double temp = round(input.get(i), 3);
        input.remove(i);
        input.add(i, temp);
      }
      return input.toString();
    }
  }

  /**
   * Rounds given double.
   * @param value To be rounded Value
   * @param places Amount of digits it will be rounded to
   * @return Rounded Variable
   */
  public double round(double value, int places) {
    logger.debug("Rounding + " + value + ", " + places + " amount of places");
    if (places < 0) {
      throw new IllegalArgumentException();
    }

    long factor = (long) Math.pow(10, places);
    value = value * factor;
    long tmp = Math.round(value);
    return (double) tmp / factor;
  }

  /**
   * Takes text from button in History and parses it into the text field.
   * @param actionEvent Triggered when button pressed
   */
  @FXML
  public void parseToText(ActionEvent actionEvent) {
    logger.info("Button " + ((Button) actionEvent.getSource()).getText() + " in history pressed");
    functionInput.setText(((Button) actionEvent.getSource()).getText());
    logger.info("Removing Button" + ((Button) actionEvent.getSource()).getText());
    history.getChildren().remove(actionEvent.getSource());
  }

  /**
   * Takes the text from the button and parses it into the text field.
   * @param actionEvent Triggered when button pressed
   */
  public void numberPadEntry(ActionEvent actionEvent) {
    logger.info("Button " + ((Button) actionEvent.getSource()).getText() + " pressed");
    logger.info("Adding Text from Button to " + functionInput.getText());
    switch (((Button) actionEvent.getSource()).getText()) {
      case ("0"):
        functionInput.setText(functionInput.getText() + "0");
        break;
      case ("1"):
        functionInput.setText(functionInput.getText() + "1");
        break;
      case ("2"):
        functionInput.setText(functionInput.getText() + "2");
        break;
      case ("3"):
        functionInput.setText(functionInput.getText() + "3");
        break;
      case ("4"):
        functionInput.setText(functionInput.getText() + "4");
        break;
      case ("5"):
        functionInput.setText(functionInput.getText() + "5");
        break;
      case ("6"):
        functionInput.setText(functionInput.getText() + "6");
        break;
      case ("7"):
        functionInput.setText(functionInput.getText() + "7");
        break;
      case ("8"):
        functionInput.setText(functionInput.getText() + "8");
        break;
      case ("9"):
        functionInput.setText(functionInput.getText() + "9");
        break;
      case (")"):
        functionInput.setText(functionInput.getText() + ")");
        break;
      case ("("):
        functionInput.setText(functionInput.getText() + "(");
        break;
      case ("x"):
        functionInput.setText(functionInput.getText() + "x");
        break;
      case ("x²"):
        functionInput.setText(functionInput.getText() + "^2");
        break;
      case ("x^"):
        functionInput.setText(functionInput.getText() + "^");
        break;
      case ("sqrt()"):
        functionInput.setText(functionInput.getText() + "^1/2");
        break;
      case ("/"):
        functionInput.setText(functionInput.getText() + "/");
        break;
      case ("X"):
        functionInput.setText(functionInput.getText() + "*");
        break;
      case ("-"):
        functionInput.setText(functionInput.getText() + "-");
        break;
      case ("+"):
        functionInput.setText(functionInput.getText() + "+");
        break;
      case ("."):
        functionInput.setText(functionInput.getText() + ".");
        break;
      case ("="):
        functionInput.setText(functionInput.getText() + "=");
        break;

      case ("DEL"):
        if (functionInput.getText().length() > 0) {
          functionInput.setText(functionInput.getText()
              .substring(0, functionInput.getText().length() - 1));
        }
        break;
      case ("AC"):
        functionInput.setText("");
        break;
      default:
        break;
    }
  }
}
