package de.hhn.it.pp.javafx.controllers.vocabletrainer;


import static de.hhn.it.pp.javafx.controllers.VocableTrainerServiceController.jbVocableTrainerService;
import de.hhn.it.pp.components.vocabletrainer.LearningState;

import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public class HomepageController implements Initializable {
  public static LearningState learningState = new LearningState();
  public static String cateSaver;
  public static int levenshtein;

  public AnchorPane scenePane;
  @FXML
  ListView<String> categoryListView;
  @FXML
  Label scoreButton;
  @FXML
  RadioButton easyRadio;
  @FXML
  RadioButton mediumRadio;
  @FXML
  RadioButton hardRadio;
  @FXML
  ToggleGroup levenRadio;


  public void initialize(URL location, ResourceBundle resources) {

    List<String> categories = jbVocableTrainerService.getVocCategories();
    for (int i = 0; categories.size() > i; i++) {
      categoryListView.getItems().add(jbVocableTrainerService.getVocCategories().get(i));
    }
    scoreButton.setText("Score: " + jbVocableTrainerService.getScore());
    categoryListView.getSelectionModel().select(0);
    mediumRadio.setSelected(true);
  }

  /**
   * Method to set the next pane.
   *
   * @param url reference to the next pane (.fxml file)
   * @throws IOException is thrown, when the referenced .fxml file does not exist
   */
  public void setScenePane(String url) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("/fxml/" + url + ".fxml"));
    scenePane.getChildren().clear();
    scenePane.getChildren().add(root);
  }

  /**
   * Method to load the current pane.
   *
   * @param event triggered by clicking a button, which switches the page
   * @throws IOException is thrown, when the referenced .fxml file does not exist
   */
  public void loadPane(ActionEvent event) {
    Node node = (Node) event.getSource();
    Scene scene = node.getScene();
    scenePane = (AnchorPane) scene.lookup("#scenePane");
  }

  public void newCategory(ActionEvent event) throws IOException {
    loadPane(event);
    setScenePane("/vocabletrainer/EditCreateCategoryPage");
  }

  public void editCategory(ActionEvent event) throws IOException {
    loadPane(event);
    setScenePane("/vocabletrainer/EditCreateCategoryPage");
  }

  public void deleteCategory(ActionEvent event) throws VocCategoryNotFoundException {
    try {
      jbVocableTrainerService
          .removeVocCategory(categoryListView.getSelectionModel().getSelectedItem());
    } catch (VocCategoryNotFoundException e) {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Warning Dialog");
      alert.setHeaderText("No Categories");
      alert.setContentText("Create new a Category");
      alert.showAndWait();
    }

    categoryListView.getItems().remove(categoryListView.getSelectionModel().getSelectedItem());
    categoryListView.refresh();
  }

  public void okClick(ActionEvent event) throws IOException {
    cateSaver = categoryListView.getSelectionModel().getSelectedItem();
    loadPane(event);
    setScenePane("/vocabletrainer/VocabularyView");
  }

  public void levenSet() {
    if (easyRadio.isSelected()) {
      levenshtein = 3;
    }

    if (mediumRadio.isSelected()) {
      levenshtein = 2;
    }

    if (hardRadio.isSelected()) {
      levenshtein = 0;
    }


  }
}
