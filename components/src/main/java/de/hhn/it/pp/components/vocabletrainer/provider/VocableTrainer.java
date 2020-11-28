package de.hhn.it.pp.components.vocabletrainer.provider;

import de.hhn.it.pp.components.vocabletrainer.Vocable;
import java.util.List;

public interface VocableTrainer {

  void loadData(String category, List<Vocable> vocableListe);

  void setScore(int score);

  int getScore();

}
