package de.hhn.it.pp.javafx.controllers.learningCards;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewCardsController {
  private static final org.slf4j.Logger logger =
       org.slf4j.LoggerFactory.getLogger(NewCardsController.class);

  @FXML
  private TextField title;
  @FXML
  private TextArea questiontext;
  @FXML
  private TextArea answertext;


  /**
   * changes the scene to Cards.
   *
   * @param e ActionEvent when button was clicked
   * @throws IOException when Input causes an Error
   */
  @FXML
  private void changeSceneToCards(ActionEvent e) throws IOException {
    logger.info("Scene switched to: Cards");
    Parent cards = FXMLLoader.load(getClass().getResource("/fxml/learningCards/cards.fxml"));
    Scene cardsScene = new Scene(cards);

    Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();

    window.setScene(cardsScene);
    window.show();
  }

  /**
   * changes scene to Cardsets scene.
   *
   * @param e ActionEvent when Button is clicked
   * @throws IOException when input or output caused an  Error
   */
  @FXML
  private void changeSceneToCardsets(ActionEvent e) throws IOException {
    logger.info("Scene switched to: Cardsets");
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/fxml/learningCards/cardsets.fxml"));
    Parent cardsets = loader.load();
    Scene cardsetsScene = new Scene(cardsets);

    Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();

    window.setScene(cardsetsScene);
    window.show();
  }

  /**
   * changes scene to Cardsets scene.
   *
   * @param e ActionEvent when Button is clicked
   * @throws IOException when input or output caused an  Error
   */
  @FXML
  private void changeSceneToHome(ActionEvent e) throws IOException {
    logger.info("Scene switched to: Home");
    Parent home = FXMLLoader.load(getClass().getResource("/fxml/learningCards/main.fxml"));
    Scene homeScene = new Scene(home);

    Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();

    window.setScene(homeScene);
    window.show();
  }

  /**
   * adds values to card object.
   *
   * @param e ActionEvent when Button is clicked
   * @throws IOException when input or output caused an  Error
   */
  @FXML
  private void addCard(ActionEvent e) throws IOException {
    if (!title.getText().trim().equalsIgnoreCase("") && !questiontext.getText().trim()
         .equalsIgnoreCase("") && !answertext.getText().trim().equalsIgnoreCase("")) {
      Data.mlcs.createCard(title.getText(), questiontext.getText(), answertext.getText());
      changeSceneToCards(e);

    } else {

      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Alert");
      alert.setHeaderText("You need to fill every Textbox :)");

      alert.showAndWait();

    }
  }


}
