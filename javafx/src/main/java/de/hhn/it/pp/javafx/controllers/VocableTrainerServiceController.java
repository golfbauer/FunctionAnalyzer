package de.hhn.it.pp.javafx.controllers;

import de.hhn.it.pp.components.vocabletrainer.Vocable;
import de.hhn.it.pp.components.vocabletrainer.exceptions.TranslationIsEmptyException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryAlreadyExistException;
import de.hhn.it.pp.components.vocabletrainer.provider.JbVocableTrainerService;
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
    List<Vocable> test2 = new ArrayList<>();
    try {
      test2.add(new Vocable("Auto",new String[]{"car"}));
    } catch (TranslationIsEmptyException e) {
      e.printStackTrace();
    }
    try {
      jbVocableTrainerService.addVocCategory("test1", test2);
    } catch (VocCategoryAlreadyExistException e) {
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
