package de.hhn.it.pp.components.spellingtrainer.Provider;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

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
  public void present(MediaReference mediaReference) throws Exception{
      AudioInputStream inputStream = AudioSystem.getAudioInputStream(mediaReference.getMediaFile());
      Clip clip = AudioSystem.getClip();
      clip.open(inputStream);
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
