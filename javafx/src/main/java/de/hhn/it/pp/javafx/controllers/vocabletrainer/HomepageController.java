package de.hhn.it.pp.javafx.controllers.vocabletrainer;


import static de.hhn.it.pp.javafx.controllers.VocableTrainerServiceController.jbVocableTrainerService;


import de.hhn.it.pp.components.vocabletrainer.LearningState;
import de.hhn.it.pp.components.vocabletrainer.exceptions.VocCategoryNotFoundException;
import de.hhn.it.pp.javafx.controllers.coffeemaker.CoffeeMakerController;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public class HomepageController implements Initializable {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(HomepageController.class);
  public static LearningState learningState = new LearningState();
  public static String cateSaver;
  public static int levenshtein;

  public AnchorPane scenePane;
  @FXML
  ListView<String> categoryListView;
  @FXML
  Label scoreButton;
  @FXML
  RadioButton easyRadio;
  @FXML
  RadioButton mediumRadio;
  @FXML
  RadioButton hardRadio;
  @FXML
  ToggleGroup levenRadio;


  public void initialize(URL location, ResourceBundle resources) {
    logger.info("Homepage is initializing");
    List<String> categories = jbVocableTrainerService.getVocCategories();
    for (int i = 0; categories.size() > i; i++) {
      categoryListView.getItems().add(jbVocableTrainerService.getVocCategories().get(i));
    }
    scoreButton.setText("Score: " + jbVocableTrainerService.getScore());
    categoryListView.getSelectionModel().select(0);
    mediumRadio.setSelected(true);
    if (levenshtein == 0) {
      hardRadio.setSelected(true);
    }
    if (levenshtein == 2) {
      mediumRadio.setSelected(true);
    }
    if (levenshtein == 3) {
      easyRadio.setSelected(true);
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
  public void loadPane(ActionEvent event) {
    Node node = (Node) event.getSource();
    Scene scene = node.getScene();
    scenePane = (AnchorPane) scene.lookup("#scenePane");
  }

  public void newCategory(ActionEvent event) throws IOException {
    logger.info("New Category button is pressed.");
    loadPane(event);
    setScenePane("/vocabletrainer/EditCreateCategoryPage");
  }

  public void editCategory(ActionEvent event) throws IOException {
    logger.info("Edit Category button is pressed.");
    cateSaver = categoryListView.getSelectionModel().getSelectedItem();
    if (cateSaver != null) {
      loadPane(event);
      setScenePane("/vocabletrainer/EditCreateCategoryPage");
    } else {
      vocCategoryNotFoundAlert();
    }
  }

  public void deleteCategory(ActionEvent event) throws VocCategoryNotFoundException {
    logger.info("Delete Category button is pressed.");
    try {
      jbVocableTrainerService
          .removeVocCategory(categoryListView.getSelectionModel().getSelectedItem());
    } catch (VocCategoryNotFoundException e) {
      vocCategoryNotFoundAlert();
    }

    categoryListView.getItems().remove(categoryListView.getSelectionModel().getSelectedItem());
    categoryListView.refresh();
  }

  public void vocCategoryNotFoundAlert() {
    logger.info("vocCategoryNotFoundAlert is showing");
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Warning Dialog");
    alert.setHeaderText("No categories");
    alert.setContentText("Create new a category");
    alert.showAndWait();
  }

  public void okClick(ActionEvent event) throws IOException {
    logger.info("OK button is pressed.");
    if (categoryListView.getSelectionModel().isEmpty()) {
      vocCategoryNotFoundAlert();
    } else {
      cateSaver = categoryListView.getSelectionModel().getSelectedItem();
      loadPane(event);
      setScenePane("/vocabletrainer/VocabularyView");
    }
  }

  public void levenSet() {
    if (easyRadio.isSelected()) {
      logger.info("Radio button easy is pressed levenshtein set to 3");
      levenshtein = 3;
    }

    if (mediumRadio.isSelected()) {
      logger.info("Radio button medium is pressed levenshtein set to 2");
      levenshtein = 2;
    }

    if (hardRadio.isSelected()) {
      logger.info("Radio button hard is pressed levenshtein set to 0");
      levenshtein = 0;
    }


  }
}
