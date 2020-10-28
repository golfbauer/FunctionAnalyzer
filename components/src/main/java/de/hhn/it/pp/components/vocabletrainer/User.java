package de.hhn.it.pp.components.vocabletrainer;

public class User {
  private int id;
  private String name;
  private int score;

  public User() {
  }

  public int getId() {
    return id;
  }

  public int getScore() {
    return score;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setScore(int score) {
    this.score = score;
  }
}
