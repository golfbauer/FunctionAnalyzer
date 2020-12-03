package de.hhn.it.pp.javafx.controllers.typingtrainerfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUITypingTrainer extends Application {

  @Override
  public void start(final Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("/fxml/StartScreen.fxml")); //Opens Startscreen
    //Parent root = FXMLLoader.load(getClass().getResource("/fxml/typingtrainer/TypingScreen.fxml")); //Opens Typingscreen

    Scene scene = new Scene(root);

    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
