package de.hhn.it.pp.javafx.controllers.learningCards;

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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LearningSessionController implements Initializable {
    @FXML
    private TextField title;
    @FXML
    private TextArea textbox;
    @FXML
    private CheckBox solved;
    @FXML
    private Button qa;


    int maxCards = Data.mlcs.getNumberOfCards();

    int currentPos = 0;

    @FXML
    private void changeSceneToCardsets(ActionEvent e){

    }
    @FXML
    private void changeSceneToCards(ActionEvent e) throws IOException{

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
    private void changeText(ActionEvent e){

        switch(qa.getText()){
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
    private void markAsSolved(ActionEvent e){
        switch(Data.mlcs.getCards().get(currentPos).getStatus()){
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
    private void goLeft(ActionEvent e){
    if(currentPos >0){
        currentPos--;
    setCard();
    }

    }
    @FXML
    private void goRight(ActionEvent e){
    if(currentPos < maxCards-1){
        currentPos++;
        setCard();
    }
    }
    private void setCard(){
        title.setText(Data.mlcs.getCards().get(currentPos).getHeadline());
        textbox.setText(Data.mlcs.getCards().get(currentPos).getTextQ());

        switch(Data.mlcs.getCards().get(currentPos).getStatus()){

            case UNSOLVED:
                solved.setSelected(false);
                break;
            case UNSEEN:
                solved.setSelected(false);
                break;
            case SOLVED:
                solved.setSelected(true);
                break;
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    title.setText(Data.mlcs.getCards().get(currentPos).getHeadline());
        textbox.setText(Data.mlcs.getCards().get(currentPos).getTextQ());
        switch(Data.mlcs.getCards().get(currentPos).getStatus()){

            case UNSOLVED:
                solved.setSelected(false);
                break;
            case UNSEEN:
                solved.setSelected(false);
                break;
            case SOLVED:
                solved.setSelected(true);
                break;
        }
    }
}
