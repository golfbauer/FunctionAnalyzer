package de.hhn.it.pp.javafx.controllers.spellingtrainer;

import de.hhn.it.pp.components.spellingtrainer.Provider.SgdsSpellingTrainerService;
import de.hhn.it.pp.components.spellingtrainer.exceptions.LearningSetCouldNotBeFoundException;
import de.hhn.it.pp.components.spellingtrainer.exceptions.LearningSetNameAlreadyAssignedException;
import de.hhn.it.pp.components.spellingtrainer.exceptions.WordAlreadyAddedException;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class SpellingTrainerServiceController {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SpellingTrainerServiceController.class);
  public static SgdsSpellingTrainerService service;
  public Button startButton;
  public StackPane scenePane;

  public SpellingTrainerServiceController()
      throws LearningSetNameAlreadyAssignedException, LearningSetCouldNotBeFoundException,
      WordAlreadyAddedException {
    service = new SgdsSpellingTrainerService();
    service.registerMediaPresentationListener(new AudioPresentationListener());
  }

  /**
   * Method to handle the event triggered by clicking on the 'Start learning' button.
   *
   * @throws IOException                             is thrown, when the referenced .fxml file does not exist
   * @throws LearningSetNameAlreadyAssignedException is thrown, when the learning set name is already assigned
   * @throws LearningSetCouldNotBeFoundException     is thrown, when the learning set could not be found
   * @throws WordAlreadyAddedException               is thrown, when the word is already added in the learning set
   */
  public void handleStartButtonClick() throws IOException {
    setScenePane("spellingtrainer/ChooseLearningSet");
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
}