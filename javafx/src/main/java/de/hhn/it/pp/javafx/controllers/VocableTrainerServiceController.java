package de.hhn.it.pp.javafx.controllers;

import de.hhn.it.pp.components.vocabletrainer.LearningState;
import de.hhn.it.pp.components.vocabletrainer.Vocable;
import de.hhn.it.pp.components.vocabletrainer.exceptions.TranslationIsEmptyException;
import de.hhn.it.pp.components.vocabletrainer.provider.JbVocableTrainerService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class VocableTrainerServiceController extends Controller implements Initializable {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(VocableTrainerServiceController.class);
  public static JbVocableTrainerService jbVocableTrainerService = new JbVocableTrainerService();

  @FXML
  AnchorPane scenePane;

  public VocableTrainerServiceController() {
    logger.debug("VocableTrainerServiceController created.");
    JbVocableTrainerService jbVocableTrainerService = new JbVocableTrainerService();
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
    List<Vocable> carList = new ArrayList<>();
    List<Vocable> pcList = new ArrayList<>();
    try {
      carList.add(
          new Vocable("Auto", new String[] {"car", "vehicle", "motorcar", "automobile", "auto"}));
      carList.add(new Vocable("Spiegel", new String[] {"mirror", "glass", "reflector"}));
      carList.add(new Vocable("Reifen", new String[] {"tire", "hoop", "maturation"}));
      carList.add(new Vocable("Lenkrad", new String[] {"wheel", "steering wheel"}));
      carList.add(new Vocable("Autositz", new String[] {"car seat", "seat"}));
      pcList.add(new Vocable("Hauptplatine", new String[] {"motherboard"}));
      pcList.add(new Vocable("Arbeitsspeicher", new String[] {"RAM", "Random access memory"}));
      pcList.add(new Vocable("Batterie", new String[] {"battery"}));
      pcList.add(new Vocable("Netzteil", new String[] {"power supply"}));
    } catch (TranslationIsEmptyException e) {
      e.printStackTrace();
    }
    HashMap<String, List<Vocable>> testMap = new HashMap<>();
    testMap.put("Auto", carList);
    testMap.put("PC", pcList);
    LearningState learningState = new LearningState();
    learningState.setScore(0);
    learningState.setVocabularyList(testMap);
    jbVocableTrainerService.loadData(learningState);
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

  public void startVocableTrainer(ActionEvent event) throws IOException {
    loadPane(event);
    setScenePane("/vocabletrainer/Homepage");
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
}
