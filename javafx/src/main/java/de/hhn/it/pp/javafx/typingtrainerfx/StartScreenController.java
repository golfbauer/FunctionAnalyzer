package de.hhn.it.pp.javafx.typingtrainerfx;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartScreenController implements Initializable {

  @FXML
  Label lbl_Title;

  @FXML
  Button btn_Start;


  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) { //Wenn startet -> ändert sachen und so
    lbl_Title.setText("penis");
  }

  public void buttonClick()
  {
    System.out.println("asdasd");
  }
}
