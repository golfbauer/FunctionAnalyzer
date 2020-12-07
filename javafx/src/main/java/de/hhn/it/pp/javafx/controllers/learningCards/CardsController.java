package de.hhn.it.pp.javafx.controllers.learningCards;

import de.hhn.it.pp.components.learningCards.exceptions.CardNotFoundException;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;


public class CardsController implements Initializable {

  int cardsCreated = Data.mlcs.getNumberOfCards();

  int valB1 = 0;
  int valB2 = 1;
  int valB3 = 2;
  int valB4 = 3;
  int valB5 = 4;
  int valB6 = 5;

  @FXML
  private Button newCard;
  @FXML
  private Button up;
  @FXML
  private Button down;
  @FXML
  private Button b1;
  @FXML
  private Button b2;
  @FXML
  private Button b3;
  @FXML
  private Button b4;
  @FXML
  private Button b5;
  @FXML
  private Button b6;
  @FXML
  private Button del1;
  @FXML
  private Button del2;
  @FXML
  private Button del3;
  @FXML
  private Button del4;
  @FXML
  private Button del5;
  @FXML
  private Button del6;


  /**
   * changes the scene to main.fxml
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
   * About to be added...
   */
  @FXML
  private void changeSceneToCardsets(ActionEvent e) throws IOException {

  }

  /**
   * changes the scene to newCards.
   *
   * @param e ActionEvent when Button is clicked
   * @throws IOException when input or output caused an  Error
   */
  @FXML
  private void changeSceneToNewCard(ActionEvent e) throws IOException {

    Parent newCard = FXMLLoader.load(getClass().getResource("/fxml/learningCards/newCards.fxml"));
    Scene newCardScene = new Scene(newCard);

    Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();

    window.setScene(newCardScene);
    window.show();
  }

  /**
   * changes scene to the card clicked and sends an ID to the cardcontroller class.
   *
   * @param e ActionEvent when Button is clicked
   * @throws IOException when input or output caused an  Error
   */
  @FXML
  private void changeSceneToCard(ActionEvent e) throws IOException {
    Button button = (Button) e.getSource();
    if (!button.getText().equalsIgnoreCase("empty")) {


      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/learningCards/card.fxml"));
      Parent card = loader.load();

      CardController c = loader.getController();


      c.initData(button.getId());
      Scene cardScene = new Scene(card);

      Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();

      window.setScene(cardScene);
      window.show();
    }
  }

  /**
   * changes values for valB1-valB6.
   *
   * @param e ActionEvent when Button was clicked
   */
  @FXML
  private void buttonsUp(ActionEvent e) {
    if (cardsCreated > 6) {
      if (valB1 == cardsCreated - 1) {
        valB1 = -1;
      }
      if (valB2 == cardsCreated - 1) {
        valB2 = -1;
      }
      if (valB3 == cardsCreated - 1) {
        valB3 = -1;
      }
      if (valB4 == cardsCreated - 1) {
        valB4 = -1;
      }
      if (valB5 == cardsCreated - 1) {
        valB5 = -1;
      }
      if (valB6 == cardsCreated - 1) {
        valB6 = -1;
      }
      valB1++;
      valB2++;
      valB3++;
      valB4++;
      valB5++;
      valB6++;
      refreshButton();
    }
  }

  /**
   * Changes the Value of valB1-valB6.
   *
   * @param e ActionEvent when Button was clicked
   */
  @FXML
  private void buttonsDown(ActionEvent e) {
    if (cardsCreated > 6) {
      if (valB1 == 0) {
        valB1 = cardsCreated;
      }
      if (valB2 == 0) {
        valB2 = cardsCreated;
      }
      if (valB3 == 0) {
        valB3 = cardsCreated;
      }
      if (valB4 == 0) {
        valB4 = cardsCreated;
      }
      if (valB5 == 0) {
        valB5 = cardsCreated;
      }
      if (valB6 == 0) {
        valB6 = cardsCreated;
      }
      valB1--;
      valB2--;
      valB3--;
      valB4--;
      valB5--;
      valB6--;
      refreshButton();
    }
  }

  /**
   * removes a card from CardCol, calls the refresh method.
   *
   * @param e ActionEvent when Button was clicked
   * @throws CardNotFoundException thrown when the card that is supposed to be
   *                               deleted does not exist.
   */
  @FXML
  private void deleteCard(ActionEvent e) throws CardNotFoundException {
    Button delButton = (Button) e.getSource();
    Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
    confirmation.setTitle("Confirmation");
    confirmation.setHeaderText("Please confirm your selection");
    Optional<ButtonType> result = confirmation.showAndWait();
    if (result.get() == ButtonType.OK) {
      switch (delButton.getId()) {
        case "del1":
          if (cardsCreated >= 1) {
            Data.mlcs.deleteCard(Integer.parseInt(b1.getId()));
          }
          break;
        case "del2":
          if (cardsCreated >= 2) {
            Data.mlcs.deleteCard(Integer.parseInt(b2.getId()));
          }
          break;
        case "del3":
          if (cardsCreated >= 3) {
            Data.mlcs.deleteCard(Integer.parseInt(b3.getId()));
          }
          break;
        case "del4":
          if (cardsCreated >= 4) {
            Data.mlcs.deleteCard(Integer.parseInt(b4.getId()));
          }
          break;
        case "del5":
          if (cardsCreated >= 5) {
            Data.mlcs.deleteCard(Integer.parseInt(b5.getId()));
          }
          break;
        case "del6":
          if (cardsCreated >= 6) {
            Data.mlcs.deleteCard(Integer.parseInt(b6.getId()));
          }
          break;
      }

      cardsCreated = Data.mlcs.getNumberOfCards();


      if (cardsCreated == 0) {
        refreshButton();
        return;

      }

      if (valB1 > 0) {
        valB1 = valB1 % cardsCreated;
      }
      if (valB2 <= cardsCreated) {
        valB2 = (valB1 + 1) % cardsCreated;
      }
      if (valB3 <= cardsCreated) {
        valB3 = (valB1 + 2) % cardsCreated;
      }
      if (valB4 <= cardsCreated) {
        valB4 = (valB1 + 3) % cardsCreated;
      }
      if (valB5 <= cardsCreated) {
        valB5 = (valB1 + 4) % cardsCreated;
      }
      if (valB6 <= cardsCreated) {
        valB6 = (valB1 + 5) % cardsCreated;
      }


      refreshButton();

    }
  }

  /**
   * Changes the scene to learningsession.
   *
   * @param e ActionEvent when Button is clicked
   * @throws IOException when input or output caused an  Error
   */
  @FXML
  private void changeSceneToLearningSession(ActionEvent e) throws IOException {
    if (Data.mlcs.getNumberOfCards() > 0) {
      Parent ls =
           FXMLLoader.load(getClass().getResource("/fxml/learningCards/LearningSession.fxml"));
      Scene lsScene = new Scene(ls);

      Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();

      window.setScene(lsScene);
      window.show();
    }
  }


  /**
   * changes text of the buttons b1-b6.
   */
  private void refreshButton() {
    if (Data.mlcs.getCards().size() >= 1) {
      b1.setText(Data.mlcs.getCards().get(valB1).getHeadline());
      b1.setId(Integer.toString(Data.mlcs.getCards().get(valB1).getId()));
    } else {
      b1.setText("empty");
    }
    if (Data.mlcs.getCards().size() >= 2) {
      b2.setText(Data.mlcs.getCards().get(valB2).getHeadline());
      b2.setId(Integer.toString(Data.mlcs.getCards().get(valB2).getId()));
    } else {
      b2.setText("empty");
    }
    if (Data.mlcs.getCards().size() >= 3) {
      b3.setText(Data.mlcs.getCards().get(valB3).getHeadline());
      b3.setId(Integer.toString(Data.mlcs.getCards().get(valB3).getId()));
    } else {
      b3.setText("empty");
    }
    if (Data.mlcs.getCards().size() >= 4) {
      b4.setText(Data.mlcs.getCards().get(valB4).getHeadline());
      b4.setId(Integer.toString(Data.mlcs.getCards().get(valB4).getId()));
    } else {
      b4.setText("empty");
    }
    if (Data.mlcs.getCards().size() >= 5) {
      b5.setText(Data.mlcs.getCards().get(valB5).getHeadline());
      b5.setId(Integer.toString(Data.mlcs.getCards().get(valB5).getId()));
    } else {
      b5.setText("empty");
    }

    if (Data.mlcs.getCards().size() >= 6) {
      b6.setText(Data.mlcs.getCards().get(valB6).getHeadline());
      b6.setId(Integer.toString(Data.mlcs.getCards().get(valB6).getId()));
    } else {
      b6.setText("empty");
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
    refreshButton();
  }
}
