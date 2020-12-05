package de.hhn.it.pp.javafx.controllers.vocabletrainer;

import static de.hhn.it.pp.javafx.controllers.VocableTrainerServiceController.jbVocableTrainerService;
import static de.hhn.it.pp.javafx.controllers.vocabletrainer.HomepageController.cateSaver;

import de.hhn.it.pp.components.vocabletrainer.Vocable;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryNotFoundException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocableNotFoundException;
import de.hhn.it.pp.javafx.controllers.vocabletrainer.HomepageController;
import de.hhn.it.pp.javafx.controllers.VocableTrainerServiceController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.layout.AnchorPane;

public class VocabularyViewController implements Initializable {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(VocabularyViewController.class);
  public static Vocable vocEdit;
  public static int vocInt;
  public static List<Vocable> toLearnList = new ArrayList<>();
  @FXML
  AnchorPane scenePane;
  @FXML
  ListView<Vocable> vocableListView;
  @FXML
  Label cateLabel;
  @FXML
  Label scoreVocList;


  public void addVocable(ActionEvent event) throws IOException {
    logger.debug("Add button is pressed.");
    loadPane(event);
    setScenePane("/vocabletrainer/EditVocablePage");
  }

  public void editVocable(ActionEvent event) throws IOException {
    logger.debug("Edit button is pressed.");
    if (vocableListView.getSelectionModel().getSelectedItem() != null) {
      vocEdit = vocableListView.getSelectionModel().getSelectedItem();
      vocInt = vocableListView.getSelectionModel().getSelectedIndex();
      loadPane(event);
      setScenePane("/vocabletrainer/EditVocablePage");
    } else {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Information Dialog");
      alert.setHeaderText(null);
      alert.setContentText("Select Vocable First!");

      alert.showAndWait();
    }
  }

  public void removeVocable(ActionEvent event)
      throws VocCategoryNotFoundException, VocableNotFoundException {
    logger.debug("Remove button is pressed.");
    if (vocableListView.getSelectionModel().getSelectedItem() != null) {
      jbVocableTrainerService
          .removeVocable(vocableListView.getSelectionModel().getSelectedIndex(),
              cateSaver);
      vocableListView.getItems().clear();
      listLoader();
    } else {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Information Dialog");
      alert.setHeaderText(null);
      alert.setContentText("Select Vocable to delete first!");

      alert.showAndWait();
    }

  }

  public void learnCategory(ActionEvent event) throws IOException, VocCategoryNotFoundException {
    logger.debug("learn button is pressed.");
    toLearnList.addAll(jbVocableTrainerService.getVocabulary(cateSaver));
    loadPane(event);
    setScenePane("/vocabletrainer/LearningView");
  }

  public void back(ActionEvent event) throws IOException {
    logger.debug("back button is pressed.");
    toLearnList.clear();
    loadPane(event);
    setScenePane("/vocabletrainer/Homepage");
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
  public void loadPane(ActionEvent event) throws IOException {
    Node node = (Node) event.getSource();
    Scene scene = node.getScene();
    scenePane = (AnchorPane) scene.lookup("#scenePane");
  }

  /**
   * Called to initialize a controller after its root element has been
   * completely processed.
   *
   * @param location  The location used to resolve relative paths for the root object, or
   *                  {@code null} if the location is not known.
   * @param resources The resources used to localize the root object, or {@code null} if
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    try {
      listLoader();
    } catch (VocCategoryNotFoundException e) {
      e.printStackTrace();
    }
    if (jbVocableTrainerService.getVocCategories().contains("SkippedAndFailed")) {
      try {
        jbVocableTrainerService.removeVocCategory("SkippedAndFailed");
      } catch (VocCategoryNotFoundException e) {
        e.printStackTrace();
      }
    }
    cateLabel.setText("Category: " + cateSaver);

  }

  public void listLoader() throws VocCategoryNotFoundException {
    for (int i = 0; i
        < jbVocableTrainerService.getVocabulary(cateSaver)
        .size(); i++) {
      vocableListView.getItems().add(
          jbVocableTrainerService.getVocabulary(cateSaver)
              .get(i));
      scoreVocList.setText("Scroe: " + jbVocableTrainerService.getScore());
    }
  }
}
