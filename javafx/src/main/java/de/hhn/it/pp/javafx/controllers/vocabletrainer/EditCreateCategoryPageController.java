package de.hhn.it.pp.javafx.controllers.vocabletrainer;

import static de.hhn.it.pp.javafx.controllers.VocableTrainerServiceController.jbVocableTrainerService;
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
      org.slf4j.LoggerFactory.getLogger(EditCreateCategoryPageController.class);

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
    logger.debug("Save button is pressed.");
    String categoryName = categoryNameTextField.getText().trim();
    if (categoryName == null || categoryName.equals("")) {
      vocCategoryCanNotBeEmptyAlert();
      return;
    }
    boolean noException = false;
    if (HomepageController.cateSaver == null) {
      // Create Category
      try {
        jbVocableTrainerService
            .addVocCategory(categoryName, new ArrayList<>());
        noException = true;
      } catch (VocCategoryAlreadyExistException e) {
        logger.info("saveVocable: throws {}", "" + e);
        categoryExistentAlert();
      }
    } else {
      // Edit Category
      try {
        jbVocableTrainerService
            .editVocCategory(HomepageController.cateSaver, categoryName);
        noException = true;
      } catch (VocCategoryNotFoundException e) {
        logger.info("saveVocable: throws {}", "" + e);
        vocCategoryNotFoundAlert();
      } catch (VocCategoryAlreadyExistException e) {
        logger.info("saveVocable: throws {}", "" + e);
        categoryExistentAlert();
      }
    }
    if (noException) {
      // clear VocEdit in Homepage
      HomepageController.cateSaver = null;
      loadPane(event);
      setScenePane("/vocabletrainer/Homepage");
    }
  }

  private void vocCategoryCanNotBeEmptyAlert() {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Warning Dialog");
    alert.setHeaderText("Category can not be empty");
    alert.setContentText("Please enter a category name");
    alert.showAndWait();
  }

  private void vocCategoryNotFoundAlert() {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Warning Dialog");
    alert.setHeaderText("Category to edit not found");
    alert.setContentText("Please cancel");
    alert.showAndWait();
  }

  private void categoryExistentAlert() {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Warning Dialog");
    alert.setHeaderText("Category already existent");
    alert.setContentText("Rename the category");
    alert.showAndWait();
  }

  public void cancel(ActionEvent event) throws IOException {
    logger.debug("cancel button is pressed.");
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
