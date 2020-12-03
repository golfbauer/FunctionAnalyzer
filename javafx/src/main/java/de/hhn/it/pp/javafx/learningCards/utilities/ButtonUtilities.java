package de.hhn.it.pp.javafx.learningCards.utilities;

import javafx.geometry.Pos;
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
