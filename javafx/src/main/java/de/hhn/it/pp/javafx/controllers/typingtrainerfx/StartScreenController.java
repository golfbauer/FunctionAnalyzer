package de.hhn.it.pp.javafx.controllers.typingtrainerfx;

import de.hhn.it.pp.components.typingtrainer.SaveData;
import de.hhn.it.pp.components.typingtrainer.SaveLoad;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/***
 * @author Tobias Maraci, Robert Pistea
 * @version 1.0
 * @since 1.1
 */
public class StartScreenController implements Initializable {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(StartScreenController.class);

  //Startscreen Main
  @FXML
  Button btn_Start;
  @FXML
  Button btn_Highscore;
  @FXML
  Button btn_Quit;
  @FXML
  Label lbl_SelectAText;
  private String selectedText = "";

  @FXML
  ListView<String> list_TextSelection;
  ObservableList<String> listView_TextSelection;

  //Highscore Panel
  @FXML
  Pane pane_Highscores;

  @FXML
  TableView<SaveData> tv_Highscores; //DataStructure für gespeichertes stattdessen rein
  @FXML
  TableColumn<SaveData, String> col_text;
  @FXML
  TableColumn<SaveData, String> col_time;
  @FXML
  TableColumn<SaveData, String> col_wpm;

  @FXML
  Button btn_Close;


  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    pane_Highscores.setVisible(false);
    lbl_SelectAText.setText("Please select a text from the list.");

    //region Init TextSelection
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
    list_TextSelection.getSelectionModel().selectedItemProperty().addListener(
        new ChangeListener<String>() {
          @Override
          public void changed(ObservableValue<? extends String> observableValue, String oldValue,
                              String newValue) {
            clickOnItem(newValue);
          }
        });
    //endregion

    logger.debug("StartScreen initialized");
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

    logger.debug("Practice files found.");

    return files;
  }

  //region Button methods

  /**
   * creates descriptor in typingscreencontroller
   * changes scene
   * sets practiceText to be used in typingScreen
   * @param event
   * @throws IOException
   */
  public void btnClick_Start(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/fxml/typingtrainer/TypingScreen.fxml"));
    Parent tableViewParent = loader.load();

    Scene tableViewScene = new Scene(tableViewParent);

    TypingScreenController controller = loader.getController();
    controller.initData(selectedText);

    Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

    window.setScene(tableViewScene);
    window.show();

    logger.debug("Start button clicked.");
  }

  /**
   * Reads all data from highscores.txt and adds it to the corresponding cell
   */
  public void btnClick_Highscore() {
    SaveLoad load = new SaveLoad();
    String data = load.load();

    String[] datas = data.split(" ");

    //Debug
    for (int i = 0; i < datas.length; i++) {
      System.out.println(datas[i]);
    }


    //setup
      col_text.setCellValueFactory(new PropertyValueFactory<SaveData, String>("text"));
      col_time.setCellValueFactory(new PropertyValueFactory<SaveData, String>("time"));
      col_wpm.setCellValueFactory(new PropertyValueFactory<SaveData, String>("wpm"));

    //load data
    tv_Highscores.setItems(getDatas(datas));

    System.out.println(data);

    pane_Highscores.setVisible(true);

    logger.debug("Highscore button clicked.");
  }

  /**
   * Converts String[] into OvervableList with type SaveData.
   * E.G. Data from txtfile was converted into a String[] but needs to be a ObservableList to put it in the UI
   * @param datas String[] data to convert
   * @return Observable list with all the data from datas
   */
  private ObservableList<SaveData> getDatas(String[] datas)
  {
    ObservableList<SaveData> rows = FXCollections.observableArrayList();

    for (int i = 0; i < datas.length; i+=3) {
      rows.add(new SaveData(datas[i], datas[i+1], datas[i+2]));
    }

    logger.debug("Save data converted for highscore list.");

    return rows;
  }

  /**
   * Closes the GUI
   */
  public void btnClick_Quit() {

    logger.debug("Close Typing Trainer.");
    System.exit(98);
  }

  public void btnClick_Close() {
    pane_Highscores.setVisible(false);
  }
  //endregion

  /**
   * Handles onClick Event at items in list
   * Enables btn_Start and changes lbl_SelectAText to the selected text
   * @param item -> name
   */
  public void clickOnItem(String item) {
    lbl_SelectAText.setText(item);
    selectedText = item;

    logger.debug("Practice text selected.");

    btn_Start.setDisable(false);
  }
}
