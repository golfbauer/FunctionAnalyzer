package de.hhn.it.pp.javafx.controllers.spellingtrainer;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PopUpPageController {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(PopUpPageController.class);

  public Label popUpTextLabel;

  public Button closeButton;

  /**
   * Method to handle the event by clicking the 'Close' button.
   *
   * @param event event that occures, when clicking on the 'Close' button
   */
  public void handleCloseButtonClick(ActionEvent event) {
    Node actualStage = (Node) event.getSource();
    Stage stage = (Stage) actualStage.getScene().getWindow();
    stage.close();
  }
}