package de.hhn.it.pp.javafx.controllers.vocabletrainer;

import static de.hhn.it.pp.javafx.controllers.VocableTrainerServiceController.jbVocableTrainerService;
import static de.hhn.it.pp.javafx.controllers.vocabletrainer.HomepageController.cateSaver;
import static de.hhn.it.pp.javafx.controllers.vocabletrainer.VocabularyViewController.vocEdit;
import static de.hhn.it.pp.javafx.controllers.vocabletrainer.VocabularyViewController.vocInt;


import de.hhn.it.pp.components.vocabletrainer.exceptions.TranslationIsEmptyException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryNotFoundException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocableNotFoundException;
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
  TextField learningWordField;
  @FXML
  TextField translationWordsField;

  public void saveVocable(ActionEvent event) throws IOException {
    boolean noException = false;
    if (vocEdit == null) {
      // Create Vocable
      String[] words = toArrayTranslations(translationWordsField.getText().trim());

      try {
        jbVocableTrainerService.addVocable(learningWordField.getText().trim(),
            words, cateSaver);
        noException = true;
      } catch (VocCategoryNotFoundException e) {
        logger.info("saveVocable: throws {}", e);
        vocCategoryNotFoundAlert();
      } catch (TranslationIsEmptyException e) {
        logger.info("saveVocable: throws {}", e);
        translationIsEmptyAlert();
      }
    } else {
      // Edit Vocable
      // jbVocableTrainerService.editVocable();
      String[] translations = toArrayTranslations(translationWordsField.getText().trim());
      try {
        jbVocableTrainerService
            .editVocable(vocInt, learningWordField.getText().trim(), translations, cateSaver);
        noException = true;
      } catch (VocableNotFoundException e) {
        logger.info("saveVocable: throws {}", e);
        e.printStackTrace();
        vocableNotFoundAlert();
      } catch (VocCategoryNotFoundException e) {
        logger.info("saveVocable: throws {}", e);
        e.printStackTrace();
        vocCategoryNotFoundAlert();
      } catch (TranslationIsEmptyException e) {
        logger.info("saveVocable: throws {}", e);
        e.printStackTrace();
        translationIsEmptyAlert();
      }
    }
    if (noException) {
      vocEdit = null;
      loadPane(event);
      setScenePane("/vocabletrainer/VocabularyView");
    }
  }

  private String[] toArrayTranslations(String str) {
    String[] tWords;
    //if string is empty or null, return empty array
    if (str == null || str.equals("")) {
      return new String[0];
    }
    String[] words = str.split(",");
    for (int i = 0; i < words.length; i++) {
      //String tmp = words[i]
      words[i] = words[i].trim();
    }
    return words;
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

  private void translationIsEmptyAlert() {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Warning Dialog");
    alert.setHeaderText("Translations are empty");
    alert.setContentText("Please add translation words");
    alert.showAndWait();
  }

  public void cancel(ActionEvent event) throws IOException {
    vocEdit = null;
    loadPane(event);
    setScenePane("/vocabletrainer/VocabularyView");
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
      learningWordField.setText(vocEdit.getLearningWord());
      // ToDo display translation words with commas in the textbox
      StringBuilder str = new StringBuilder();
      for (String s : vocEdit.getTranslations()) {
        str.append(s).append(", ");
      }
      translationWordsField.setText(str.toString());
    }
  }
}
