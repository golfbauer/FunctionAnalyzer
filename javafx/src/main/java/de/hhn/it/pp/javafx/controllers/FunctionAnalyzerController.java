package de.hhn.it.pp.javafx.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import de.hhn.it.pp.components.functionanalyzer.Function;
import de.hhn.it.pp.components.functionanalyzer.exceptions.ValueNotDefinedException;
import de.hhn.it.pp.components.functionanalyzer.provider.FunctionAnalyzer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void openDialogWindow(ActionEvent actionEvent) {

    }

    public void chooseXDialog(ActionEvent actionEvent) {

    }

    public void chooseYDialog(ActionEvent actionEvent) {

    }

    @FXML
    public void enterFunction(ActionEvent actionEvent) throws ValueNotDefinedException {
        currentF = currentFa.readFunction(functionInput.getText());
        Button temp = new Button();
        temp.setText(functionInput.getText());
        temp.setPrefSize(600, 50);
        temp.setMinHeight(50);
        temp.setMnemonicParsing(false);
        temp.setOnAction(this::parseToText);
        history.getChildren().add(temp);
        VBox.setMargin(temp, new Insets(5, 15, 5, 15));
        System.out.println(currentF);
//        minima.setText(currentFa.calculateMinima(currentF).toString());
//        maxima.setText(currentFa.calculateMaxima(currentF).toString());
//        xIntersection.setText(currentFa.calculateXIntersection(currentF).toString());
//        yIntersection.setText(currentFa.calculateYIntersection(currentF) + "");

    }

    @FXML
    public void parseToText(ActionEvent actionEvent) {
        functionInput.setText(((Button)actionEvent.getSource()).getText());
    }

    public void numberPadEntry(ActionEvent actionEvent) {
        switch(((Button)actionEvent.getSource()).getText()) {
            case("0"): functionInput.setText(functionInput.getText() + "0"); break;
            case("1"): functionInput.setText(functionInput.getText() + "1"); break;
            case("2"): functionInput.setText(functionInput.getText() + "2"); break;
            case("3"): functionInput.setText(functionInput.getText() + "3"); break;
            case("4"): functionInput.setText(functionInput.getText() + "4"); break;
            case("5"): functionInput.setText(functionInput.getText() + "5"); break;
            case("6"): functionInput.setText(functionInput.getText() + "6"); break;
            case("7"): functionInput.setText(functionInput.getText() + "7"); break;
            case("8"): functionInput.setText(functionInput.getText() + "8"); break;
            case("9"): functionInput.setText(functionInput.getText() + "9"); break;

            case(")"): functionInput.setText(functionInput.getText() + ")"); break;
            case("("): functionInput.setText(functionInput.getText() + "("); break;
            case("x"): functionInput.setText(functionInput.getText() + "x"); break;
            case("x²"): functionInput.setText(functionInput.getText() + "^2"); break;
            case("x^"): functionInput.setText(functionInput.getText() + "^"); break;
            case("sqrt()"): functionInput.setText(functionInput.getText() + "^1/2"); break;
            case("/"): functionInput.setText(functionInput.getText() + "/"); break;
            case("X"): functionInput.setText(functionInput.getText() + "X"); break;
            case("-"): functionInput.setText(functionInput.getText() + "-"); break;
            case("+"): functionInput.setText(functionInput.getText() + "+"); break;
            case("."): functionInput.setText(functionInput.getText() + "."); break;
            case("="): functionInput.setText(functionInput.getText() + "="); break;

            case("DEL"): functionInput.setText(functionInput.getText()
                .substring(0, functionInput.getText().length() - 1)); break;
            case("AC"): functionInput.setText(""); break;
        }
    }
}
