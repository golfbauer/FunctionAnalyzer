package de.hhn.it.pp.javafx.controllers.vocabletrainer;

import static de.hhn.it.pp.javafx.controllers.vocabletrainer.HomepageController.cateSaver;
import static de.hhn.it.pp.javafx.controllers.vocabletrainer.HomepageController.levenshtein;
import static de.hhn.it.pp.javafx.controllers.vocabletrainer.LearningViewController.vocPosInCategory;
import static de.hhn.it.pp.javafx.controllers.vocabletrainer.LearningViewController.skippedAndFailed;
import static de.hhn.it.pp.javafx.controllers.vocabletrainer.VocabularyViewController.toLearnList;
import static de.hhn.it.pp.javafx.controllers.vocabletrainer.VocabularyViewController.vocEdit;
import static de.hhn.it.pp.javafx.controllers.VocableTrainerServiceController.jbVocableTrainerService;

import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryAlreadyExistException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryNotFoundException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class EndscreenController implements Initializable {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(EndscreenController.class);

  @FXML
  AnchorPane scenePane;
  @FXML
  Label endScore;
  @FXML
  Label displayCorrectWords;
  @FXML
  Label displayWrongWords;

  public void learnIncorrectWords(ActionEvent event)
      throws IOException, VocCategoryAlreadyExistException {
    logger.debug("learnIncorrectWords button is pressed.");
    if (skippedAndFailed.size() != 0) {
      jbVocableTrainerService.addVocCategory("SkippedAndFailed", skippedAndFailed);
      cateSaver = "SkippedAndFailed";
      loadPane(event);
      setScenePane("/vocabletrainer/LearningViewController");
    } else {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Information Dialog");
      alert.setHeaderText(null);
      alert.setContentText("No IncorrectWords!");

      alert.showAndWait();
    }
  }

  public void repeatLearning(ActionEvent event) throws IOException, VocCategoryNotFoundException {
    logger.debug("Repeat button is pressed.");
    skippedAndFailed.clear();
    toLearnList.clear();
    toLearnList.addAll(jbVocableTrainerService.getVocabulary(cateSaver));
    vocPosInCategory = 0;
    loadPane(event);
    setScenePane("/vocabletrainer/LearningViewController");
  }

  public void finishLearning(ActionEvent event) throws IOException {
    logger.debug("Finish button is pressed.");
    skippedAndFailed.clear();
    cateSaver = null;
    toLearnList.clear();
    vocPosInCategory = 0;
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
    endScore.setText("Score: " + jbVocableTrainerService.getScore());
    int correctAmount = 0;
    try {
      correctAmount =
          jbVocableTrainerService.getVocabulary(cateSaver).size() - skippedAndFailed.size();
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
    displayCorrectWords.setText("Correct words: " + correctAmount);
    displayWrongWords.setText("Incorrect words: " + skippedAndFailed.size());
  }
}
