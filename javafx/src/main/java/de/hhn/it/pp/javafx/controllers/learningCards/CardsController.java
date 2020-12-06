package de.hhn.it.pp.javafx.controllers.learningCards;


import de.hhn.it.pp.components.learningCards.exceptions.CardNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CardsController implements Initializable {

    int cardsCreated = Data.mlcs.getNumberOfCards();

    int valB1 = 1;
    int valB2 = 2;
    int valB3 = 3;
    int valB4 = 4;
    int valB5 = 5;
    int valB6 = 6;

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
     * @param e
     * @throws IOException
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
     *  About to be added...
     */
    @FXML
    private void changeSceneToCardsets(ActionEvent e) throws IOException{

    }

    /**
     * changes the scene to newCards.fxml
     * @param e
     * @throws IOException
     */
    @FXML
    private void changeSceneToNewCard(ActionEvent e) throws IOException{

        Parent newCard = FXMLLoader.load(getClass().getResource("/fxml/learningCards/newCards.fxml"));
        Scene newCardScene = new Scene(newCard);

        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();

        window.setScene(newCardScene);
        window.show();
    }

    /**
     * changes scene to the card clicked and sends an ID to the card.fxml controller class
     * @param e
     * @throws IOException
     */
    @FXML
    private void changeSceneToCard(ActionEvent e)throws IOException{
        Button button = (Button) e.getSource();
        if(!button.getText().equalsIgnoreCase("empty")){


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
     * changes values for valB1-valB6
     * @param e
     */
    @FXML
    private void buttonsUp(ActionEvent e){
        if(cardsCreated > 6){
            if(valB1 == cardsCreated){
                valB1 = 0;
            }
            if(valB2 == cardsCreated){
                valB2 = 0;
            }
            if(valB3 == cardsCreated){
                valB3 = 0;
            }
            if(valB4 == cardsCreated){
                valB4 = 0;
            }
            if(valB5 == cardsCreated){
                valB5 = 0;
            }
            if(valB6 == cardsCreated){
                valB6 = 0;
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
     * Changes the Value of valB1-valB6
     * @param e
     */
    @FXML
    private void buttonsDown(ActionEvent e){
        if(cardsCreated > 6){
            if(valB1 == 1){
                valB1 = cardsCreated+1;
            }
            if(valB2 == 1){
                valB2 = cardsCreated+1;
            }
            if(valB3 == 1){
                valB3 = cardsCreated+1;
            }
            if(valB4 == 1){
                valB4 = cardsCreated+1;
            }
            if(valB5 == 1){
                valB5 = cardsCreated+1;
            }
            if(valB6 == 1){
                valB6 = cardsCreated+1;
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
    @FXML
    private void deleteCard(ActionEvent e) throws CardNotFoundException {
        Data.mlcs.deleteCard(1);
        refreshButton();

    }
    @FXML
    private void changeSceneToLearningSession(ActionEvent e) throws IOException {
        if(Data.mlcs.getNumberOfCards() >0) {
            Parent ls = FXMLLoader.load(getClass().getResource("/fxml/learningCards/LearningSession.fxml"));
            Scene lsScene = new Scene(ls);

            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();

            window.setScene(lsScene);
            window.show();
        }
    }

    /**
     * changes text of the buttons b1-b6
     */
    private void refreshButton(){
    if(Data.mlcs.getNumberOfCards() >0){
       if(Data.mlcs.getCards().size() >= 1) {
           b1.setText(Data.mlcs.getCards().get(valB1 - 1).getHeadline());
           b1.setId(Integer.toString(valB1));
           if (Data.mlcs.getCards().size() >= 2) {
               b2.setText(Data.mlcs.getCards().get(valB2 - 1).getHeadline());
               b2.setId(Integer.toString(valB2));
           }
           if (Data.mlcs.getCards().size() >= 3) {
               b3.setText(Data.mlcs.getCards().get(valB3 - 1).getHeadline());
               b3.setId(Integer.toString(valB3));
           }
           if (Data.mlcs.getCards().size() >= 4) {
               b4.setText(Data.mlcs.getCards().get(valB4 - 1).getHeadline());
               b4.setId(Integer.toString(valB4));

           }
           if (Data.mlcs.getCards().size() >= 5) {
               b5.setText(Data.mlcs.getCards().get(valB5 - 1).getHeadline());
               b5.setId(Integer.toString(valB5));
           }
           if (Data.mlcs.getCards().size() >= 6) {
               b6.setText(Data.mlcs.getCards().get(valB6 - 1).getHeadline());
               b6.setId(Integer.toString(valB6));
           }
       }





       }


    }

    /**
     * initilizes the scene values
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshButton();
    }
}
