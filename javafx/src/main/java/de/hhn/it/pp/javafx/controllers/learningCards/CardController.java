package de.hhn.it.pp.javafx.controllers.learningCards;

import de.hhn.it.pp.components.learningCards.Status;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CardController {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(CardController.class);

  private int id;
  @FXML
  private TextField title;
  @FXML
  private TextArea textbox;
  @FXML
  private Button qa;
  @FXML
  private CheckBox solved;

  /**
   * marks card as solved or unsolved.
   *
   * @param e ActionEvent when button was clicked
   */
  @FXML
  private void markAsSolved(ActionEvent e) {

    switch (Data.mlcs.getCardFromCol(id).getStatus()) {
      case UNSOLVED:
        Data.mlcs.getCardFromCol(id).setStatusToSolved();
        break;
      case SOLVED:
        Data.mlcs.getCardFromCol(id).setStatusToUnSolved();
        break;
    }
    System.out.println(Data.mlcs.getCardFromCol(id).getStatus());
  }

  /**
   * changes the textarea text.
   *
   * @param e ActionEvent when button was clicked
   */
  @FXML
  private void changeText(ActionEvent e) {
    Button button = (Button) e.getSource();

    switch (button.getText()) {
      case "Answer":
        button.setText("Question");
        textbox.setText(Data.mlcs.getCardFromCol(id).getTextA());
        break;
      case "Question":
        button.setText("Answer");
        textbox.setText(Data.mlcs.getCardFromCol(id).getTextQ());
        break;
    }

  }

  /**
   * takes the value from cardsController.
   *
   * @param ident cardButtonId
   */
  public void initData(String ident) {
    int i = Integer.parseInt(ident);
    if (i >= 1) {
      title.setText(Data.mlcs.getCardFromCol(i).getHeadline());
      textbox.setText(Data.mlcs.getCardFromCol(i).getTextQ());
      id = i;
      if (Data.mlcs.getCardFromCol(i).getStatus() == Status.UNSEEN) {
        Data.mlcs.getCardFromCol(i).setStatusToUnSolved();
      }
      if (Data.mlcs.getCardFromCol(i).getStatus() == Status.SOLVED) {
        solved.setSelected(true);
      }
    }
  }

  /**
   * changes Scene to Cardsets scene.
   *
   * @param e ActionEvent when button was clicked
   * @throws IOException when input or output causes an error
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
   * changes Scene to Home / main scene.
   *
   * @param e ActionEvent when button was clicked
   * @throws IOException when input or output causes an error
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
   * changes Scene to Cards scene.
   *
   * @param e ActionEvent when button was clicked
   * @throws IOException when input or output causes an error
   */
  @FXML
  private void changeSceneToCards(ActionEvent e) throws IOException {
    logger.info("Scene switched to: Cards");
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/fxml/learningCards/cards.fxml"));

    Parent cards = loader.load();
    Scene cardsScene = new Scene(cards);

    Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();

    window.setScene(cardsScene);
    window.show();
  }

  /**
   * saves changes made to the cardobject values.
   *
   * @param e MouseEvent when mouse left textarea
   */
  @FXML
  private void saveChanges(MouseEvent e) {

    if (title.getText().trim().equalsIgnoreCase("")
         || textbox.getText().trim().equalsIgnoreCase("")) {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Alert");
      alert.setHeaderText("The textbox needs to be filled :)");
      alert.showAndWait();
      title.setText(Data.mlcs.getCardFromCol(id).getHeadline());
      if (qa.getText().equalsIgnoreCase("Question")) {
        textbox.setText(Data.mlcs.getCardFromCol(id).getTextA());
      }
      if (qa.getText().equalsIgnoreCase("Answer")) {
        textbox.setText(Data.mlcs.getCardFromCol(id).getTextQ());
      }
    }


    Data.mlcs.getCardFromCol(id).setHeadline(title.getText());

    switch (qa.getText()) {
      case "Answer":
        Data.mlcs.getCardFromCol(id).editTextQ(textbox.getText());
        logger.info("saved changes");
        break;
      case "Question":
        Data.mlcs.getCardFromCol(id).editTextA(textbox.getText());
        logger.info("saved changes");
    }

  }


}
