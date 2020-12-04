package de.hhn.it.pp.javafx.controllers.spellingtrainer;


import de.hhn.it.pp.components.spellingtrainer.Provider.MediaPresentationListener;
import de.hhn.it.pp.components.spellingtrainer.Provider.MediaReference;

public class AudioPresentationListener implements MediaPresentationListener {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(AudioPresentationListener.class);

  public AudioPresentationListener(){
    super();
  }
  /**
   * Method to present a media file.
   *
   * @param mediaReference reference to media file to be presented
   */
  @Override
  public void present(MediaReference mediaReference) {


  }
}
