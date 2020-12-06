package de.hhn.it.pp.javafx.controllers.learningCards;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class NewCardsController {

    @FXML
    private Button homeB;
    @FXML
    private Button cardsB;
    @FXML
    private Button cardsetsB;
    @FXML
    private Button finish;
    @FXML
    private TextField title;
    @FXML
    private TextArea questiontext;
    @FXML
    private TextArea answertext;


    /**
     * changes the scene to Cards
     * @param e
     * @throws IOException
     */
    @FXML
    public void changeSceneToCards(ActionEvent e) throws IOException {
        Parent cards = FXMLLoader.load(getClass().getResource("/fxml/learningCards/cards.fxml"));
        Scene cardsScene = new Scene(cards);

        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();

        window.setScene(cardsScene);
        window.show();
    }
    @FXML
    public void changeSceneToCardsets(ActionEvent e)throws IOException{

    }
    @FXML
    public void changeSceneToHome(ActionEvent e)throws IOException{
        Parent home = FXMLLoader.load(getClass().getResource("/fxml/learningCards/main.fxml"));
        Scene homeScene = new Scene(home);

        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();

        window.setScene(homeScene);
        window.show();
    }
    @FXML
    public void addCard(ActionEvent e) throws IOException{
        Data.mlcs.createCard(title.getText(),questiontext.getText(),answertext.getText());
        changeSceneToCards(e);

    }
}
