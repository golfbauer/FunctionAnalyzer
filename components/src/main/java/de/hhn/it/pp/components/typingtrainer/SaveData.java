package de.hhn.it.pp.components.typingtrainer;

public class SaveData {
  private String text;
  private String time;
  private String wpm;

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
