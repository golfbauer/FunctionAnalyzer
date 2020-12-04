package de.hhn.it.pp.javafx.controllers;

import de.hhn.it.pp.components.vocabletrainer.provider.JbVocableTrainerService;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class VocableTrainerServiceController extends Controller implements Initializable {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(VocableTrainerServiceController.class);

  private Map<String, Controller> keyToControllerMap;

  public VocableTrainerServiceController(){
    logger.debug("VocableTrainerServiceController created.");
    JbVocableTrainerService jbVocableTrainerService = new JbVocableTrainerService();
    keyToControllerMap = new HashMap<>();
  }

  /**
   * Called to initialize a controller after its root element has been
   * completely processed.
   *
   * @param location  The location used to resolve relative paths for the root object, or
   *                  {@code null} if the location is not known.
   * @param resources The resources used to localize the root object, or {@code null} if
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }
}
