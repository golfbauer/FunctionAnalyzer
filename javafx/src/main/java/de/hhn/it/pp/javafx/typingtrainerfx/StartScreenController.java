package de.hhn.it.pp.javafx.typingtrainerfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StartScreenController implements Initializable {

  //Startscreen Main
  @FXML
  Button btn_Start;
  @FXML
  Button btn_Highscore;
  @FXML
  Button btn_Quit;
  @FXML
  Label lbl_SelectAText;

  @FXML
  ListView<String> list_TextSelection;

  //Highscore Panel
  @FXML
  Pane pane_Highscores;
  @FXML
  ListView<String> list_Highscores;
  @FXML
  Button btn_Close;


  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    pane_Highscores.setVisible(false);
    lbl_SelectAText.setText("Please select a text from the list.");
  }

  public void btnClick_Start(ActionEvent event) throws IOException {

    Parent typingScreenParent = FXMLLoader.load(getClass().getResource("/fxml/typingtrainer/TypingScreen.fxml"));
    Scene typingScreenScene = new Scene(typingScreenParent);

    //Stage Info
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

    window.setScene(typingScreenScene);
    window.show();

    //wurde ein text ausgewählt
    System.out.println("Wechselt zu typingscreen");
  }

  public void btnClick_Highscore() {
    pane_Highscores.setVisible(true);
  }

  public void btnClick_Quit() {
    System.exit(98);
  }

  public void btnClick_Close() {
    pane_Highscores.setVisible(false);
  }
}
