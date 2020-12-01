package de.hhn.it.pp.components.spellingtrainer.Provider;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

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
  public void present(MediaReference mediaReference)
      throws LineUnavailableException, IOException, UnsupportedAudioFileException {
      AudioInputStream inputStream = AudioSystem.getAudioInputStream(mediaReference.getMediaFile());
      Clip clip = AudioSystem.getClip();
      clip.open(inputStream);
  }




}
