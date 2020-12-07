package de.hhn.it.pp.javafx.controllers.learningCards;

import de.hhn.it.pp.components.learningCards.Status;

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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LearningSessionController implements Initializable {

  @FXML
  private TextField title;
  @FXML
  private TextArea textbox;
  @FXML
  private CheckBox solved;
  @FXML
  private Button qa;
  @FXML
  private CheckBox onlyUnsolved;

  private int maxCards = Data.mlcs.getNumberOfCards();

  private int currentPos = 0;

  /**
   * changes Scene to Cardsets scene.
   *
   * @param e ActionEvent when button was clicked
   * @throws IOException when input or output causes an error
   */
  @FXML
  private void changeSceneToCardsets(ActionEvent e) throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/fxml/learningCards/cardsets.fxml"));
    Parent cardsets = loader.load();
    Scene cardsetsScene = new Scene(cardsets);

    Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();

    window.setScene(cardsetsScene);
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

    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/fxml/learningCards/cards.fxml"));

    Parent cards = loader.load();
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
  private void changeSceneToHome(ActionEvent e) throws IOException {
    Parent home = FXMLLoader.load(getClass().getResource("/fxml/learningCards/main.fxml"));
    Scene homeScene = new Scene(home);

    Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();

    window.setScene(homeScene);
    window.show();
  }

  /**
   * changes the textarea text.
   *
   * @param e ActionEvent when button was clicked
   */
  @FXML
  private void changeText(ActionEvent e) {

    switch (qa.getText()) {
      case "Answer":
        textbox.setText(Data.mlcs.getCards().get(currentPos).getTextA());
        qa.setText("Question");
        break;
      case "Question":
        textbox.setText(Data.mlcs.getCards().get(currentPos).getTextQ());
        qa.setText("Answer");
    }
  }

  /**
   * marks the current card to solved and unsolved depending on the checkbox.
   *
   * @param e ActionEvent when button was clicked
   */
  @FXML
  private void markAsSolved(ActionEvent e) {
    switch (Data.mlcs.getCards().get(currentPos).getStatus()) {
      case UNSOLVED:
        Data.mlcs.getCards().get(currentPos).setStatusToSolved();
        break;
      case SOLVED:
        Data.mlcs.getCards().get(currentPos).setStatusToUnSolved();
        break;
      case UNSEEN:
        Data.mlcs.getCards().get(currentPos).setStatusToSolved();
    }
  }

  /**
   * swaps the to the left card.
   *
   * @param e ActionEvent when button was clicked
   */
  @FXML
  private void goLeft(ActionEvent e) {
    if (currentPos > 0) {
      currentPos--;
      setCard();
    }

  }

  /**
   * swaps the to the right card.
   *
   * @param e ActionEvent when button was clicked
   */
  @FXML
  private void goRight(ActionEvent e) {
    if (currentPos < maxCards - 1) {
      currentPos++;

      setCard();
    }
  }

  /**
   * resets the card pos to 0 and calls setcard() method.
   *
   * @param e ActionEvent when button was clicked
   */
  @FXML
  private void onlyUnsolvedWasSelected(ActionEvent e) {
    currentPos = 0;
    setCard();
  }

  /**
   * Setups the card depending if they are solved or not.
   */
  private void setCard() {


    if (onlyUnsolved.isSelected()) {

      if (Data.mlcs.getCards().get(currentPos).getStatus()
           == Status.SOLVED && currentPos < maxCards - 1) {
        currentPos++;
        setCard();
      }
    }
    title.setText(Data.mlcs.getCards().get(currentPos).getHeadline());
    switch (Data.mlcs.getCards().get(currentPos).getStatus()) {
      case SOLVED:
        solved.setSelected(true);
        break;
      case UNSOLVED:
        solved.setSelected(false);
        break;
      case UNSEEN:
        solved.setSelected(false);
        break;
    }
    switch (qa.getText()) {
      case "Answer":
        textbox.setText(Data.mlcs.getCards().get(currentPos).getTextQ());

        break;
      case "Question":
        qa.setText("Answer");
        textbox.setText(Data.mlcs.getCards().get(currentPos).getTextQ());
        break;
    }


  }

  /**
   * initilizes the scene values.
   *
   * @param url            location of resources
   * @param resourceBundle bundle of resources
   */
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

    setCard();
  }
}
