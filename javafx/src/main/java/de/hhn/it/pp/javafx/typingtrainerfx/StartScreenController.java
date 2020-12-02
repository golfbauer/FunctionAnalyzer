package de.hhn.it.pp.javafx.typingtrainerfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

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

  public void btnClick_Start() {
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
