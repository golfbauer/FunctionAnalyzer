package de.hhn.it.pp.javafx.controllers.spellingtrainer;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class SpellingTrainerServiceController {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SpellingTrainerServiceController.class);

  public Button startButton;
  public StackPane scenePane;

  public void handleStartButtonClick() throws IOException {
    setScenePane("spellingtrainer/ChooseLearningSet");
  }

  public void setScenePane(String url) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("/fxml/" + url + ".fxml"));
    scenePane.getChildren().clear();
    scenePane.getChildren().add(root);
  }
}
