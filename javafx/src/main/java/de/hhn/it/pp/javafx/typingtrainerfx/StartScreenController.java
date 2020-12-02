package de.hhn.it.pp.javafx.typingtrainerfx;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
  ObservableList<String> listView_TextSelection;

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

    //Init list_TextSelection
    ArrayList<File> files = findTxtPracticeFiles();
    String[] availablePracticeFiles = new String[files.size()];

    for (int i = 0; i < availablePracticeFiles.length; i++) {
      String f = files.get(i).getName();
      availablePracticeFiles[i] = f;
    }

    listView_TextSelection = FXCollections
        .observableArrayList(availablePracticeFiles[0], availablePracticeFiles[1],
            availablePracticeFiles[2]);
    list_TextSelection.setItems(listView_TextSelection);
  }

  /**
   * Finds all .txt files in resources Folder to fill in the list in Startscreen
   *
   * @return
   */
  private ArrayList<File> findTxtPracticeFiles() {

    File folder = new File("components/src/main/resources");
    File[] listOfFiles = folder.listFiles();

    ArrayList<File> files = new ArrayList<>();

    for (File file : listOfFiles) {
      if (file.isFile() && file.getName().endsWith(".txt")) {
        files.add(file);
      }
    }

    return files;
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
