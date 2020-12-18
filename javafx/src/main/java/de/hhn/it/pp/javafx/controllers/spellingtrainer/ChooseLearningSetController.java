package de.hhn.it.pp.javafx.controllers.spellingtrainer;

import static de.hhn.it.pp.javafx.controllers.spellingtrainer.SpellingTrainerServiceController.service;

import de.hhn.it.pp.components.spellingtrainer.exceptions.LearningSetCouldNotBeFoundException;
import de.hhn.it.pp.components.spellingtrainer.exceptions.NoWordException;
import de.hhn.it.pp.components.spellingtrainer.provider.LearningSet;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ChooseLearningSetController implements Initializable {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(ChooseLearningSetController.class);

  public Button startLearningButton;
  public Button homescreenButton;
  public StackPane scenePane;
  public ListView<String> learningsetListView;

  /**
   * Method to handle the event when clicking on the 'Start Learning' button.
   *
   * @param event event triggered by a mouse click on the button 'Start Learning'
   * @throws IOException                         is thrown, when the referenced .fxml file does not exist
   * @throws LearningSetCouldNotBeFoundException is thrown, when the learning set could not be found
   * @throws NoWordException                     is thrown, when the parameter of the method updateCounter is not equal to right,wrong or remaining
   */
  public void handleStartLearningButtonClick(ActionEvent event)
      throws IOException, LearningSetCouldNotBeFoundException, NoWordException {

    String learningSet = learningsetListView.getSelectionModel().getSelectedItem();
    if (learningSet == null) {
      Stage popUpWindow = new Stage();
      popUpWindow.initModality(Modality.APPLICATION_MODAL);
      popUpWindow.setTitle("WarningPopUpPage");
      Parent root =
          FXMLLoader.load(getClass().getResource("/fxml/spellingtrainer/PopUpPage.fxml"));
      popUpWindow.setScene(new Scene(root));
      Scene scene = popUpWindow.getScene();
      Label label = (Label) scene.lookup("#popUpTextLabel");
      label.setText("Please choose an learning set!");
      popUpWindow.show();
    } else {
      service.startLearning(learningSet);
      loadPane(event);
      setScenePane("spellingtrainer/LearningPage");
    }
  }

  /**
   * Method to handle the event triggered by clicking on the 'Homescreen' button.
   *
   * @param event triggered by clicking on the 'Homescreen' button
   * @throws IOException is thrown, when the referenced .fxml file does not exist
   */
  public void handleHomescreenButtonClick(ActionEvent event) throws IOException {
    loadPane(event);
    setScenePane("SpellingTrainerService");
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
    ArrayList<LearningSet> learningSets = service.getLearningSets();
    for (LearningSet learningSet : learningSets) {
      learningsetListView.getItems().add(learningSet.getLearningSetName());
    }
  }
}