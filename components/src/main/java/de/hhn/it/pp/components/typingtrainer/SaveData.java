package de.hhn.it.pp.components.typingtrainer;

/***
 * @author Tobias Maraci, Robert Pistea
 * @version 1.0
 * @since 1.1
 */
public class SaveData {
  private String text;
  private String time;
  private String wpm;

  /**
   * SaveData Constructor sets text, time and wpm to be used in tableview gui
   * @param text PracticeText that was used
   * @param time Time needed for completion
   * @param wpm Words Per Minute in that round of training
   */
  public SaveData(String text, String time, String wpm)
  {
    this.text = text;
    this.time = time;
    this.wpm = wpm;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getTime() {
    return time;
  }

  public void setWpm(String wpm) {
    this.wpm = wpm;
  }

  public String getWpm() {
    return wpm;
  }
}
