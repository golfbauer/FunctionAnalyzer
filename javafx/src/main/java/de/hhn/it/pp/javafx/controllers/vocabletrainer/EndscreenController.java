package de.hhn.it.pp.javafx.controllers.vocabletrainer;

import de.hhn.it.pp.javafx.controllers.VocableTrainerServiceController;
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
import javafx.scene.layout.AnchorPane;

public class EndscreenController implements Initializable {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(VocableTrainerServiceController.class);

  @FXML
  AnchorPane scenePane;

  public void learnIncorrectWords(ActionEvent event) throws IOException {
    loadPane(event);
    setScenePane("/vocabletrainer/Homepage");
  }

  public void repeatLearning(ActionEvent event) throws IOException {
    loadPane(event);
    setScenePane("/vocabletrainer/Homepage");
  }

  public void finishLearning(ActionEvent event) throws IOException {
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

  }
}
