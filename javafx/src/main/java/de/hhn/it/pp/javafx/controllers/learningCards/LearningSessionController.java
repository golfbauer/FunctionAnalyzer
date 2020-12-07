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

  @FXML
  private void changeSceneToCardsets(ActionEvent e) {

  }

  @SuppressWarnings("checkstyle:Indentation")
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

  @FXML
  private void changeSceneToHome(ActionEvent e) throws IOException {
    Parent home = FXMLLoader.load(getClass().getResource("/fxml/learningCards/main.fxml"));
    Scene homeScene = new Scene(home);

    Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();

    window.setScene(homeScene);
    window.show();
  }

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

  @FXML
  private void goLeft(ActionEvent e) {
    if (currentPos > 0) {
      currentPos--;
      setCard();
    }

  }

  @FXML
  private void goRight(ActionEvent e) {
    if (currentPos < maxCards - 1) {
      currentPos++;

      setCard();
    }
  }

  @FXML
  private void onlyUnsolvedWasSelected(ActionEvent e) {
    currentPos = 0;
    setCard();
  }

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

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

    setCard();
  }
}
