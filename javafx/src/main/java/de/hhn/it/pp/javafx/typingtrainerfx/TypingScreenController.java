package de.hhn.it.pp.javafx.typingtrainerfx;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TypingScreenController implements Initializable {

  @FXML private Button btn_Exit;
  @FXML private Button btn_Retry;

  @FXML private Label lbl_Time;
  @FXML private Label lbl_practiceText;
  @FXML private Label lbl_typedText;
  @FXML private Label lbl_WPM;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

  }
}
