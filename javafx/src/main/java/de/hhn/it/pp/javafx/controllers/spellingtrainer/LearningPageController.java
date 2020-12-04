package de.hhn.it.pp.javafx.controllers.spellingtrainer;

import static de.hhn.it.pp.javafx.controllers.spellingtrainer.SpellingTrainerServiceController.service;

import de.hhn.it.pp.components.spellingtrainer.exceptions.NoWordException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LearningPageController implements Initializable {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(LearningPageController.class);

  public Button playAgainButton;
  public Button checkSpellingButton;
  public Button endLearningButton;
  public Button showAnswerButton;
  public Button continueButton;

  public ProgressBar progressBar;

  public Label remainingWordsLabel;
  public Label rightWordsLabel;
  public Label wrongWordsLabel;

  public TextField solutionTextField;
  public StackPane scenePane;
  private int tries;

  public void handlePlayAgainButtonClick() {
  }

  public void handlePressingEnterInTextField(KeyEvent event) throws NoWordException {
    if(event.getCode().equals(KeyCode.ENTER)){
      handleCheckSpellingButtonClick();
    }
  }

  /**
   * Method to handle the event by clicking on the 'Check spelling' button.
   *
   * @throws NoWordException is thrown, when the parameter of the method updateCounter is not equal to right,wrong or remaining
   */
  public void handleCheckSpellingButtonClick() throws NoWordException {
    String answer = solutionTextField.getText();
    boolean isTrue = service.checkSpelling(answer);
    if (isTrue) {
      if (tries == 1) {
        service.getDescriptor().updateCounter("right", 1);
        rightWordsLabel
            .setText("Words spelled right: " + service.getDescriptor().getCounter("right"));
        service.getDescriptor().updateCounter("remaining", -1);
        remainingWordsLabel
            .setText("Remaining words: " + service.getDescriptor().getCounter("remaining"));
      }
      solutionTextField.setStyle("-fx-background-color: #09e909");
    } else {
      if (tries == 1) {
        service.getDescriptor().updateCounter("wrong", 1);
        wrongWordsLabel
            .setText("Words spelled wrong: " + service.getDescriptor().getCounter("wrong"));
        service.getDescriptor().updateCounter("remaining", -1);
        remainingWordsLabel
            .setText("Remaining words: " + service.getDescriptor().getCounter("remaining"));
      }
      solutionTextField.setStyle("-fx-background-color: #ff0a0a");
    }
    tries++;
  }

  /**
   * Method to handle the even triggered by clicking on the 'End learning' button
   *
   * @param event triggered by clicking on the 'End learning' button
   * @throws IOException is thrown, when the referenced .fxml file does not exist
   */
  public void handleEndLearningButtonClick(ActionEvent event) throws IOException {
    loadPane(event);
    setScenePane("SpellingTrainerService");
    service.endLearning();
  }

  /**
   * Method to handle the event triggered by clicking on the 'Show answer' button
   *
   * @param event event triggered by clicking on the 'Show answer' button
   * @throws IOException     is thrown, when the referenced .fxml file does not exist
   * @throws NoWordException is thrown, when the parameter of the method updateCounter is not equal to right,wrong or remaining
   */
  public void handleShowAnswerButtonClick(ActionEvent event) throws IOException, NoWordException {
    Stage popUpWindow = new Stage();
    popUpWindow.initModality(Modality.APPLICATION_MODAL);
    popUpWindow.setTitle("AnswerPopUpPage");
    Parent root =
        FXMLLoader.load(getClass().getResource("/fxml/spellingtrainer/PopUpPage.fxml"));
    popUpWindow.setScene(new Scene(root));
    Scene scene = popUpWindow.getScene();
    Label label = (Label) scene.lookup("#popUpTextLabel");
    label.setText("The correct answer is: " + service.currentWord());
    popUpWindow.show();
    if (tries == 1) {
      tries++;
      service.getDescriptor().updateCounter("remaining", -1);
      remainingWordsLabel
          .setText("Remaining words: " + service.getDescriptor().getCounter("remaining"));
    }
  }

  /**
   * Method to handle the event triggered by clicking on the 'Continue' button
   *
   * @param event event triggered by clicking on the 'Continue' button
   * @throws IOException     is thrown, when the referenced .fxml file does not exist
   * @throws NoWordException is thrown, when the parameter of the method updateCounter is not equal to right,wrong or remaining
   */
  public void handleContinueButtonClick(ActionEvent event) throws IOException, NoWordException {
    if (tries == 1) {
      handleCheckSpellingButtonClick();
    }
    if (service.hasNextWord()) {
      loadPane(event);
      setScenePane("spellingtrainer/LearningPage");
    } else {
      service.endLearning();
      loadPane(event);
      setScenePane("spellingtrainer/ResultPage");
    }
    tries = 1;
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
    scenePane = (StackPane) scene.lookup("#scenePane");
  }

  /**
   * Called to initialize a controller after its root element has been
   * completely processed.
   *
   * @param location  The location used to resolve relative paths for the root object, or
   *                  <tt>null</tt> if the location is not known.
   * @param resources The resources used to localize the root object, or <tt>null</tt> if
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    try {
      wrongWordsLabel.setText(
          "Words spelled wrong: " + String.valueOf(service.getDescriptor().getCounter("wrong")));
      rightWordsLabel.setText(
          "Words spelled right: " + String.valueOf(service.getDescriptor().getCounter("right")));
      remainingWordsLabel.setText(
          "Remaining words: " + String.valueOf(service.getDescriptor().getCounter("remaining")));
    } catch (NoWordException e) {
      e.printStackTrace();
    }
    tries = 1;
    progressBar.setProgress(0.0);
  }
}