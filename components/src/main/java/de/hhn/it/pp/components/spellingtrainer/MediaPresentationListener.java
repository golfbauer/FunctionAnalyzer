package de.hhn.it.pp.components.spellingtrainer;

import java.util.ArrayList;

/**
 * Class MediaPresentationListener contains methods to react to triggered events from the user.
 */
public class MediaPresentationListener {

  static ArrayList<MediaPresentationListener> mediaPresentationListeners =
      new ArrayList<>();

  /**
   * Method to present a media file.
   *
   * @param mediaReference reference to media file to be presented
   */
  public void present(MediaReference mediaReference) {

  }

}
