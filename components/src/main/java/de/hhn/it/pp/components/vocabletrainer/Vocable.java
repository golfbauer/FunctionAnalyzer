package de.hhn.it.pp.components.vocabletrainer;

public class Vocable {
  private String originWord;
  private String foreignWord;


  public Vocable(String originWord, String foreignWord) {
    this.originWord = originWord;
    this.foreignWord = foreignWord;
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


  public void setOriginWord(String originWord) {
    this.originWord = originWord;
  }
}
