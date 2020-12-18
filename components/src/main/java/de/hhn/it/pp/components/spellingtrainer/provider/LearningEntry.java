package de.hhn.it.pp.components.spellingtrainer.provider;

/**
 * Class LearningEntry contains an constructor to connect an media reference to a string.
 * It provides getter and setter methods for the media reference and words.
 */
public class LearningEntry {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(LearningEntry.class);
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

  public String getWordEntry() {
    return this.wordEntry;
  }

  public void setWordEntry(String wordEntry) {
    this.wordEntry = wordEntry;
  }

  public MediaReference getMediaReference() {
    return this.mediaReference;
  }

  public void setMediaReference(MediaReference mediaReference) {
    this.mediaReference = mediaReference;
  }
}