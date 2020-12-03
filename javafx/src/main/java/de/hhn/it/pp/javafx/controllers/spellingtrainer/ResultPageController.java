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

  public void handleHomescreenButtonClick(ActionEvent event) throws IOException {
    loadPane(event);
    setScenePane("SpellingTrainerService");
  }

  public void handleContinueLearningButtonClick(ActionEvent event) throws IOException {
    loadPane(event);
    setScenePane("spellingtrainer/ChooseLearningSet");
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    try {
      pieChart.setData(FXCollections.observableArrayList(
          new PieChart.Data("Right", service.getDescriptor().getCounter("right")),
          new PieChart.Data("Wrong", service.getDescriptor().getCounter("wrong"))
      ));
    } catch (NoWordException e) {
      e.printStackTrace();
    }
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
}
