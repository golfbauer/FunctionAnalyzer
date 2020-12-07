package de.hhn.it.pp.javafx.controllers.learningCards;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private final int width = 1080;

    private final int height = 720;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/learningCards/main.fxml"));
        
        Scene scene = new Scene(root,width,height);
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(width);
        primaryStage.setMinHeight(height);
        primaryStage.show();
        

    }


    public static void main(String[] args) {
        launch(args);

    }

}
