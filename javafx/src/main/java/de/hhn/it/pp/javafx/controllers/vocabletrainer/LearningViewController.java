package de.hhn.it.pp.javafx.controllers.vocabletrainer;

import static de.hhn.it.pp.javafx.controllers.VocableTrainerServiceController.jbVocableTrainerService;
import static de.hhn.it.pp.javafx.controllers.vocabletrainer.HomepageController.cateSaver;
import static de.hhn.it.pp.javafx.controllers.vocabletrainer.HomepageController.levenshtein;
import static de.hhn.it.pp.javafx.controllers.vocabletrainer.VocabularyViewController.toLearnList;
import static de.hhn.it.pp.javafx.controllers.vocabletrainer.VocabularyViewController.vocEdit;


import de.hhn.it.pp.components.vocabletrainer.Vocable;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryNotFoundException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocableNotFoundException;
import de.hhn.it.pp.javafx.controllers.VocableTrainerServiceController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LearningViewController implements Initializable {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(VocableTrainerServiceController.class);
  public static List<Vocable> skippedAndFailed = new ArrayList<>();
  public static int vocPosInCategory;
  @FXML
  AnchorPane scenePane;
  @FXML
  Label learningWordLabel;
  @FXML
  TextField textFieldInput;
  @FXML
  Label scoreLabel;

  private boolean isAtEndOfLearning() {
    vocPosInCategory++;
    int categorySize = 0;
    try {
      categorySize = jbVocableTrainerService.getVocabulary(cateSaver).size();
    } catch (VocCategoryNotFoundException e) {
      e.printStackTrace();
    }
    if (vocPosInCategory >= categorySize) {
      // End of category - Set vocPosInCategory to 0
      vocPosInCategory = 0;
      return true;
    } else {
      return false;
    }
  }

  public void skipVocable(ActionEvent event) throws IOException {
    if(!isAtEndOfLearning()){
      // ToDo Add skipped vocable to list of false words
      initialize(null, null);
    }else {
      loadScene(event, "/vocabletrainer/Endscreen");
    }
  }

  public void checkVocable(ActionEvent event) throws IOException {
    // Get vocable
    Vocable vocable = null;
    try {
      vocable = jbVocableTrainerService.getVocable(vocPosInCategory, cateSaver);
    } catch (VocCategoryNotFoundException e) {
      vocCategoryNotFoundAlert();
      return;
    } catch (VocableNotFoundException e) {
      vocableNotFoundAlert();
      return;
    }
    // Get word from textField
    String wordToCompare = textFieldInput.getText().trim();
    boolean isCorrect = false;
    try {
      // Check vocable correctness
      isCorrect = jbVocableTrainerService
          .checkVocable(wordToCompare, vocPosInCategory, cateSaver, levenshtein);
    } catch (VocCategoryNotFoundException e) {
      vocCategoryNotFoundAlert();
      return;
    } catch (VocableNotFoundException e) {
      vocableNotFoundAlert();
      return;
    }
    if (isCorrect) {
      // Vocable is correct
      // Show correct word to compare if levenshtein was used

    } else {
      // Vocable is false
      // Add vocable to list of false words
    }
    if(isAtEndOfLearning()){
      loadScene(event, "/vocabletrainer/Endscreen");
    }
  }

  private void vocableNotFoundAlert() {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Warning Dialog");
    alert.setHeaderText("Vocable not found");
    alert.setContentText("Please cancel");
    alert.showAndWait();
  }

  private void vocCategoryNotFoundAlert() {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Warning Dialog");
    alert.setHeaderText("Category not found");
    alert.setContentText("Please cancel");
    alert.showAndWait();
  }

  private void canNotSkipAlert() {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Warning Dialog");
    alert.setHeaderText("Can not skip");
    alert.setContentText("This is the last word");
    alert.showAndWait();
  }

  private void loadScene(ActionEvent event, String sceneUrl) throws IOException {
    loadPane(event);
    setScenePane(sceneUrl);
  }

  public void cancel(ActionEvent event) throws IOException {
    if (jbVocableTrainerService.getVocCategories().contains("SkippedAndFailed")) {
      try {
        jbVocableTrainerService.removeVocCategory("SkippedAndFailed");
      } catch (VocCategoryNotFoundException e) {
        e.printStackTrace();
      }
    }
    cateSaver = null;
    vocEdit = null;
    vocPosInCategory = 0;
    loadScene(event, "/vocabletrainer/Endscreen");
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
    String learnWord = "There is no learning word";
    try {
      learnWord = toLearnList.get(vocPosInCategory).getLearningWord();
    } catch (IndexOutOfBoundsException e) {
      canNotSkipAlert();
      return;
    }
    learningWordLabel.setText("What means " + learnWord);
    scoreLabel.setText("Score: " + jbVocableTrainerService.getScore());
  }



  /*
  public void initialize() {
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
  */
}
