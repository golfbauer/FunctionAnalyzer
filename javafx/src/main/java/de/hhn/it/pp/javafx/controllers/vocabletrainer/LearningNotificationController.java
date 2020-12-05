package de.hhn.it.pp.javafx.controllers.vocabletrainer;

import static de.hhn.it.pp.javafx.controllers.VocableTrainerServiceController.jbVocableTrainerService;
import static de.hhn.it.pp.javafx.controllers.vocabletrainer.HomepageController.cateSaver;
import static de.hhn.it.pp.javafx.controllers.vocabletrainer.LearningViewController.checker;
import static de.hhn.it.pp.javafx.controllers.vocabletrainer.LearningViewController.vocPosInCategory;
import static de.hhn.it.pp.javafx.controllers.vocabletrainer.LearningViewController.userText;
import static de.hhn.it.pp.javafx.controllers.vocabletrainer.VocabularyViewController.vocEdit;

import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryNotFoundException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocableNotFoundException;
import de.hhn.it.pp.javafx.controllers.VocableTrainerServiceController;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
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

public class LearningNotificationController implements Initializable {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(VocableTrainerServiceController.class);

  @FXML
  AnchorPane scenePane;
  @FXML
  Label labelAsk;
  @FXML
  Label scoreNotify;
  @FXML
  Label successFail;
  @FXML
  TextField oldInput;

  public void nextVocable(ActionEvent event) throws IOException {
    loadPane(event);
    setScenePane("/vocabletrainer/LearningViewController");
  }

  public void cancel(ActionEvent event) throws IOException {
    loadPane(event);
    setScenePane("/vocabletrainer/EndscreenController");
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
      labelAsk.setText("What means"
          + jbVocableTrainerService.getVocable(vocPosInCategory, cateSaver).getLearningWord());
    } catch (VocableNotFoundException | VocCategoryNotFoundException e) {
      e.printStackTrace();
    }
    scoreNotify.setText("Score: " + jbVocableTrainerService.getScore());
    oldInput.setText(userText);
    if (checker) {
      successFail.setText("Success!!!");
    } else {
      try {
        if (jbVocableTrainerService.getVocable(vocPosInCategory, cateSaver).getTranslations().length
            == 1) {
          successFail.setText("Failed you misspelled. The correct answer is"
              + Arrays.toString(
                  jbVocableTrainerService.getVocable(vocPosInCategory, cateSaver).getTranslations()));
        } else {
          successFail.setText("Failed you misspelled. Right options are"
              + Arrays.toString(
              jbVocableTrainerService.getVocable(vocPosInCategory, cateSaver).getTranslations()));
        }
      } catch (VocableNotFoundException | VocCategoryNotFoundException e) {
        e.printStackTrace();
      }
    }
  }
}
