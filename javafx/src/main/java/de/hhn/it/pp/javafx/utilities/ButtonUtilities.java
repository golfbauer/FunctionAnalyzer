package de.hhn.it.pp.javafx.utilities;

import de.hhn.it.pp.components.learningCards.Cardset;
import de.hhn.it.pp.components.learningCards.LearningCardsService;
import de.hhn.it.pp.javafx.controllers.LearningCardsServiceController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class ButtonUtilities {


    public static Button createButton(String buttonText) {
        Button myButton = new Button(buttonText);
        myButton.setPrefSize(200, 50);
        myButton.setAlignment(Pos.CENTER);
        myButton.setFont(new Font((20)));
        myButton.setStyle("-fx-background-radius: 0;");
        return myButton;
    }

    // to identification of Cards and Cardsets
    public static Button createButton(String buttonText, String buttonID) {
        Button myButton = new Button(buttonText);
        myButton.setPrefSize(200, 50);
        myButton.setAlignment(Pos.CENTER);
        myButton.setFont(new Font((20)));
        myButton.setStyle("-fx-background-radius: 0;");
        myButton.setId(buttonID);
        return myButton;
    }



}
