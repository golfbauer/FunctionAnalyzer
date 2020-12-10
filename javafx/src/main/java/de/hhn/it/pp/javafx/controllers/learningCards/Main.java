package de.hhn.it.pp.javafx.controllers.learningCards;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(de.hhn.it.pp.javafx.controllers.learningCards.Main.class);

  private final int width = 1080;

  private final int height = 720;

  /**
   * starts the main window.
   *
   * @param primaryStage primary window
   * @throws IOException when input or output caused an  Error
   */
  @Override
  public void start(Stage primaryStage) throws IOException {
    logger.info("LearningCards window is loading...");

    Parent root = FXMLLoader.load(getClass().getResource("/fxml/learningCards/main.fxml"));

    Scene scene = new Scene(root, width, height);
    primaryStage.setScene(scene);
    primaryStage.setMinWidth(width);
    primaryStage.setMinHeight(height);
    primaryStage.show();


  }

  /**
   * starts the ui.
   *
   * @param args string[]
   */
  public static void main(String[] args) {
    launch(args);

  }

}
