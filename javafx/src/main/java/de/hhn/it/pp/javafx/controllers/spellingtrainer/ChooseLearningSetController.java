package de.hhn.it.pp.javafx.controllers.spellingtrainer;

import static de.hhn.it.pp.javafx.controllers.spellingtrainer.SpellingTrainerServiceController.service;

import de.hhn.it.pp.components.spellingtrainer.Provider.LearningSet;
import de.hhn.it.pp.components.spellingtrainer.exceptions.LearningSetCouldNotBeFoundException;
import de.hhn.it.pp.components.spellingtrainer.exceptions.NoWordException;
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

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    ArrayList<LearningSet> learningSets = service.getLearningSets();
    for (LearningSet learningSet : learningSets) {
      learningsetListView.getItems().add(learningSet.getLearningSetName());
    }
  }
}
