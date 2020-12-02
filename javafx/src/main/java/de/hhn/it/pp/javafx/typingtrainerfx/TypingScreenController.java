package de.hhn.it.pp.javafx.typingtrainerfx;

import de.hhn.it.pp.components.typingtrainer.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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

  public void btnClick_Exit(ActionEvent event) throws IOException {

    Parent typingScreenParent = FXMLLoader.load(getClass().getResource("/fxml/typingtrainer/StartScreen.fxml"));
    Scene typingScreenScene = new Scene(typingScreenParent);

    //Stage Info
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

    window.setScene(typingScreenScene);
    window.show();

    //wurde ein text ausgewählt
    System.out.println("Wechselt zu startscreen");
  }
}
