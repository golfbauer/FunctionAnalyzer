package de.hhn.it.pp.javafx.learningCards;

import de.hhn.it.pp.javafx.learningCards.controllers.LearningCardsServiceController;

import de.hhn.it.pp.javafx.learningCards.utilities.ButtonUtilities;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {

    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(de.hhn.it.pp.javafx.Main.class);
    private final int width = 1000;

    private final int height = 720;
    private VBox leftSide;
    private VBox rightSide;
    private Separator separator;

    private LearningCardsServiceController learningCardsServiceController;

    /**
     * the main method.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {

        System.out.println("java version: " + System.getProperty("java.version"));
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        learningCardsServiceController = new LearningCardsServiceController();

        Pane root = new Pane();
        Scene scene = new Scene(root, width, height);

        setLeftSide();
        setSeparator();
        setRightSide();

        root.getChildren().addAll(leftSide, separator, rightSide);


        primaryStage.setTitle("Learning Cards");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setLeftSide(){
        leftSide = new VBox(25);
        leftSide.setPrefSize(250, height);
        leftSide.setAlignment(Pos.TOP_CENTER);
        leftSide.setPadding(new Insets(50,0,0,0));

        Button cardsetButton = ButtonUtilities.createButton("Cardsets");
        cardsetButton.setOnAction( e ->
                rightSide.getChildren().setAll(learningCardsServiceController.getObservableCardsets()));

        Button cardButton = ButtonUtilities.createButton("Cards");
        cardButton.setOnAction( e ->
                rightSide.getChildren().setAll(learningCardsServiceController.getObservableCards()));


        leftSide.getChildren().addAll(cardsetButton, cardButton);
    }

    private void setSeparator(){
        separator = new Separator();
        separator.setStyle(
                "-fx-background-color: #000000;"+
                        "-fx-background-radius: 1;"
        );
        separator.setTranslateX(250);
        separator.setOrientation(Orientation.VERTICAL);
        separator.setPrefHeight(height);
    }

    private void setRightSide(){
        rightSide = new VBox(25);
        rightSide.setTranslateX(250);
        rightSide.setPrefSize(width - 250, height);
        rightSide.setAlignment(Pos.TOP_CENTER);
        rightSide.setPadding(new Insets(50,0,0,0));
    }



}

