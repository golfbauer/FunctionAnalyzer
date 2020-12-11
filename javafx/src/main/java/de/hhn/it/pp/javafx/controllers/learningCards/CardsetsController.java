package de.hhn.it.pp.javafx.controllers.learningCards;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class CardsetsController {
  private static final org.slf4j.Logger logger =
       org.slf4j.LoggerFactory.getLogger(CardsetsController.class);

  /**
   * changes the scene to main.fxml
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
}
