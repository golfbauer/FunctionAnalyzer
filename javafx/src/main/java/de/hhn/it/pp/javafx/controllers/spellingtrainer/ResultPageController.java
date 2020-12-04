package de.hhn.it.pp.javafx.controllers.spellingtrainer;

import static de.hhn.it.pp.javafx.controllers.spellingtrainer.SpellingTrainerServiceController.service;

import de.hhn.it.pp.components.spellingtrainer.exceptions.NoWordException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class ResultPageController implements Initializable {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(ResultPageController.class);

  public PieChart pieChart;

  public Button homescreenButton;
  public Button continueLearningButton;

  StackPane scenePane;

  /**
   * Method to handle the event triggered by clicking on the 'Homescreen' button.
   *
   * @param event event triggered by clicking on the 'Homescreen' button.
   * @throws IOException is thrown, when the referenced .fxml file does not exist
   */
  public void handleHomescreenButtonClick(ActionEvent event) throws IOException {
    loadPane(event);
    setScenePane("SpellingTrainerService");
  }

  /**
   * Method to handle the event triggered by clicking on the 'Continue learning' button.
   *
   * @param event event triggered by clicking on the 'Continue learning' button
   * @throws IOException is thrown, when the referenced .fxml file does not exist
   */
  public void handleContinueLearningButtonClick(ActionEvent event) throws IOException {
    loadPane(event);
    setScenePane("spellingtrainer/ChooseLearningSet");
  }

  /**
   * Called to initialize a controller after its root element has been
   * completely processed.
   *
   * @param url            The location used to resolve relative paths for the root object, or
   *                       <tt>null</tt> if the location is not known.
   * @param resourceBundle The resources used to localize the root object, or <tt>null</tt> if
   */
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    try {
      int right = service.getDescriptor().getCounter("right");
      int wrong = service.getDescriptor().getCounter("wrong");

      pieChart.setData(FXCollections.observableArrayList(
          new PieChart.Data("Right", right),
          new PieChart.Data("Wrong", wrong)
      ));
    } catch (NoWordException e) {
      e.printStackTrace();
    }
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
}
