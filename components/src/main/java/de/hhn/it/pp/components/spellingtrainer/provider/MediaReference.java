package de.hhn.it.pp.components.spellingtrainer.provider;

/**
 * Class MediaReference contains an constructor which references an media file.
 */
public class MediaReference {
  private String mediaFile;

  /**
   * Constructor to reference an audio file.
   *
   * @param mediaFile File to be referenced
   */
  public MediaReference(String mediaFile) {
    this.mediaFile = mediaFile;
  }

  public String getMediaFile() {
    return this.mediaFile;
  }
}