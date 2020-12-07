package de.hhn.it.pp.javafx.controllers.vocabletrainer;

import static de.hhn.it.pp.javafx.controllers.VocableTrainerServiceController.jbVocableTrainerService;
import static de.hhn.it.pp.javafx.controllers.vocabletrainer.HomepageController.cateSaver;
import static de.hhn.it.pp.javafx.controllers.vocabletrainer.HomepageController.levenshtein;
import static de.hhn.it.pp.javafx.controllers.vocabletrainer.VocabularyViewController.vocEdit;


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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
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
  @FXML
  Label successFail;
  @FXML
  Label displayCorrectWord;
  @FXML
  Button skipButton;
  @FXML
  Button okButton;
  private boolean notificationState;

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
    skippedAndFailed.add(vocable);
    if (!isAtEndOfLearning()) {
      initialize(null, null);
    } else {
      loadScene(event, "/vocabletrainer/Endscreen");
    }
  }

  public void checkVocable(ActionEvent event) throws IOException {
    if (notificationState) {
      if (isAtEndOfLearning()) {
        loadScene(event, "/vocabletrainer/Endscreen");
      } else {
        initialize(null, null);
      }
      return;
    }
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
    notificationState = true;
    if (isCorrect) {
      // Vocable is correct
      successFail.setText("The word is correct after the Levenshtein distance!");
    } else {
      successFail.setText("The word is false!");
      // Vocable is false - Add vocable to list of false words
      skippedAndFailed.add(vocable);
    }
    displayCorrectWord.setText("The correct word: " + vocable);
    skipButton.setDisable(true);
    textFieldInput.setEditable(false);
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

  private void endOfListAlert() {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Warning Dialog");
    alert.setHeaderText("End of List reached");
    alert.setContentText("This is the last word");
    alert.showAndWait();
  }

  private void loadScene(ActionEvent event, String sceneUrl) throws IOException {
    loadPane(event);
    setScenePane(sceneUrl);
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
    okButton.setDefaultButton(true);
    okButton.setOnKeyPressed(event -> {
          if (event.getCode().equals(KeyCode.ENTER)) {
            okButton.fire();
          }
        }
    );
    String learnWord = "There is no learning word";
    try {
      learnWord = jbVocableTrainerService.getVocable(vocPosInCategory, cateSaver).getLearningWord();
    } catch (IndexOutOfBoundsException e) {
      endOfListAlert();
      return;
    } catch (VocCategoryNotFoundException e) {
      vocCategoryNotFoundAlert();
      return;
    } catch (VocableNotFoundException e) {
      vocableNotFoundAlert();
      return;
    }
    textFieldInput.setText("");
    learningWordLabel.setText("What means " + learnWord + "?");
    scoreLabel.setText("Score: " + jbVocableTrainerService.getScore());
    successFail.setText("");
    displayCorrectWord.setText("");
    skipButton.setDisable(false);
    textFieldInput.setEditable(true);
    notificationState = false;
  }
}
