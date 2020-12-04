package de.hhn.it.pp.javafx.controllers.vocabletrainer;

import static de.hhn.it.pp.javafx.controllers.vocabletrainer.HomepageController.cateSaver;
import static de.hhn.it.pp.javafx.controllers.vocabletrainer.HomepageController.jbVocableTrainerService;
import static de.hhn.it.pp.javafx.controllers.vocabletrainer.VocabularyViewController.vocEdit;


import de.hhn.it.pp.components.vocabletrainer.exceptions.TranslationIsEmptyException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryNotFoundException;
import de.hhn.it.pp.javafx.controllers.VocableTrainerServiceController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class EditVocablePageController implements Initializable {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(VocableTrainerServiceController.class);

  @FXML
  AnchorPane scenePane;
  @FXML
  TextField learningWord;
  @FXML
  TextField translationWords;

  public void saveVocable(ActionEvent event) throws IOException {
    if (vocEdit == null) {
      // Create Vocable
      // ToDo: method to separate translations
      String tWords = translationWords.getText();
      
      try {
        jbVocableTrainerService.addVocable(learningWord.getText().trim(),
            new String[] {translationWords.getText().trim()}, cateSaver);
      } catch (VocCategoryNotFoundException e) {
        e.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setHeaderText("Category not found");
        alert.setContentText("Please cancel");
        alert.showAndWait();
      } catch (TranslationIsEmptyException e) {
        e.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setHeaderText("Translations are empty");
        alert.setContentText("Please add translation words");
        alert.showAndWait();
      }
    } else {
      // Edit Vocable
      // jbVocableTrainerService.editVocable();

    }
    vocEdit = null;
    loadPane(event);
    setScenePane("/vocabletrainer/Homepage");
  }

  public void cancel(ActionEvent event) throws IOException {
    vocEdit = null;
    loadPane(event);
    setScenePane("/vocabletrainer/Homepage");
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
    if (vocEdit != null) {
      learningWord.setText(vocEdit.getLearningWord());
      // ToDo display translation words with commas in the textbox
      translationWords.setText(vocEdit.getTranslations().toString());
    }
  }
}
