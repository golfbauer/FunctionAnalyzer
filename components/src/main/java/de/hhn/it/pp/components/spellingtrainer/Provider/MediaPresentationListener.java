package de.hhn.it.pp.components.spellingtrainer.Provider;

import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Class MediaPresentationListener contains methods to react to triggered events from the user.
 */
public interface MediaPresentationListener {


  /**
   * Method to present a media file.
   *
   * @param mediaReference reference to media file to be presented
   */
   void present(MediaReference mediaReference);



}