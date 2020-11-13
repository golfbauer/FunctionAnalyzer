package de.hhn.it.pp.components.spellingtrainer;

public class LearningEntry {
  private MediaReference mediaReference;
  private String wordEntry;

  /**
   * Constructor which builds an LearningEntry with an media reference and it's according string.
   *
   * @param mediaReference File to be referenced
   * @param wordEntry      word, that has to be spelled and is connected to the media reference
   */
  public LearningEntry(MediaReference mediaReference, String wordEntry) {
    this.mediaReference = mediaReference;
    this.wordEntry = wordEntry;
  }


  public void setWordEntry(String wordEntry) {
    this.wordEntry = wordEntry;
  }

  public String getWordEntry() {
    return this.wordEntry;
  }

  public void setMediaReference(MediaReference mediaReference) {
    this.mediaReference = mediaReference;
  }

  public MediaReference getMediaReference() {
    return this.mediaReference;
  }

}
