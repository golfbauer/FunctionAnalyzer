package de.hhn.it.pp.components.vocabletrainer;

public class Vocable {
  private String originWord;
  private String foreignWord;
  private int Id;

  public int getId() {
    return Id;
  }

  public String getForeignWord() {
    return foreignWord;
  }

  public String getOriginWord() {
    return originWord;
  }

  public void setForeignWord(String foreignWord) {
    this.foreignWord = foreignWord;
  }

  public void setId(int id) {
    Id = id;
  }

  public void setOriginWord(String originWord) {
    this.originWord = originWord;
  }
}
