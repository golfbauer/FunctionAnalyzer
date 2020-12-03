package de.hhn.it.pp.javafx.controllers.spellingtrainer;

import static de.hhn.it.pp.javafx.controllers.spellingtrainer.SpellingTrainerServiceController.service;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PopUpPageController   {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(PopUpPageController.class);

  public Label popUpTextLabel;

  public Button closeButton;

  public void handleCloseButtonClick(ActionEvent event){
    Node actualStage = (Node) event.getSource();
    Stage stage = (Stage) actualStage.getScene().getWindow();
    stage.close();
  }
}