package de.hhn.it.pp.javafx.controllers.vocabletrainer;


import de.hhn.it.pp.components.vocabletrainer.LearningState;
import de.hhn.it.pp.components.vocabletrainer.provider.JbVocableTrainerService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class HomepageController implements Initializable {
  JbVocableTrainerService jbVocableTrainerService = new JbVocableTrainerService();
  LearningState learningState = new LearningState();

  @FXML
  ListView<String> categoryListView;

  public void initialize(URL location, ResourceBundle resources) {
    List<String> categories = jbVocableTrainerService.getVocCategories();
    for (int i = 0; categories.size() > i; i++) {
      categoryListView.getItems().add(jbVocableTrainerService.getVocCategories().get(i));
    }
  }
}
