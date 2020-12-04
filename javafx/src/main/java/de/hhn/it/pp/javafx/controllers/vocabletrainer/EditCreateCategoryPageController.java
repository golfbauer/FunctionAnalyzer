package de.hhn.it.pp.javafx.controllers.vocabletrainer;

import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryAlreadyExistException;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryNotFoundException;
import de.hhn.it.pp.javafx.controllers.VocableTrainerServiceController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

public class EditCreateCategoryPageController implements Initializable {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(VocableTrainerServiceController.class);

  @FXML
  AnchorPane editCreateCategoryPagePane;
  @FXML
  TextField categoryNameTextField;

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
    if (HomepageController.cateSaver != null) {
      categoryNameTextField.setText(HomepageController.cateSaver);
    }
  }

  public void saveCategory(ActionEvent event) throws IOException {
    if (HomepageController.cateSaver == null) {
      // Create Category
      try {
        HomepageController.jbVocableTrainerService
            .addVocCategory(categoryNameTextField.getText(), new ArrayList<>());
      } catch (VocCategoryAlreadyExistException e) {
        logger.error("Category already existent", e);
        categoryExistentAlert();
      }
    } else {
      // Edit Category
      try {
        HomepageController.jbVocableTrainerService
            .editVocCategory(HomepageController.cateSaver, categoryNameTextField.getText());
      } catch (VocCategoryNotFoundException e) {
        e.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setHeaderText("Category to edit not found");
        alert.setContentText("Please cancel");
        alert.showAndWait();
      } catch (VocCategoryAlreadyExistException e) {
        e.printStackTrace();
        categoryExistentAlert();
      }
    }
    // clear VocEdit in Homepage
    HomepageController.cateSaver = null;
    loadPane(event);
    setScenePane("/vocabletrainer/Homepage");
  }

  private void categoryExistentAlert() {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Warning Dialog");
    alert.setHeaderText("Category already existent");
    alert.setContentText("Rename the category");
    alert.showAndWait();
  }

  public void cancel(ActionEvent event) throws IOException {
    // clear VocEdit in Homepage
    HomepageController.cateSaver = null;
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
    editCreateCategoryPagePane.getChildren().clear();
    editCreateCategoryPagePane.getChildren().add(root);
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
    editCreateCategoryPagePane = (AnchorPane) scene.lookup("#scenePane");
  }
}
