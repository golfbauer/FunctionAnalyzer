package de.hhn.it.pp.javafx.controllers.vocabletrainer;


import de.hhn.it.pp.components.vocabletrainer.LearningState;
import de.hhn.it.pp.components.vocabletrainer.provider.JbVocableTrainerService;
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
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class HomepageController implements Initializable {
  JbVocableTrainerService jbVocableTrainerService = new JbVocableTrainerService();
  LearningState learningState = new LearningState();

  public AnchorPane scenePane;
  @FXML
  ListView<String> categoryListView;


  public void initialize(URL location, ResourceBundle resources) {
    List<String> categories = jbVocableTrainerService.getVocCategories();
    for (int i = 0; categories.size() > i; i++) {
      categoryListView.getItems().add(jbVocableTrainerService.getVocCategories().get(i));
    }
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

  public void newCategory(ActionEvent event) throws IOException {
    loadPane(event);
    setScenePane("/vocabletrainer/EditCreateCategoryPage");
  }
}
