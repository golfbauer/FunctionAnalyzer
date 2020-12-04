package de.hhn.it.pp.javafx.controllers.typingtrainerfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.logging.Logger;

/***
 * @author Tobias Maraci, Robert Pistea
 * @version 1.0
 * @since 1.1
 */
public class GUITypingTrainer extends Application {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(GUITypingTrainer.class);

  @Override
  public void start(final Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("/fxml/typingtrainer/StartScreen.fxml")); //Opens Startscreen

    Scene scene = new Scene(root);

    primaryStage.setScene(scene);
    primaryStage.show();

    logger.debug("Open Typing Trainer.");
  }

  public static void main(String[] args) {
    launch(args);
  }
}
