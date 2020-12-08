package de.hhn.it.pp.javafx.controllers.learningCards;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;


public class MainController {


  @FXML
  private Button mainQA;

  @FXML
  private Button homeB;

  @FXML
  private Button cardsB;

  @FXML
  private Button cardsetsB;

  @FXML
  private TextArea mainT;

  /**
   * Method changes the text in the textarea from mainT.
   *
   * @param e ActionEvent when button was clicked
   */
  @FXML
  private void changeText(ActionEvent e) {
    System.out.println(Data.mlcs.getNumberOfCards());
    Button b = (Button) e.getSource();

    switch (b.getText()) {

      case "Answer":
        b.setText("Question");
        mainT.setText("Cards--> new Cards\n"
             + "You can put your Cards in Sets, which is useful if\n you want to categorize them");
        break;
      case "Question":
        b.setText("Answer");
        mainT.setText("- Where do you create cards?\n"
             + "- What do I do with cardsets?");
    }

  }

  /**
   * changes the scene to cards.
   *
   * @param e ActionEvent when button was clicked
   * @throws IOException when input or output causes an error
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
  private void changeSceneToCardsets(ActionEvent e) throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/fxml/learningCards/cardsets.fxml"));
    Parent cardsets = loader.load();
    Scene cardsetsScene = new Scene(cardsets);

    Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();

    window.setScene(cardsetsScene);
    window.show();
  }


}
