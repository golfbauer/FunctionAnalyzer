package de.hhn.it.pp.components.spellingtrainer;

import java.io.File;

public class MediaReference {
  private File mediaFile;

  /**
   * Constructor to reference an audio file.
   *
   * @param mediaFile File to be referenced
   */
  public MediaReference(File mediaFile) {
    this.mediaFile = mediaFile;
  }

}
