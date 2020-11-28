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

  public static MediaPresentationListener getMediaPresentationListener(int index){
    return mediaPresentationListeners.get(index);
  }

  public static void addMediaPresentationListener(MediaPresentationListener mediaPresentationListener){
    mediaPresentationListeners.add(mediaPresentationListener);
  }

  public static void removeMediaPresentationListener(MediaPresentationListener mediaPresentationListener){
    mediaPresentationListeners.remove(mediaPresentationListener);
  }

}
