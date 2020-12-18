package de.hhn.it.pp.javafx.controllers.spellingtrainer;

import de.hhn.it.pp.components.spellingtrainer.provider.MediaPresentationListener;
import de.hhn.it.pp.components.spellingtrainer.provider.MediaReference;
import de.hhn.it.pp.javafx.Main;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioPresentationListener implements MediaPresentationListener {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(AudioPresentationListener.class);

  public AudioPresentationListener() {
    super();
  }

  /**
   * Method to present a media file.
   *
   * @param mediaReference reference to media file to be presented
   */
  @Override
  public void present(MediaReference mediaReference) {
    Media media = new Media(Main.class.getResource(mediaReference.getMediaFile()).toString());
    MediaPlayer player = new MediaPlayer(media);
    player.play();
  }
}
