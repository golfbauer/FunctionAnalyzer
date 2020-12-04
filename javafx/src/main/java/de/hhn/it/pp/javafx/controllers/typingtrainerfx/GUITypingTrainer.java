package de.hhn.it.pp.javafx.controllers.typingtrainerfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/***
 * @author Tobias Maraci, Robert Pistea
 * @version 1.0
 * @since 1.1
 */
public class GUITypingTrainer extends Application {

  @Override
  public void start(final Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("/fxml/typingtrainer/StartScreen.fxml")); //Opens Startscreen
    //Parent root = FXMLLoader.load(getClass().getResource("/fxml/typingtrainer/TypingScreen.fxml")); //Opens Typingscreen

    Scene scene = new Scene(root);

    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
