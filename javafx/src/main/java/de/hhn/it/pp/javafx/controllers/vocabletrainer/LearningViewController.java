package de.hhn.it.pp.javafx.controllers.vocabletrainer;

import static de.hhn.it.pp.javafx.controllers.vocabletrainer.HomepageController.cateSaver;
import static de.hhn.it.pp.javafx.controllers.vocabletrainer.HomepageController.levenshtein;
import static de.hhn.it.pp.javafx.controllers.vocabletrainer.VocabularyViewController.toLearnList;
import static de.hhn.it.pp.javafx.controllers.vocabletrainer.VocabularyViewController.vocEdit;
import static de.hhn.it.pp.javafx.controllers.VocableTrainerServiceController.jbVocableTrainerService;

import de.hhn.it.pp.components.vocabletrainer.Vocable;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryNotFoundException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocableNotFoundException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LearningViewController implements Initializable {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(VocableTrainerServiceController.class);
  public static List<Vocable> skippedAndFailed = new ArrayList<>();
  public static boolean checker;
  public static int listPosition;
  public static String userText;
  @FXML
  AnchorPane scenePane;
  @FXML
  Label learningWordLabel;
  @FXML
  TextField textFieldInput;
  @FXML
  Label scoreLabel;


  public void skipVocable(ActionEvent event) throws IOException {
    if (toLearnList.size() == 0) {
      loadPane(event);
      setScenePane("/vocabletrainer/EndscreenController");
    } else {
      skippedAndFailed.add(toLearnList.get(0));
      toLearnList.remove(0);
      checker = false;
      loadPane(event);
      setScenePane("/vocabletrainer/LearningViewController");
    }
  }

  public void checkVocable(ActionEvent event)
      throws IOException, VocCategoryNotFoundException, VocableNotFoundException {
    if (toLearnList.size() == 0) {
      loadPane(event);
      setScenePane("/vocabletrainer/EndscreenController");
    } else {
      for (listPosition = 0;
           listPosition <= jbVocableTrainerService.getVocabulary(cateSaver).size();
           listPosition++) {
        if (jbVocableTrainerService.getVocable(listPosition, cateSaver).getLearningWord()
            .equals(toLearnList.get(0).getLearningWord())) {
          checker =
              jbVocableTrainerService
                  .checkVocable(textFieldInput.getText(), listPosition, cateSaver, levenshtein);
          userText = textFieldInput.getText();
          if (checker) {
            toLearnList.remove(0);
            loadPane(event);
            setScenePane("/vocabletrainer/LearningNotificationController");
          } else {
            skippedAndFailed.add(toLearnList.get(0));
            toLearnList.remove(0);
            loadPane(event);
            setScenePane("/vocabletrainer/LearningNotificationController");
          }
        }
      }
    }
  }

  public void cancel(ActionEvent event) throws IOException {
    cateSaver = null;
    vocEdit = null;
    listPosition = 0;
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

    learningWordLabel.setText("What means " + toLearnList.get(0).getLearningWord());
    scoreLabel.setText("Score: " + jbVocableTrainerService.getScore());

  }
}
