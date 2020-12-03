package de.hhn.it.pp.javafx.controllers.spellingtrainer;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;

public class ChooseLearningSetController {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(ChooseLearningSetController.class);

  public Button startLearningButton;
  public Button homescreenButton;

  public StackPane scenePane;

  public ListView<String> learningsetListView;

  public void handleStartLearningButtonClick(ActionEvent event) throws IOException {
    loadPane(event);
    setScenePane("spellingtrainer/LearningPage");
  }

  public void handleHomescreenButtonClick(ActionEvent event) throws IOException {
    loadPane(event);
    setScenePane("SpellingTrainerService");
  }

  public void setScenePane(String url) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("/fxml/" + url + ".fxml"));
    scenePane.getChildren().clear();
    scenePane.getChildren().add(root);
  }

  public void loadPane(ActionEvent event) throws IOException {
    Node node = (Node) event.getSource();
    Scene scene = node.getScene();
    scenePane = (StackPane) scene.lookup("#scenePane");
  }
}
